package content.ecosystem.ai.reference.mcp

import org.http4k.ai.mcp.model.McpEntity
import org.http4k.ai.mcp.protocol.ServerMetaData
import org.http4k.ai.mcp.protocol.ServerProtocolCapability.ToolsChanged
import org.http4k.ai.mcp.protocol.Version
import org.http4k.ai.mcp.server.security.NoMcpSecurity
import org.http4k.routing.bind
import org.http4k.routing.mcpHttpNonStreaming
import org.http4k.server.SunHttp
import org.http4k.server.asServer

fun main() {
    // this protocol version does not support SSE connections.
    val mcpServer = mcpHttpNonStreaming(
        ServerMetaData(McpEntity.of("http4k MCP Server"), Version.of("1.0.0"), ToolsChanged),
        NoMcpSecurity,
        toolDefinitionFor("David") bind diaryToolHandler,
    )

    // simply start it up on any server you like!
    mcpServer.asServer(SunHttp(3002)).start()
}
