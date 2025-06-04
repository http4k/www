package content.news.http4k_mcp_has_landed

import org.http4k.core.Uri
import org.http4k.ai.mcp.ToolResponse
import org.http4k.ai.mcp.model.Content
import org.http4k.ai.mcp.model.McpEntity
import org.http4k.ai.mcp.model.Tool
import org.http4k.ai.mcp.model.string
import org.http4k.ai.mcp.protocol.ServerMetaData
import org.http4k.ai.mcp.protocol.Version
import org.http4k.ai.mcp.server.security.BearerAuthMcpSecurity
import org.http4k.ai.mcp.server.security.OAuthMcpSecurity
import org.http4k.routing.bind
import org.http4k.routing.mcpHttpStreaming
import org.http4k.server.Jetty
import org.http4k.server.asServer

fun main() {
    // 1. Define a tool using a typesafe lens
    val cityArg = Tool.Arg.string().required("city", "City name")

    val weatherTool = Tool(
        "weather",
        "Gets city weather",
        cityArg
    ) bind { req ->
        val city = cityArg(req)
        ToolResponse.Ok(Content.Text("Weather in $city: Sunny and 25°C"))
    }

    // 2. Create an MCP server and select your security model
    val mcpServer = mcpHttpStreaming(
        ServerMetaData(McpEntity.of("Weather API"), Version.of("1.0.0")),
        OAuthMcpSecurity(Uri.of("https://oauth-server")) { it == "my_oauth_token" },
        weatherTool
    )

    // 3. Start the server
    mcpServer.asServer(Jetty(3000)).start()

    println("MCP Server running on http://localhost:3000/mcp")
}
