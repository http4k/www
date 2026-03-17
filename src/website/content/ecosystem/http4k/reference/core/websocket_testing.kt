package content.ecosystem.http4k.reference.core

import org.http4k.core.Method.GET
import org.http4k.core.Request
import org.http4k.testing.testWsClient
import org.http4k.websocket.WsMessage
import org.http4k.websocket.WsStatus

val wsClient = polyApp.testWsClient(Request(GET, "ws://localhost:9000/hello/bob"))

val testWs = run {
    wsClient.send(WsMessage("1"))
    wsClient.close(WsStatus(200, "bob"))

    wsClient.received().take(2).forEach(::println)
}
