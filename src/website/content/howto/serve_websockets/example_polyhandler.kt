package content.howto.serve_websockets

import org.http4k.core.Method.GET
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status.Companion.OK
import org.http4k.lens.Path
import org.http4k.routing.bindHttp
import org.http4k.routing.poly
import org.http4k.routing.routes
import org.http4k.routing.websocket.bind
import org.http4k.routing.websockets
import org.http4k.server.Jetty
import org.http4k.server.asServer
import org.http4k.websocket.Websocket
import org.http4k.websocket.WsMessage
import org.http4k.websocket.WsResponse

fun main() {
    val namePath = Path.of("name")

    val ws = websockets(
        "/{name}" bind { req: Request ->
            WsResponse { ws: Websocket ->
                val name = namePath(req)
                ws.send(WsMessage("hello $name"))
                ws.onMessage {
                    ws.send(WsMessage("$name is responding"))
                }
                ws.onClose { println("$name is closing") }
            }
        }
    )
    val http = routes("all:{.+}" bindHttp GET to { _: Request ->
        Response(OK).body("hiya world")
    })

    poly(http, ws).asServer(Jetty(9000)).start()
}
