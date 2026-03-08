package content.tutorial.build_a_simple_mcp_app

import org.http4k.ai.mcp.server.capability.CapabilityPack
import org.http4k.template.HandlebarsTemplates

fun RepoHealthCheckerApp(): CapabilityPack {
    val templates = HandlebarsTemplates().CachingClasspath()
    val selections = mutableMapOf<String, RepoHealthSelection>()

    return CapabilityPack(
        HealthCheckerUi(templates),
        SaveHealthSelectionTool(selections),
        GetHealthSelectionTool(selections),
        AnalyseRepoHealth()
    )
}
