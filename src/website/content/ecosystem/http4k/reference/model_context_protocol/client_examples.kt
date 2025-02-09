package content.ecosystem.http4k.reference.model_context_protocol

import org.http4k.client.JavaHttpClient
import org.http4k.core.BodyMode
import org.http4k.core.Method.GET
import org.http4k.core.Request
import org.http4k.lens.int
import org.http4k.lens.with
import org.http4k.mcp.PromptRequest
import org.http4k.mcp.SamplingRequest
import org.http4k.mcp.ToolRequest
import org.http4k.mcp.client.SseMcpClient
import org.http4k.mcp.model.Content
import org.http4k.mcp.model.MaxTokens
import org.http4k.mcp.model.McpEntity
import org.http4k.mcp.model.Message
import org.http4k.mcp.model.ModelIdentifier
import org.http4k.mcp.model.Prompt
import org.http4k.mcp.model.PromptName
import org.http4k.mcp.model.Role.assistant
import org.http4k.mcp.model.Role.user
import org.http4k.mcp.model.ToolName
import org.http4k.mcp.protocol.ClientCapabilities
import org.http4k.mcp.protocol.Version

fun main() {
    val client = SseMcpClient(
        McpEntity.of("http4k MCP Client"), Version.of("1.0.0"),
        ClientCapabilities(),
        Request(GET, "http://localhost:3001/sse"),
        JavaHttpClient(responseBodyMode = BodyMode.Stream)
    )

    println(
        ">>> Server handshake\n" +
            client.start()
    )

    println(
        ">>> Tool list\n" +
            client.tools().list()
    )

    println(
        ">>> Tool calling\n" +
            client.tools().call(
                ToolName.of("diary_for_David"),
                ToolRequest(mapOf("date" to "2025-03-21"))
            )
    )

    println(
        ">>> Prompt calling\n" +
            client.prompts().get(
                PromptName.of("Greet"),
                PromptRequest().with(
                    Prompt.Arg.required("name") of "David",
                    Prompt.Arg.int().optional("age") of 30
                )
            )
    )

    println(
        ">>> Sampling\n" +
            client.sampling().sample(
                ModelIdentifier.of("my-llm"),
                SamplingRequest(
                    listOf(
                        Message(user, Content.Text("Make me a sandwich!")),
                        Message(assistant, Content.Text("You do not have the necessary authority.")),
                        Message(user, Content.Text("Sudo make me a sandwich!")),
                    ), MaxTokens.of(123123)
                )
            ).toList()
    )

    client.close()
}
