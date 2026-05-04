package content._sites.a2a

import org.http4k.routing.a2aJsonRpc
import org.http4k.server.Helidon
import org.http4k.server.asServer

val server = a2aJsonRpc(agentCard, handler)
    .asServer(Helidon(8080))
    .start()
