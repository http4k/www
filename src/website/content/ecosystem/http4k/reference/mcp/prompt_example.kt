package content.ecosystem.http4k.reference.mcp

import org.http4k.ai.model.Role.Companion.Assistant
import org.http4k.lens.int
import org.http4k.lens.with
import org.http4k.ai.mcp.PromptHandler
import org.http4k.ai.mcp.PromptRequest
import org.http4k.ai.mcp.PromptResponse
import org.http4k.ai.mcp.model.Content
import org.http4k.ai.mcp.model.Message
import org.http4k.ai.mcp.model.Prompt
import org.http4k.ai.mcp.model.PromptName

// argument lenses for the prompt
val name = Prompt.Arg.required("name", "the name of the person to greet")
val age = Prompt.Arg.int().optional("age", "the age of the person to greet")


// the description of the prompt
val prompt: Prompt = Prompt(PromptName.of("Greet"), "Creates a greeting message for a person", name, age)

// handles the actual call to tht prompt
val greetingPromptHandler: PromptHandler = { req: PromptRequest ->
    val content = when (age(req)) {
        null -> Content.Text("Hello, ${name(req)}!")
        else -> Content.Text("Hello, ${name(req)}! How is req being ${age(req)}?")
    }
    PromptResponse(listOf(Message(Assistant, content)))
}


object GreetPersonPrompt {
    @JvmStatic
    fun main() = println(
        // invoke/test the prompt offline - just invoke it like a function
        greetingPromptHandler(
            PromptRequest().with(
                name of "David",
                age of 30
            )
        )
    )
}
