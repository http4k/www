package content.tutorial.build_a_simple_mcp_app

import org.http4k.ai.mcp.model.Domain
import org.http4k.ai.mcp.model.apps.Csp
import org.http4k.ai.mcp.model.apps.McpAppResourceMeta
import org.http4k.ai.mcp.server.capability.ServerCapability
import org.http4k.ai.mcp.server.capability.extension.RenderMcpApp
import org.http4k.core.Uri
import org.http4k.template.HandlebarsTemplates

fun RenderReleasePlanner(): ServerCapability {
    val templates = HandlebarsTemplates().CachingClasspath()

    return RenderMcpApp(
        name = "show_release_planner",
        description = "Display the GitHub Release Planner UI",
        uri = Uri.of("ui://release-planner"),
        meta = McpAppResourceMeta(
            csp = Csp(
                resourceDomains = listOf(Domain.of("https://unpkg.com"), Domain.of("data:")),
                connectDomains = listOf(
                    Domain.of("https://unpkg.com"),
                    Domain.of("https://api.github.com"),
                    Domain.of("https://*.trycloudflare.com")
                )
            )
        )
    ) { templates(ReleasePlannerUI()) }
}
