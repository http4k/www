package content.news.http4k_mcp_has_landed

import org.http4k.mcp.model.McpEntity
import org.http4k.mcp.protocol.ServerMetaData
import org.http4k.mcp.protocol.Version
import org.http4k.routing.mcpHttpStreaming
import org.http4k.routing.mcpWebsocket

// Use the standard Streamable HTTP transport
val mcpServer = mcpHttpStreaming(
    ServerMetaData(McpEntity.of("My MCP Server"), Version.of("1.0.0")),
    weatherTool
)

// Or use WebSockets
val wsServer = mcpWebsocket(
    ServerMetaData(McpEntity.of("WebSocket MCP Server"), Version.of("1.0.0")),
    weatherTool
)
