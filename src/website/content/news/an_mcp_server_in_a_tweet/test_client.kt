package content.news.an_mcp_server_in_a_tweet

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import dev.forkhandles.result4k.onFailure
import org.http4k.ai.mcp.ToolRequest
import org.http4k.ai.mcp.ToolResponse.Ok
import org.http4k.ai.mcp.model.Content.Text
import org.http4k.ai.mcp.model.Tool
import org.http4k.ai.mcp.model.string
import org.http4k.ai.mcp.protocol.ServerMetaData
import org.http4k.ai.mcp.server.security.NoMcpSecurity
import org.http4k.ai.mcp.testing.testMcpClient
import org.http4k.ai.model.ToolName
import org.http4k.lens.with
import org.http4k.routing.bind
import org.http4k.routing.mcp
import org.junit.jupiter.api.Test

class McpServerTest {

    private val name = Tool.Arg.string().required("name")

    private val mcpServer = mcp(
        ServerMetaData("Greeter", "1.0.0"), NoMcpSecurity,
        Tool("greet", "Say hello", name) bind { Ok(Text("Hello, ${name(it)}!")) }
    )

    @Test
    fun `can call a tool on the server`() {
        val client = mcpServer.testMcpClient().apply { start() }

        val tools = client.tools().list()
            .onFailure { error("") }

        assertThat(tools.size, equalTo(1))

        val result = client.tools()
            .call(ToolName.of("greet"), ToolRequest().with(name of "Bob"))
            .onFailure { error("") } as Ok

        assertThat((result.content?.firstOrNull() as Text).text, equalTo("Hello, Bob!"))
    }
}
