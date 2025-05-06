package content.news.http4k_mcp_has_landed

import org.http4k.mcp.ToolResponse
import org.http4k.mcp.model.Content
import org.http4k.mcp.model.McpEntity
import org.http4k.mcp.model.Tool
import org.http4k.mcp.model.string
import org.http4k.mcp.protocol.ServerMetaData
import org.http4k.mcp.protocol.Version
import org.http4k.routing.bind
import org.http4k.routing.mcpHttpStreaming
import org.http4k.server.Jetty
import org.http4k.server.asServer

fun main() {
    // 1. Define a tool
    val cityArg = Tool.Arg.string().required("city", "City name")

    val weatherTool = Tool(
        "weather",
        "Gets city weather",
        cityArg
    ) bind { req ->
        val city = cityArg(req)
        ToolResponse.Ok(Content.Text("Weather in $city: Sunny and 25°C"))
    }

    // 2. Create an MCP server
    val mcpServer = mcpHttpStreaming(
        ServerMetaData(McpEntity.of("Weather API"), Version.of("1.0.0")),
        weatherTool
    )

    // 3. Start the server
    mcpServer.asServer(Jetty(3000)).start()

    println("MCP Server running on http://localhost:3000/mcp")
}
