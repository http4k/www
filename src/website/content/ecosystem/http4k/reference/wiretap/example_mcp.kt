package content.ecosystem.http4k.reference.wiretap

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.greaterThan
import org.http4k.ai.mcp.client.McpClient
import org.http4k.ai.mcp.coerce
import org.http4k.ai.mcp.protocol.messages.McpTool
import org.http4k.core.Response
import org.http4k.core.Status.Companion.OK
import org.http4k.wiretap.junit.Intercept
import org.http4k.wiretap.junit.RenderMode
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.RegisterExtension

class McpTest {

    @RegisterExtension
    @JvmField
    val intercept = Intercept.poly(RenderMode.Always) {
        MyMcpServer(http { Response(OK) }, otel("mcp-server"))
    }

    // McpClient is injected — connects to the MCP server under test
    @Test
    fun `can list tools via MCP`(mcpClient: McpClient) {
        mcpClient.start()
        val tools = mcpClient.tools().list().coerce<List<McpTool>>()
        assertThat(tools.size, greaterThan(0))
    }
}
