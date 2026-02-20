package content.tutorial.build_a_simple_mcp_app

import org.http4k.ai.mcp.server.capability.CapabilityPack

fun GitHubReleasePlannerApp(): CapabilityPack {
    var releaseSelection: ReleaseSelection? = null

    return CapabilityPack(
        RenderReleasePlanner(),
        SaveReleaseSelectionTool { releaseSelection = it },
        GetReleaseSelectionTool { releaseSelection }
    )
}
