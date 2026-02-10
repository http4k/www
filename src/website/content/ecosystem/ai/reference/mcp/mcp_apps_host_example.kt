package content.ecosystem.ai.reference.mcp

import org.http4k.ai.mcp.apps.McpAppsHost
import org.http4k.ai.mcp.model.apps.McpApps
import org.http4k.ai.mcp.protocol.ServerMetaData
import org.http4k.ai.mcp.protocol.withExtensions
import org.http4k.ai.mcp.server.capability.extension.RenderMcpApp
import org.http4k.ai.mcp.server.security.NoMcpSecurity
import org.http4k.ai.mcp.testing.McpClientFactory
import org.http4k.core.Uri
import org.http4k.routing.mcpHttpStreaming
import org.http4k.server.Helidon
import org.http4k.server.asServer

object McpAppsHostExample {
    @JvmStatic
    fun main(args: Array<String>) {
        // Create MCP app servers
        val dashboardApp = mcpHttpStreaming(
            ServerMetaData("Dashboard", "1.0.0").withExtensions(McpApps),
            NoMcpSecurity,
            RenderMcpApp("show_dashboard", "Show dashboard", Uri.of("ui://dash")) {
                "<h1>Dashboard</h1>"
            }
        )

        val settingsApp = mcpHttpStreaming(
            ServerMetaData("Settings", "1.0.0").withExtensions(McpApps),
            NoMcpSecurity,
            RenderMcpApp("show_settings", "Show settings", Uri.of("ui://settings")) {
                "<h1>Settings</h1>"
            }
        )

        // Create host with multiple apps
        // Use McpClientFactory.Test for in-memory testing
        // Use McpClientFactory.Http for remote servers
        val host = McpAppsHost(
            McpClientFactory.Test(dashboardApp),
            McpClientFactory.Test(settingsApp)
        )

        // Start the host server - provides endpoints for listing and accessing apps
        host.asServer(Helidon(8099)).start()

        // The host provides these endpoints:
        // GET /               - List all available MCP apps
        // GET /api/resources  - Read UI resource content (?serverId=...&uri=...)
        // POST /api/tools/call - Call a tool from an MCP app
    }
}
