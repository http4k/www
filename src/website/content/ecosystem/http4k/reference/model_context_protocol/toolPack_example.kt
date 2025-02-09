package content.ecosystem.http4k.reference.model_context_protocol

import org.http4k.mcp.server.capability.CapabilityPack

// We can compose multiple different capabilities into a single pack
fun AvengersDiaryPack(): CapabilityPack {
    return CapabilityPack(
        DiaryTool("Black Panther"),
        DiaryTool("Black Widow"),
        DiaryTool("Captain America"),
        DiaryTool("Hawkeye"),
        DiaryTool("Hulk"),
        DiaryTool("Ironman"),
        DiaryTool("Spiderman"),
        DiaryTool("Thor"),
        DiaryTool("Wanda Maximoff"),
        DiaryTool("Winter Soldier"),
    )
}
