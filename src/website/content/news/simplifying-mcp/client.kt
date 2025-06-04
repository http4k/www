package content.news.`simplifying-mcp`

import content.news.http4k_mcp_has_landed.cityArg
import org.http4k.client.JavaHttpClient
import org.http4k.ai.model.ToolName
import org.http4k.core.Credentials
import org.http4k.core.Uri
import org.http4k.core.then
import org.http4k.filter.ClientFilters
import org.http4k.lens.with
import org.http4k.ai.mcp.ToolRequest
import org.http4k.ai.mcp.client.DiscoveredMcpOAuth
import org.http4k.ai.mcp.client.http.HttpStreamingMcpClient
import org.http4k.ai.mcp.model.McpEntity
import org.http4k.ai.mcp.protocol.Version

val clientCredentials = Credentials("clientId", "clientSecret")
val autoDiscoveryHttp = ClientFilters.DiscoveredMcpOAuth(clientCredentials)
    .then(JavaHttpClient())

// Create an MCP client
val client = HttpStreamingMcpClient(McpEntity.of("My Client"), Version.of("1.0.0"),
    Uri.of("http://localhost:3000/mcp"),
    autoDiscoveryHttp
).apply { start() }

// Call tools programmatically
val toolResponse = client.tools().call(
    ToolName.of("weather"),
    ToolRequest().with(cityArg of "London"),
)

