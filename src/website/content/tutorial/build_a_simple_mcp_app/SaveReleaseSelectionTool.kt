package content.tutorial.build_a_simple_mcp_app

import org.http4k.ai.mcp.ToolResponse.Ok
import org.http4k.ai.mcp.model.Content.Text
import org.http4k.ai.mcp.model.Meta
import org.http4k.ai.mcp.model.Tool
import org.http4k.ai.mcp.model.apps.McpAppMeta
import org.http4k.ai.mcp.model.apps.McpAppVisibility.app
import org.http4k.ai.mcp.model.int
import org.http4k.ai.mcp.model.string
import org.http4k.ai.mcp.server.capability.ToolCapability
import org.http4k.routing.bind

fun SaveReleaseSelectionTool(save: (ReleaseSelection) -> Unit): ToolCapability {
    val repo = Tool.Arg.string().required("repo", "GitHub repo in owner/repo format")
    val issues = Tool.Arg.int().multi.required("issues", "Comma-separated issue numbers")

    return Tool(
        name = "save_release_selection",
        description = "Save the selected issues for a release",
        repo, issues,
        meta = Meta(ui = McpAppMeta(visibility = listOf(app)))
    ) bind { args ->
        save(ReleaseSelection(repo(args), issues(args)))
        Ok(listOf(Text("Saved release selection")))
    }
}
