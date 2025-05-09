package content._sites.mcp

import org.http4k.core.Uri
import org.http4k.mcp.ToolRequest
import org.http4k.mcp.ToolResponse
import org.http4k.mcp.model.Content
import org.http4k.mcp.model.McpEntity
import org.http4k.mcp.model.Tool
import org.http4k.mcp.model.string
import org.http4k.mcp.protocol.ServerMetaData
import org.http4k.mcp.protocol.Version
import org.http4k.mcp.server.capability.ToolCapability
import org.http4k.mcp.server.security.OAuthMcpSecurity
import org.http4k.routing.bind
import org.http4k.routing.mcpHttpStreaming
import org.http4k.server.JettyLoom
import org.http4k.server.asServer


fun main() {
    val mcpServer = mcpHttpStreaming(
        ServerMetaData(McpEntity.of("http4k MCP Server"), Version.of("1.0.0")),
        OAuthMcpSecurity(Uri.of("https://oauth-server")) { it == "my_oauth_token" },
        liveWeatherTool(),
        loadFromFileSystem(),
        searchWebsite()
    )

    mcpServer.asServer(JettyLoom(3002)).start()
}


fun liveWeatherTool(): ToolCapability {
    val input = Tool.Arg.string().required("city")
    return Tool(
        "weather",
        "Checks the weather for a particular city.", input
    ) bind { request: ToolRequest ->
        ToolResponse.Ok(listOf(Content.Text("Sunny and 100 degrees")))
    }
}


fun loadFromFileSystem(): ToolCapability = TODO()
fun searchWebsite(): ToolCapability = TODO()
