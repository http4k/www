package content.ecosystem.http4k.reference.model_context_protocol

import org.http4k.lens.int
import org.http4k.mcp.PromptResponse
import org.http4k.mcp.model.Content
import org.http4k.mcp.model.Message
import org.http4k.mcp.model.Prompt
import org.http4k.mcp.model.PromptName
import org.http4k.mcp.model.Role.assistant
import org.http4k.mcp.server.capability.PromptCapability
import org.http4k.routing.bind

fun GreetingPrompt(): PromptCapability {
    val name = Prompt.Arg.required("name", "the name of the person to greet")
    val age = Prompt.Arg.int().optional("age", "the age of the person to greet")
    val prompt = Prompt(PromptName.of("Greet"), "Creates a greeting message for a person", name, age)

    return prompt bind {
        val content = when (age(it)) {
            null -> Content.Text("Hello, ${name(it)}!")
            else -> Content.Text("Hello, ${name(it)}! How is it being ${age(it)}?")
        }
        PromptResponse(listOf(Message(assistant, content)))
    }
}
