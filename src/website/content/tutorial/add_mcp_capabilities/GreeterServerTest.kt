package content.tutorial.add_mcp_capabilities

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import dev.forkhandles.result4k.onFailure
import org.http4k.ai.mcp.ToolRequest
import org.http4k.ai.mcp.ToolResponse.Ok
import org.http4k.ai.mcp.model.Content.Text
import org.http4k.ai.mcp.testing.testMcpClient
import org.http4k.ai.model.ToolName
import org.http4k.lens.with
import org.junit.jupiter.api.Test

class GreeterServerTest {

    private val client = GreeterServer().testMcpClient().apply { start() }

    @Test
    fun `lists all tools`() {
        val tools = client.tools().list().onFailure { error("") }
        assertThat(tools.size, equalTo(1))
        assertThat(tools.first().name, equalTo(ToolName.of("greet")))
    }

    @Test
    fun `calls greet tool`() {
        val result = client.tools()
            .call(ToolName.of("greet"), ToolRequest().with(name of "Bob"))
            .onFailure { error("") } as Ok

        assertThat((result.content?.firstOrNull() as Text).text, equalTo("Hello, Bob!"))
    }

    @Test
    fun `lists all resources`() {
        val resources = client.resources().list().onFailure { error("") }
        assertThat(resources.size, equalTo(1))
    }

    @Test
    fun `lists all prompts`() {
        val prompts = client.prompts().list().onFailure { error("") }
        assertThat(prompts.size, equalTo(1))
    }
}
