package content.ecosystem.ai.reference.mcp

import org.http4k.ai.mcp.server.capability.CapabilityPack
import org.http4k.routing.bind

fun SetOfCapabilities() = CapabilityPack(
    toolDefinitionFor("David") bind diaryToolHandler,
    promptReference bind completionHandler,
    websiteResource bind getLinksResourceHandler,
    prompt bind greetingPromptHandler
)
