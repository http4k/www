package content.ecosystem.http4k.reference.core

import org.http4k.core.Method.GET
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status.Companion.OK
import org.http4k.routing.bind
import org.http4k.routing.bindWs
import org.http4k.routing.poly
import org.http4k.routing.routes
import org.http4k.routing.websockets
import org.http4k.server.Jetty
import org.http4k.server.asServer
import org.http4k.websocket.Websocket
import org.http4k.websocket.WsMessage
import org.http4k.websocket.WsResponse

val polyApp = poly(
    routes(
        "/" bind GET to { r: Request -> Response(OK) }
    ),
    websockets(
        "/ws" bindWs { req: Request ->
            WsResponse { ws: Websocket ->
                ws.send(WsMessage("hello!"))
            }
        }
    )
)
val polyServer = polyApp.asServer(Jetty(9000)).start()
