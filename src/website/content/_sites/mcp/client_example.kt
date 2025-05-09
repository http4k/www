package content._sites.mcp

import org.http4k.connect.model.ToolName
import org.http4k.core.Uri
import org.http4k.mcp.ToolRequest
import org.http4k.mcp.client.http.HttpStreamingMcpClient
import org.http4k.mcp.model.McpEntity
import org.http4k.mcp.protocol.Version

fun main() {

    val mcpClient = HttpStreamingMcpClient(
        McpEntity.of("http4k mcp client"), Version.of("1.0.0"),
        Uri.of("http://localhost:3001/mcp"),
    )

    mcpClient.start()

    mcpClient.tools().call(ToolName.of("weather"), ToolRequest(mapOf("city" to "london")))

}
