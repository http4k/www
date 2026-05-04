package content.ecosystem.http4k.reference.wiretap

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.containsSubstring
import com.natpryce.hamkrest.greaterThan
import org.http4k.ai.mcp.ToolResponse
import org.http4k.ai.mcp.client.McpClient
import org.http4k.ai.mcp.coerce
import org.http4k.ai.mcp.model.Tool
import org.http4k.ai.mcp.protocol.messages.McpTool
import org.http4k.ai.mcp.server.capability.capabilities
import org.http4k.ai.model.ToolName
import org.http4k.routing.bind
import org.http4k.wiretap.junit.Intercept
import org.http4k.wiretap.junit.RenderMode.Always
import org.http4k.wiretap.junit.mcpCapabilities
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.RegisterExtension

class McpCapabilitiesTest {

    @RegisterExtension
    @JvmField
    val intercept = Intercept.mcpCapabilities(Always) {
        capabilities(
            Tool("greet", "Say hello") bind { ToolResponse.Ok("hello!") }
        )
    }

    @Test
    fun `can call MCP tools`(mcpClient: McpClient) {
        mcpClient.start()
        assertThat(mcpClient.tools().list().coerce<List<McpTool>>().size, greaterThan(0))
        assertThat(
            mcpClient.tools().call(ToolName.of("greet")).coerce<ToolResponse.Ok>().content?.first().toString(),
            containsSubstring("hello")
        )
    }
}
