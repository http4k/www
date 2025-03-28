package content.ecosystem.http4k.reference.mcp

import org.http4k.mcp.model.McpEntity
import org.http4k.mcp.protocol.ProtocolCapability.ToolsChanged
import org.http4k.mcp.protocol.ServerMetaData
import org.http4k.mcp.protocol.Version
import org.http4k.routing.bind
import org.http4k.routing.mcpHttpStreaming
import org.http4k.server.Helidon
import org.http4k.server.asServer

fun main() {
    // call the correct protocol method here
    val mcpServer = mcpHttpStreaming(
        ServerMetaData(McpEntity.of("http4k MCP Server"), Version.of("1.0.0"), ToolsChanged),
        // bind server capabilities here ...
        toolDefinitionFor("David") bind diaryToolHandler,
        promptReference bind completionHandler,
        websiteResource bind getLinksResourceHandler,
        prompt bind greetingPromptHandler
    )

    // simply start it up!
    mcpServer.asServer(Helidon(3002)).start()
}
