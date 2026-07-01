package content.ecosystem.pro.reference.mcp

import org.http4k.ai.mcp.model.McpEntity
import org.http4k.ai.mcp.protocol.ServerMetaData
import org.http4k.ai.mcp.protocol.Version
import org.http4k.ai.mcp.server.security.NoMcpSecurity
import org.http4k.core.Uri
import org.http4k.routing.mcpA2aBridgeServer
import org.http4k.server.Helidon
import org.http4k.server.asServer

fun main() {
    // Point at any A2A agent - the agent card is fetched once at construction.
    // The send_message / get_task / cancel_task / list_tasks tools are exposed
    // under /mcp. The inbound MCP request's Authorization header is forwarded
    // to the A2A agent on every tool call.
    val server = mcpA2aBridgeServer(
        identity = ServerMetaData(McpEntity.of("a2a-bridge"), Version.of("1.0.0")),
        baseUri = Uri.of("https://my-a2a-agent.example.com"),
        security = NoMcpSecurity
    )

    server.asServer(Helidon(3001)).start()
}
