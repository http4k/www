package content.ecosystem.http4k.reference.mcp

import org.http4k.mcp.server.capability.CapabilityPack
import org.http4k.routing.bind

// We can compose multiple different capabilities into a single pack
fun AvengersDiaryPack(): CapabilityPack = CapabilityPack(
    toolDefinitionFor("Black Panther") bind diaryToolHandler,
    toolDefinitionFor("Black Widow") bind diaryToolHandler,
    toolDefinitionFor("Captain America") bind diaryToolHandler,
    toolDefinitionFor("Hawkeye") bind diaryToolHandler,
    toolDefinitionFor("Hulk") bind diaryToolHandler
)
