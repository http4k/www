package content._sites.mcp

import org.http4k.core.Uri
import org.http4k.lens.with
import org.http4k.ai.mcp.ToolRequest
import org.http4k.ai.mcp.ToolResponse
import org.http4k.ai.mcp.model.McpEntity
import org.http4k.ai.mcp.model.Tool
import org.http4k.ai.mcp.model.string
import org.http4k.ai.mcp.protocol.ServerMetaData
import org.http4k.ai.mcp.protocol.Version
import org.http4k.ai.mcp.server.capability.ToolCapability
import org.http4k.ai.mcp.server.security.OAuthMcpSecurity
import org.http4k.ai.mcp.util.McpJson.auto
import org.http4k.routing.bind
import org.http4k.routing.mcpHttpStreaming
import org.http4k.server.JettyLoom
import org.http4k.server.asServer


fun main() {
    val mcpServer = mcpHttpStreaming(
        ServerMetaData(McpEntity.of("http4k MCP Server"), Version.of("1.0.0")),
        OAuthMcpSecurity(Uri.of("https://oauth-server"), Uri.of("https://mcp-server/mcp")) { it == "my_oauth_token" },
        liveWeatherTool(),
        loadFromFileSystem(),
        searchWebsite()
    )

    mcpServer.asServer(JettyLoom(3002)).start()
}

fun liveWeatherTool(): ToolCapability {
    val city = Tool.Arg.string().required("city")
    val report = Tool.Output.auto(WeatherReport(100, "Sunny")).toLens()
    return Tool(
        "weather",
        "Checks the weather for a particular city.", city, output = report
    ) bind { request: ToolRequest ->
        ToolResponse.Ok().with(report of WeatherReport(100, "Sunny in ${city(request)}"))
    }
}

data class WeatherReport(val temperature: Int, val outlook: String)


fun loadFromFileSystem(): ToolCapability = TODO()
fun searchWebsite(): ToolCapability = TODO()
