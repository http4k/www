package content.news.`simplifying-mcp`

import org.http4k.core.Uri
import org.http4k.mcp.ToolResponse
import org.http4k.mcp.model.Content
import org.http4k.mcp.model.McpEntity
import org.http4k.mcp.model.Tool
import org.http4k.mcp.protocol.ServerMetaData
import org.http4k.mcp.protocol.Version
import org.http4k.mcp.server.security.OAuthMcpSecurity
import org.http4k.routing.bind
import org.http4k.routing.mcpHttpStreaming
import org.http4k.server.JettyLoom
import org.http4k.server.asServer
import java.time.Instant

fun main() {
    mcpHttpStreaming(
        ServerMetaData(McpEntity.of("foo"), Version.of("bar")),
        OAuthMcpSecurity(Uri.of("https://oauth-server")) { it == "my_oauth_token" },
        Tool("time", "Get the current time") bind {
            ToolResponse.Ok(listOf(Content.Text(Instant.now().toString())))
        }
    )
        .asServer(JettyLoom(3001)).start()
}
