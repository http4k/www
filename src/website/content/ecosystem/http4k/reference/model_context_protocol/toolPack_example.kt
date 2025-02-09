package content.ecosystem.http4k.reference.model_context_protocol

import org.http4k.mcp.server.capability.CapabilityPack

// We can compose multiple different capabilities into a single pack
fun AvengersDiaryPack(): CapabilityPack {
    return CapabilityPack(
        DiaryTool("Black Panther").capability,
        DiaryTool("Black Widow").capability,
        DiaryTool("Captain America").capability,
        DiaryTool("Hawkeye").capability,
        DiaryTool("Hulk").capability,
        DiaryTool("Ironman").capability,
        DiaryTool("Spiderman").capability,
        DiaryTool("Thor").capability,
        DiaryTool("Wanda Maximoff").capability,
        DiaryTool("Winter Soldier").capability,
    )
}
