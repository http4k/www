package content.news.http4k_mcp_has_landed

import org.http4k.connect.model.ToolName
import org.http4k.core.Uri
import org.http4k.lens.with
import org.http4k.mcp.ToolRequest
import org.http4k.mcp.client.http.HttpStreamingMcpClient
import org.http4k.mcp.model.McpEntity
import org.http4k.mcp.protocol.Version

// Create an MCP client
val client = HttpStreamingMcpClient(
    McpEntity.of("My Client"),
    Version.of("1.0.0"),
    Uri.of("http://localhost:3000/sse"),
).apply { start() }

// Call tools programmatically
val toolResponse = client.tools().call(
    ToolName.of("weather"),
    ToolRequest().with(cityArg of "London"),
)
