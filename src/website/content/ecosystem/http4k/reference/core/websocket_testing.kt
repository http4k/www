package content.ecosystem.http4k.reference.core

import org.http4k.core.Method
import org.http4k.core.Request
import org.http4k.core.Status
import org.http4k.websocket.WsMessage
import org.http4k.websocket.testWsClient

val wsClient = polyApp.testWsClient(Request(Method.GET, "ws://localhost:9000/hello/bob"))!!

val testWs = run {
    wsClient.send(WsMessage("1"))
    wsClient.close(Status(200, "bob"))

    wsClient.received.take(2).forEach(::println)
}
