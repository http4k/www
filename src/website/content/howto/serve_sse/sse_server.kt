package content.howto.serve_sse

import org.http4k.routing.sse
import org.http4k.server.Undertow
import org.http4k.server.asServer
import org.http4k.sse.Sse
import org.http4k.sse.SseMessage

val server = sse({ sse: Sse -> sse.send(SseMessage.Data("hello")) }).asServer(Undertow(9000)).start()
