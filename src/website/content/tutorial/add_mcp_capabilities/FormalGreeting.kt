package content.tutorial.add_mcp_capabilities

import org.http4k.ai.mcp.PromptResponse
import org.http4k.ai.mcp.model.Prompt
import org.http4k.ai.model.Role
import org.http4k.lens.string
import org.http4k.routing.bind

val personName = Prompt.Arg.string().required("name", "Person to greet")

val greetingPrompt = Prompt("formal_greeting", "Generate a formal greeting", personName) bind {
    PromptResponse.Ok(
        Role.User,
        "Write a formal greeting for ${personName(it)}. " +
            "Read the greeting://guidelines resource first, then call the greet tool."
    )
}
