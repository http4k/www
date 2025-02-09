package content.ecosystem.http4k.reference.model_context_protocol

import org.http4k.client.JavaHttpClient
import org.http4k.core.BodyMode
import org.http4k.core.Method.GET
import org.http4k.core.Request
import org.http4k.core.Uri
import org.http4k.lens.int
import org.http4k.lens.localDate
import org.http4k.lens.with
import org.http4k.mcp.CompletionRequest
import org.http4k.mcp.PromptRequest
import org.http4k.mcp.ResourceRequest
import org.http4k.mcp.SamplingRequest
import org.http4k.mcp.ToolRequest
import org.http4k.mcp.client.SseMcpClient
import org.http4k.mcp.model.CompletionArgument
import org.http4k.mcp.model.Content
import org.http4k.mcp.model.MaxTokens
import org.http4k.mcp.model.McpEntity
import org.http4k.mcp.model.Message
import org.http4k.mcp.model.ModelIdentifier
import org.http4k.mcp.model.Prompt
import org.http4k.mcp.model.PromptName
import org.http4k.mcp.model.Reference
import org.http4k.mcp.model.Role.assistant
import org.http4k.mcp.model.Role.user
import org.http4k.mcp.model.Tool
import org.http4k.mcp.model.ToolName
import org.http4k.mcp.protocol.ClientCapabilities
import org.http4k.mcp.protocol.Version
import java.time.LocalDate

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
                ToolRequest().with(
                    Tool.Arg.localDate().required("date") of LocalDate.parse("2025-03-21")
                )
            )
    )

    println(
        ">>> Prompt list\n" +
            client.prompts().list()
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
        ">>> Completions\n" +
            client.completions().complete(
                CompletionRequest(Reference.Prompt("Greet"), CompletionArgument("prefix", "Al"))
            )
    )

    println(
        ">>> Resource list\n" +
            client.resources().list()
    )

    println(
        ">>> Resource reading\n" +
            client.resources().read(
                ResourceRequest(Uri.of("https://http4k.org"))
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
