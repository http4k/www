package content.tutorial.build_a_simple_mcp_app

import org.http4k.ai.mcp.model.apps.McpApps
import org.http4k.ai.mcp.protocol.ServerMetaData
import org.http4k.ai.mcp.protocol.withExtensions
import org.http4k.ai.mcp.server.security.NoMcpSecurity
import org.http4k.core.then
import org.http4k.filter.PolyFilters
import org.http4k.routing.mcp

fun RepoHealthChecker() =
    PolyFilters.CatchAll()
        .then(
            mcp(
                ServerMetaData("Repo Health Checker", "0.0.0").withExtensions(McpApps),
                NoMcpSecurity,
                RepoHealthCheckerApp(),
            )
        )
