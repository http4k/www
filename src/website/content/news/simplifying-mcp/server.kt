package content.news.`simplifying-mcp`

import org.http4k.core.Uri
import org.http4k.ai.mcp.ToolResponse
import org.http4k.ai.mcp.model.Content
import org.http4k.ai.mcp.model.McpEntity
import org.http4k.ai.mcp.model.Tool
import org.http4k.ai.mcp.protocol.ServerMetaData
import org.http4k.ai.mcp.protocol.Version
import org.http4k.ai.mcp.server.security.OAuthMcpSecurity
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
    ).asServer(JettyLoom(3001)).start()
}
