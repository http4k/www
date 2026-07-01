package content.ecosystem.pro.reference.mcp

import org.http4k.ai.mcp.server.capability.CapabilityPack
import org.http4k.routing.bind

fun SetOfCapabilities() = CapabilityPack(
    _root_ide_package_.content.ecosystem.pro.reference.mcp.toolDefinitionFor("David") bind _root_ide_package_.content.ecosystem.pro.reference.mcp.diaryToolHandler,
    _root_ide_package_.content.ecosystem.pro.reference.mcp.promptReference bind _root_ide_package_.content.ecosystem.pro.reference.mcp.completionHandler,
    _root_ide_package_.content.ecosystem.pro.reference.mcp.websiteResource bind _root_ide_package_.content.ecosystem.pro.reference.mcp.getLinksResourceHandler,
    _root_ide_package_.content.ecosystem.pro.reference.mcp.prompt bind _root_ide_package_.content.ecosystem.pro.reference.mcp.greetingPromptHandler
)
