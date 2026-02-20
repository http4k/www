package content.tutorial.build_a_simple_mcp_app

import org.http4k.ai.mcp.ToolResponse.Ok
import org.http4k.ai.mcp.model.Content.Text
import org.http4k.ai.mcp.model.Meta
import org.http4k.ai.mcp.model.Tool
import org.http4k.ai.mcp.model.apps.McpAppMeta
import org.http4k.ai.mcp.model.apps.McpAppVisibility.model
import org.http4k.routing.bind

fun GetReleaseSelectionTool(get: () -> ReleaseSelection?) = Tool(
    name = "get_release_selection",
    description = "Get the current release selection",
    meta = Meta(ui = McpAppMeta(visibility = listOf(model)))
) bind {
    val text = when (val selection = get()) {
        null -> "No release selection saved yet."
        else -> "Repo: ${selection.repo}\nSelected issues: ${
            selection.issues.joinToString(", ") { "#$it" }
        }"
    }
    Ok(listOf(Text(text)))
}
