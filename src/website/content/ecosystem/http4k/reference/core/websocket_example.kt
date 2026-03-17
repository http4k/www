package content.ecosystem.http4k.reference.core

import org.http4k.core.Request
import org.http4k.lens.Path
import org.http4k.lens.string
import org.http4k.routing.websocket.bind
import org.http4k.routing.websockets
import org.http4k.websocket.Websocket
import org.http4k.websocket.WsHandler
import org.http4k.websocket.WsMessage
import org.http4k.websocket.WsResponse

class Wrapper(val value: String)

val body = WsMessage.string().map(::Wrapper, Wrapper::value).toLens()

val nameLens = Path.of("name")

val ws: WsHandler = websockets(
    "/hello" bind websockets(
        "/{name}" bind { req: Request ->
            WsResponse { ws: Websocket ->
                val name = nameLens(req)
                ws.send(WsMessage("hello $name"))
                ws.onMessage {
                    val received = body(it)
                    ws.send(body(received))
                }
                ws.onClose {
                    println("closed")
                }
            }
        }
    )
)
