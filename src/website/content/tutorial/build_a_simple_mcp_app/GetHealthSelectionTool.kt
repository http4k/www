package content.tutorial.build_a_simple_mcp_app

import org.http4k.ai.mcp.ToolResponse.Ok
import org.http4k.ai.mcp.model.Content.Text
import org.http4k.ai.mcp.model.Meta
import org.http4k.ai.mcp.model.Tool
import org.http4k.ai.mcp.model.apps.McpAppMeta
import org.http4k.ai.mcp.model.apps.McpAppVisibility.model
import org.http4k.ai.mcp.model.string
import org.http4k.ai.mcp.server.capability.ToolCapability
import org.http4k.routing.bind

fun GetHealthSelectionTool(selections: MutableMap<String, RepoHealthSelection>): ToolCapability {
    val repo = Tool.Arg.string().required("repo", "The GitHub repo in owner/name format")

    return Tool(
        name = "get_health_selection",
        description = "Get the repo health metrics selected by the user.",
        repo,
        meta = Meta(ui = McpAppMeta(visibility = listOf(model)))
    ) bind { args ->
        val repoName = repo(args)
        val current = selections[repoName]
        if (current == null) {
            Ok(listOf(Text("No health metrics have been saved for $repoName. Ask the user to open the repo health checker and select some metrics.")))
        } else {
            val metricsText = current.metrics.entries.joinToString("\n") { (name, value) -> "- $name: $value" }
            Ok(listOf(Text("Repo: ${current.repo}\nFocus: ${current.focus}\n\nSelected metrics:\n$metricsText")))
        }
    }
}
