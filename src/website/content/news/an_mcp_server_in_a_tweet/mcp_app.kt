package content.news.an_mcp_server_in_a_tweet

import org.http4k.ai.mcp.model.apps.McpAppResourceMeta
import org.http4k.ai.mcp.protocol.ServerMetaData
import org.http4k.ai.mcp.server.capability.extension.RenderMcpApp
import org.http4k.ai.mcp.server.security.NoMcpSecurity
import org.http4k.core.Uri
import org.http4k.routing.mcp

fun MyMcpApp() = mcp(
    ServerMetaData("Greeter", "1.0.0"),
    NoMcpSecurity,
    RenderMcpApp("greet", "say hello", Uri.of("ui://hello"), McpAppResourceMeta()) {
        """
        <html>
        <body>
            <h1>UI goes here!</h1>
        </body>
        </html>
        """
    }
)
