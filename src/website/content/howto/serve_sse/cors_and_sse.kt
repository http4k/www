package content.howto.serve_sse

import org.http4k.core.Method.DELETE
import org.http4k.core.Method.GET
import org.http4k.core.Method.POST
import org.http4k.core.then
import org.http4k.filter.AnyOf
import org.http4k.filter.CorsAndRebindProtection
import org.http4k.filter.CorsPolicy
import org.http4k.filter.OriginPolicy
import org.http4k.filter.ServerFilters
import org.http4k.routing.poly
import org.http4k.routing.sse
import org.http4k.routing.sse.bind
import org.http4k.server.Helidon
import org.http4k.server.asServer
import org.http4k.sse.SseMessage

fun main() {
    val sseServer = poly(
        "/sse" bind sse { sse ->
            sse.send(SseMessage.Data("hello!"))
            sse.send(SseMessage.Data("world!"))
            sse.close()
        }
    )

    // Define a CORS policy to protect against cross-origin requests and DNS rebinding attacks
    val corsPolicy = CorsPolicy(
        OriginPolicy.AnyOf("foo.com", "localhost"),
        listOf("allowed-header"), listOf(GET, POST, DELETE)
    )

    ServerFilters.CorsAndRebindProtection(corsPolicy)
        .then(sseServer)
        .asServer(Helidon(3002)).start()
}
