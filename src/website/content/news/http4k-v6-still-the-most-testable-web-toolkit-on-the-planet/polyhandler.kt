package content.news.`http4k-v6-still-the-most-testable-web-toolkit-on-the-planet`

import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status.Companion.OK
import org.http4k.routing.bindHttp
import org.http4k.routing.bindSse
import org.http4k.routing.bindWs
import org.http4k.routing.poly
import org.http4k.routing.sse
import org.http4k.routing.websockets
import org.http4k.sse.SseMessage
import org.http4k.websocket.Websocket
import org.http4k.websocket.WsMessage

val app = poly(
    "/http" bindHttp { req: Request -> Response(OK).body(req.body) },
    "/ws" bindWs websockets { ws: Websocket ->
        ws.send(WsMessage("hello"))
    },
    "/sse" bindSse sse {
        it.send(SseMessage.Data("hello"))
        it.close()
    }
)
