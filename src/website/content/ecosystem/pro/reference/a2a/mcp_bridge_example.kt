package content.ecosystem.pro.reference.a2a

import org.http4k.ai.mcp.model.McpEntity
import org.http4k.ai.mcp.protocol.ServerMetaData
import org.http4k.ai.mcp.protocol.Version
import org.http4k.ai.mcp.server.security.NoMcpSecurity
import org.http4k.core.Uri
import org.http4k.routing.mcpA2aBridgeServer
import org.http4k.server.Helidon
import org.http4k.server.asServer

fun main() {
    // Expose an existing A2A agent as a full MCP HTTP server. Tools generated:
    //  - send_message: send a free-text message to the agent (with skill catalog in the description)
    //  - get_task / cancel_task / list_tasks: task lifecycle management
    //
    // The inbound MCP request's Authorization header is forwarded to the A2A
    // agent, so each MCP caller authenticates as itself against the agent.
    val server = mcpA2aBridgeServer(
        identity = ServerMetaData(McpEntity.of("a2a-bridge"), Version.of("1.0.0")),
        baseUri = Uri.of("https://my-a2a-agent.example.com"),
        security = NoMcpSecurity
    )

    server.asServer(Helidon(3001)).start()
}
