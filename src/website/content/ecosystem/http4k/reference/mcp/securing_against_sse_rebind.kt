package content.ecosystem.http4k.reference.mcp

import org.http4k.core.Method.DELETE
import org.http4k.core.Method.GET
import org.http4k.core.Method.POST
import org.http4k.core.Uri
import org.http4k.core.then
import org.http4k.filter.AnyOf
import org.http4k.filter.CorsAndRebindProtection
import org.http4k.filter.CorsPolicy
import org.http4k.filter.OriginPolicy
import org.http4k.filter.ServerFilters
import org.http4k.mcp.model.McpEntity
import org.http4k.mcp.protocol.ServerMetaData
import org.http4k.mcp.protocol.ServerProtocolCapability.ToolsChanged
import org.http4k.mcp.protocol.Version
import org.http4k.mcp.server.security.OAuthMcpSecurity
import org.http4k.routing.bind
import org.http4k.routing.mcpHttpStreaming
import org.http4k.server.Helidon
import org.http4k.server.asServer

fun main() {
    val mcpServer = mcpHttpStreaming(
        ServerMetaData(McpEntity.of("http4k MCP Server"), Version.of("1.0.0"), ToolsChanged),
        toolDefinitionFor("David") bind diaryToolHandler,
    )

    // Define a CORS policy to protect against cross-origin requests and DNS rebinding attacks
    val corsPolicy = CorsPolicy(
        OriginPolicy.AnyOf("foo.com", "localhost"),
        listOf("allowed-header"), listOf(GET, POST, DELETE)
    )

    ServerFilters.CorsAndRebindProtection(corsPolicy)
        .then(mcpServer)
        .asServer(Helidon(3002)).start()
}
