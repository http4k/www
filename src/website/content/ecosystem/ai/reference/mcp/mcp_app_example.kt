package content.ecosystem.ai.reference.mcp

import org.http4k.ai.mcp.model.Domain
import org.http4k.ai.mcp.model.apps.Csp
import org.http4k.ai.mcp.model.apps.McpAppResourceMeta
import org.http4k.ai.mcp.model.apps.McpAppVisibility.app
import org.http4k.ai.mcp.model.apps.McpAppVisibility.model
import org.http4k.ai.mcp.model.apps.McpApps
import org.http4k.ai.mcp.protocol.ServerMetaData
import org.http4k.ai.mcp.protocol.withExtensions
import org.http4k.ai.mcp.server.capability.extension.RenderMcpApp
import org.http4k.ai.mcp.server.security.NoMcpSecurity
import org.http4k.core.Uri
import org.http4k.routing.mcpHttpStreaming
import org.http4k.server.Helidon
import org.http4k.server.asServer

object McpAppExample {
    @JvmStatic
    fun main(args: Array<String>) {
        // Create an MCP App that renders a dashboard UI
        val dashboardApp = RenderMcpApp(
            name = "show_dashboard",
            description = "Display the analytics dashboard",
            uri = Uri.of("ui://dashboard"),
            meta = McpAppResourceMeta(
                csp = Csp(connectDomains = listOf(Domain.of("https://api.example.com"))),
                prefersBorder = true
            ),
            visibility = listOf(model, app)
        ) { request ->
            // Return HTML content based on the request
            """
            <html>
            <body>
                <h1>Analytics Dashboard</h1>
                <p>Requested: ${request.uri}</p>
            </body>
            </html>
            """.trimIndent()
        }

        // Create the MCP server with Apps extension enabled
        val server = mcpHttpStreaming(
            ServerMetaData("Dashboard App", "1.0.0").withExtensions(McpApps),
            NoMcpSecurity,
            dashboardApp
        )

        server.asServer(Helidon(3001)).start()
    }
}
