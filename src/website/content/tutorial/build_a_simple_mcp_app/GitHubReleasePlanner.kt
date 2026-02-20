package content.tutorial.build_a_simple_mcp_app

import org.http4k.ai.mcp.model.apps.McpApps
import org.http4k.ai.mcp.protocol.ServerMetaData
import org.http4k.ai.mcp.protocol.withExtensions
import org.http4k.ai.mcp.server.security.NoMcpSecurity
import org.http4k.routing.mcpHttpStreaming

fun GithubReleasePlanner() =
    mcpHttpStreaming(
        ServerMetaData("GitHub Release Planner", "0.0.0").withExtensions(McpApps),
        NoMcpSecurity,
        GitHubReleasePlannerApp(),
    )
