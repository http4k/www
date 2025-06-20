package content.ecosystem.ai.reference.mcp

import org.http4k.ai.mcp.model.McpEntity
import org.http4k.ai.mcp.protocol.ServerMetaData
import org.http4k.ai.mcp.protocol.ServerProtocolCapability.ToolsChanged
import org.http4k.ai.mcp.protocol.Version
import org.http4k.ai.mcp.server.security.OAuthMcpSecurity
import org.http4k.core.Uri
import org.http4k.routing.bind
import org.http4k.routing.mcpHttpStreaming
import org.http4k.server.Helidon
import org.http4k.server.asServer

fun main() {
    // call the correct protocol method here - there are 5 to choose from!
    val mcpServer = mcpHttpStreaming(
        // give the server an identity
        ServerMetaData(McpEntity.of("http4k MCP Server"), Version.of("1.0.0"), ToolsChanged),

        // insert a security implementation
        OAuthMcpSecurity(Uri.of("https://oauth-server"), Uri.of("https://mcp-server/mcp")) { it == "my_oauth_token" },

        // bind server capabilities here ...
        toolDefinitionFor("David") bind diaryToolHandler,
        promptReference bind completionHandler,
        websiteResource bind getLinksResourceHandler,
        prompt bind greetingPromptHandler
    )

    // simply start it up!
    mcpServer.asServer(Helidon(3002)).start()
}
