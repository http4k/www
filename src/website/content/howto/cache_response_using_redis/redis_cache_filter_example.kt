package content.howto.cache_response_using_redis

import io.micrometer.core.instrument.MockClock.clock
import org.http4k.config.Environment
import org.http4k.config.EnvironmentKey
import org.http4k.core.Filter
import org.http4k.core.HttpHandler
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status
import org.http4k.core.Uri
import org.http4k.core.then
import org.http4k.events.AutoMarshallingEvents
import org.http4k.events.Event
import org.http4k.events.EventFilters
import org.http4k.events.Events
import org.http4k.events.HttpEvent
import org.http4k.events.then
import org.http4k.filter.ResponseFilters
import org.http4k.format.Jackson
import org.http4k.lens.int
import org.http4k.lens.string
import org.http4k.routing.bind
import org.http4k.routing.routes
import org.http4k.server.Jetty
import org.http4k.server.asServer
import redis.clients.jedis.HostAndPort
import redis.clients.jedis.Protocol
import redis.clients.jedis.RedisClient
import redis.clients.jedis.commands.JedisCommands
import redis.clients.jedis.exceptions.JedisConnectionException
import java.net.SocketTimeoutException
import java.security.MessageDigest
import java.time.Clock
import java.time.Duration


fun sha256Key(uri: Uri): String {
    val digest = MessageDigest.getInstance("SHA-256")
    val hashBytes = digest.digest(uri.toString().toByteArray(Charsets.UTF_8))
    return hashBytes.joinToString("") { "%02x".format(it) }
}

enum class CacheEventType { HIT, MISS, INSERT }
data class CacheEvent(val type: CacheEventType, val uri: Uri, val key: String) : Event

fun redisCacheFilter(
    redis: JedisCommands,
    events: Events,
    prefix: String = "cache",
    ttl: (Request) -> Duration = { Duration.ofMinutes(5) },
    key: (Request) -> String = { sha256Key(it.uri) }
): Filter = Filter { next ->
    { request ->
        val cacheKey = key(request)
        val bodyKey = "$prefix:body:$cacheKey"
        val headersKey = "$prefix:headers:$cacheKey"

        try {
            redis.get(bodyKey)?.let { cachedBody ->
                events(CacheEvent(CacheEventType.HIT, request.uri, bodyKey))
                redis.hgetAll(headersKey).entries.fold(
                    Response(Status.OK).body(cachedBody)
                ) { resp, (name, value) -> resp.header(name, value) }
            } ?: run {
                events(CacheEvent(CacheEventType.MISS, request.uri, bodyKey))

                next(request).also { response ->
                    if (response.status == Status.OK) {
                        events(CacheEvent(CacheEventType.INSERT, request.uri, bodyKey))

                        val cacheTtl = ttl(request)
                        try {
                            with(redis) {
                                set(bodyKey, response.bodyString())
                                expire(bodyKey, cacheTtl.toSeconds())

                                hset(headersKey, response.headers.toMap())
                                expire(headersKey, cacheTtl.toSeconds())
                            }
                        } catch (_: JedisConnectionException) {
                            // ignore
                        } catch (_: SocketTimeoutException) {
                            // ignore
                        }
                    }
                }
            }
        } catch (_: JedisConnectionException) {
            next(request)
        } catch (_: SocketTimeoutException) {
            next(request)
        }
    }
}

fun slowHandler(): HttpHandler = {
    Thread.sleep(1000)
    Response(Status.OK)
        .header("Content-Type", "text/plain")
        .body("Hello, World!")
}

fun main() {

    // You'll want a redis server running on your local machine for this:
    // docker run -p 6379:6379 redis:8.2.0

    val redisHost = EnvironmentKey.string().defaulted("REDIS_HOST", default = "localhost")
    val redisPort = EnvironmentKey.int().defaulted("REDIS_PORT", default = Protocol.DEFAULT_PORT)

    val environment = Environment.JVM_PROPERTIES overrides Environment.ENV

    val redis = RedisClient.create(HostAndPort(redisHost(environment), redisPort(environment)))

    val clock = Clock.systemUTC()

    val events = EventFilters.AddTimestamp(clock)
        .then(EventFilters.AddEventName())
        .then(AutoMarshallingEvents(Jackson))

    val app = ResponseFilters.ReportHttpTransaction { events(HttpEvent.Incoming(it)) }
        .then(
            routes(
                "/" bind redisCacheFilter(redis = redis, events = events)
                    .then(slowHandler())
            )
        )

    val jettyServer = app.asServer(Jetty(9000)).start()

    jettyServer.start()

}
