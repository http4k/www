package content.ecosystem.pro.reference.a2a

import org.http4k.ai.a2a.model.AgentCard
import org.http4k.ai.a2a.model.Version
import org.http4k.routing.a2aRest

// REST binding supports multi-tenant routing with /{tenant} prefix
// Requests to /acme/message:send will have tenant="acme"
val restServer = a2aRest(
    agentCard = AgentCard("Multi-tenant Agent", Version.of("1.0.0"), "Serves multiple tenants"),
    messageHandler = { request ->
        // Tenant is available in the HTTP request headers/path
        TODO()
    }
)

// JSON-RPC passes tenant via the request params
// val server = a2aJsonRpc(agentCard) { request -> ... }
