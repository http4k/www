package content.ecosystem.http4k.reference.model_context_protocol

import org.http4k.lens.int
import org.http4k.lens.with
import org.http4k.mcp.PromptHandler
import org.http4k.mcp.PromptRequest
import org.http4k.mcp.PromptResponse
import org.http4k.mcp.model.Content
import org.http4k.mcp.model.Message
import org.http4k.mcp.model.Prompt
import org.http4k.mcp.model.PromptName
import org.http4k.mcp.model.Role.assistant
import org.http4k.mcp.server.capability.PromptCapability
import org.http4k.routing.bind

object GreetingPrompt {
    // arguments
    val name = Prompt.Arg.required("name", "the name of the person to greet")
    val age = Prompt.Arg.int().optional("age", "the age of the person to greet")

    // the description of the prompt
    val prompt: Prompt = Prompt(PromptName.of("Greet"), "Creates a greeting message for a person", name, age)

    // handles the actual call to tht prompt
    val handler: PromptHandler = { req: PromptRequest ->
        val content = when (age(req)) {
            null -> Content.Text("Hello, ${name(req)}!")
            else -> Content.Text("Hello, ${name(req)}! How is req being ${age(req)}?")
        }
        PromptResponse(listOf(Message(assistant, content)))
    }

    // the binding of the prompt to the handler - this is added to the MCP Server
    val capability: PromptCapability = prompt bind handler
}

// invoke/test the prompt offline - just invoke it like a function
fun main() =
    println(
        GreetingPrompt.handler(
            PromptRequest().with(
                Prompt.Arg.required("name") of "David",
                Prompt.Arg.int().optional("age") of 30
            )
        )
    )
