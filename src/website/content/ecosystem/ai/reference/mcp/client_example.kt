package content.ecosystem.ai.reference.mcp

import org.http4k.ai.mcp.CompletionRequest
import org.http4k.ai.mcp.ElicitationResponse
import org.http4k.ai.mcp.PromptRequest
import org.http4k.ai.mcp.ResourceRequest
import org.http4k.ai.mcp.SamplingResponse
import org.http4k.ai.mcp.ToolRequest
import org.http4k.ai.mcp.client.http.HttpStreamingMcpClient
import org.http4k.ai.mcp.model.Content.Text
import org.http4k.ai.mcp.model.ElicitationAction.accept
import org.http4k.ai.mcp.model.McpEntity
import org.http4k.ai.mcp.model.Prompt
import org.http4k.ai.mcp.model.PromptName
import org.http4k.ai.mcp.model.Reference
import org.http4k.ai.mcp.model.Tool
import org.http4k.ai.mcp.model.localDate
import org.http4k.ai.mcp.protocol.Version
import org.http4k.ai.model.ModelName
import org.http4k.ai.model.Role
import org.http4k.ai.model.ToolName
import org.http4k.client.JavaHttpClient
import org.http4k.core.BodyMode
import org.http4k.core.Uri
import org.http4k.lens.int
import org.http4k.lens.with
import java.time.LocalDate

fun main() {
    val client = HttpStreamingMcpClient(
        McpEntity.of("http4k MCP Client"), Version.of("1.0.0"),
        Uri.of("http://localhost:3001/mcp"),
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
                Reference.Prompt("Greet"),
                CompletionRequest("prefix", "Al")
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

    client.sampling().onSampled {
        println(">>> Sampled: $it")
        sequenceOf(SamplingResponse(ModelName.of("gpt-4"), Role.Assistant, Text("Sampled: $it")))
    }

    client.elicitations().onElicitation {
        println(">>> Elicitation: $it")
        ElicitationResponse(accept).with(userName of "David", userAge of 30)
    }

    client.close()
}
