package content.ecosystem.http4k.reference.model_context_protocol

import org.http4k.filter.debug
import org.http4k.mcp.model.McpEntity
import org.http4k.mcp.protocol.ProtocolCapability.PromptsChanged
import org.http4k.mcp.protocol.ProtocolCapability.ToolsChanged
import org.http4k.mcp.protocol.ServerMetaData
import org.http4k.mcp.protocol.Version
import org.http4k.routing.mcpSse
import org.http4k.server.Helidon
import org.http4k.server.asServer

fun main() {
    // creates the MCP server with all of the capabilities
    val http4kMcp = mcpSse(
        ServerMetaData(McpEntity.of("http4k MCP Server"), Version.of("1.0.0"), PromptsChanged),
        DiaryTool("David"),
        DiaryTool("Ivan"),
        GreetingPrompt(),
        SampleFromOurLocalLlm(),
        LookupAllLinksFromWebResource(),
        ProvideCompletionOptionsForPrompt(),
    )

    http4kMcp.debug().asServer(Helidon(3001)).start()

    val avengersMcp = mcpSse(
        ServerMetaData(McpEntity.of("http4k MCP Server"), Version.of("1.0.0"), ToolsChanged),
        AvengersDiaryPack()
    )

    avengersMcp.asServer(Helidon(3002)).start()
}
