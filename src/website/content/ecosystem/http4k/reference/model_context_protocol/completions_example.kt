package content.ecosystem.http4k.reference.model_context_protocol

import org.http4k.mcp.CompletionResponse
import org.http4k.mcp.model.Completion
import org.http4k.mcp.model.Reference
import org.http4k.mcp.server.capability.CompletionCapability
import org.http4k.routing.bind

// this function provides completion options for the "Greet" prompt, returning
// a list of all users whose names do not contain the letters already typed
fun ProvideCompletionOptionsForPrompt(): CompletionCapability {
    val reference = Reference.Prompt("Greet")

    return reference bind {
        val prefix = it.argument.value
        CompletionResponse(Completion(allUsers.filter { it.startsWith(prefix) }))
    }
}

private val allUsers = listOf("Alice", "Alex", "Albert", "Bob", "Charlie", "David")
