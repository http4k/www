package content.tutorial.build_a_simple_mcp_app

import org.http4k.ai.mcp.model.Domain
import org.http4k.ai.mcp.model.apps.Csp
import org.http4k.ai.mcp.model.apps.McpAppResourceMeta
import org.http4k.ai.mcp.server.capability.CapabilityPack
import org.http4k.ai.mcp.server.capability.extension.RenderMcpApp
import org.http4k.core.Uri
import org.http4k.template.TemplateRenderer

fun HealthCheckerUi(templates: TemplateRenderer): CapabilityPack = RenderMcpApp(
    name = "show_repo_health_checker",
    description = "Display the repo health checker UI",
    uiUri = Uri.of("ui://repo-health-checker"),
    meta = McpAppResourceMeta(
        csp = Csp(
            connectDomains = listOf(Domain.of("https://unpkg.com"), Domain.of("https://api.github.com")),
            resourceDomains = listOf(Domain.of("https://unpkg.com"))
        )
    )
) { templates(HealthChecker()) }
