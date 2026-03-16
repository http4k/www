package content.tutorial.build_a_simple_mcp_app

import org.http4k.ai.mcp.ToolResponse.Ok
import org.http4k.ai.mcp.model.Content.Text
import org.http4k.ai.mcp.model.Meta
import org.http4k.ai.mcp.model.Tool
import org.http4k.ai.mcp.model.apps.McpAppMeta
import org.http4k.ai.mcp.model.apps.McpAppVisibility.app
import org.http4k.ai.mcp.model.enum
import org.http4k.ai.mcp.model.string
import org.http4k.ai.mcp.server.capability.ToolCapability
import org.http4k.ai.mcp.util.auto
import org.http4k.format.Moshi
import org.http4k.lens.MetaKey
import org.http4k.routing.bind

fun SaveHealthSelectionTool(selections: MutableMap<String, RepoHealthSelection>): ToolCapability {
    val repo = Tool.Arg.string().required("repo", "The GitHub repo in owner/name format")
    val metrics = Tool.Arg.string().required("metrics", "JSON-encoded map of selected metric names to values")
    val focus = Tool.Arg.enum<AnalysisFocus>().required("focus", "Analysis focus: contributor, dependency, or benchmarking")

    return Tool(
        name = "save_health_selection",
        description = "Save the selected repo health metrics for Claude to analyse",
        repo, metrics, focus,
        meta = Meta(MetaKey.auto(McpAppMeta).toLens() of McpAppMeta(visibility = listOf(app)))
    ) bind {
        val repoName = repo(it)
        val metricsMap = Moshi.asA<Map<String, String>>(metrics(it))
        val focusValue = focus(it)

        selections[repoName] = RepoHealthSelection(repoName, metricsMap, focusValue)

        Ok(listOf(Text("Saved ${metricsMap.size} metrics for $repoName with focus: $focusValue")))
    }
}
