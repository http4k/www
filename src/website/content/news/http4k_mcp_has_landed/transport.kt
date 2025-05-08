package content.news.http4k_mcp_has_landed

import org.http4k.core.Credentials
import org.http4k.mcp.model.McpEntity
import org.http4k.mcp.protocol.ServerMetaData
import org.http4k.mcp.protocol.Version
import org.http4k.mcp.server.security.BasicAuthMcpSecurity
import org.http4k.mcp.server.security.BearerAuthMcpSecurity
import org.http4k.routing.mcpHttpStreaming
import org.http4k.routing.mcpWebsocket

// Use the standard Streamable HTTP transport
val mcpServer = mcpHttpStreaming(
    ServerMetaData(McpEntity.of("My MCP Server"), Version.of("1.0.0")),
    BasicAuthMcpSecurity("realm") { it == Credentials("user", "password") },
    weatherTool
)

// Or use WebSockets
val wsServer = mcpWebsocket(
    ServerMetaData(McpEntity.of("WebSocket MCP Server"), Version.of("1.0.0")),
    BasicAuthMcpSecurity("realm") { it == Credentials("user", "password") },
    weatherTool
)
