import org.http4k.client.JavaHttpClient
import org.http4k.core.ContentType
import org.http4k.core.Method.GET
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status.Companion.OK
import org.http4k.lens.Path
import org.http4k.lens.accept
import org.http4k.routing.sse
import org.http4k.routing.sse.bind
import org.http4k.server.PolyHandler
import org.http4k.server.Undertow
import org.http4k.server.asServer
import org.http4k.sse.Sse
import org.http4k.sse.SseFilter
import org.http4k.sse.SseMessage
import org.http4k.sse.SseResponse
import org.http4k.sse.then
import kotlin.concurrent.thread

fun main() {
    val namePath = Path.of("name")

    // a filter allows us to intercept the call to the sse and do logging etc...
    val sayHello = SseFilter { next ->
        {
            println("Hello from the sse!")
            next(it)
        }
    }

    val sse = sayHello.then(
        sse(
            "/hello/{name}" bind { req ->
                SseResponse { sse: Sse ->
                    val name = namePath(req)
                    thread {
                        repeat(10) {
                            sse.send(SseMessage.Data("hello $it"))
                            Thread.sleep(100)
                        }
                        sse.close()
                    }
                    sse.onClose { println("$name is closing") }
                }
            }
        )
    )

    val http = { req: Request -> Response(OK).body("hitting HTTP server: " + req.uri) }

    PolyHandler(http, sse = sse).asServer(Undertow(9000)).start()

    val httpClient = JavaHttpClient()

    // send an sse request - we need the event stream accept content type for it to be picked up by the Sse handler
    httpClient(
        Request(GET, "http://localhost:9000/hello/bob")
            .accept(ContentType.TEXT_EVENT_STREAM)
    ).bodyString().let(::println)

    // if the SSE does not match it will fall back to HTTP
    httpClient(
        Request(GET, "http://localhost:9000/notbob")
            .accept(ContentType.TEXT_EVENT_STREAM)
    ).bodyString().let(::println)

    // without the accept header, it will just be a normal http request
    httpClient(
        Request(GET, "http://localhost:9000/hello/no_accept_header")
    ).bodyString().let(::println)
}
