package content.ecosystem.http4k.reference.mcp

import org.http4k.mcp.CompletionHandler
import org.http4k.mcp.CompletionRequest
import org.http4k.mcp.CompletionResponse
import org.http4k.mcp.model.Reference

// the reference of the completion
val promptReference = Reference.Prompt("Greet")

// this function provides completion options for the "Greet" prompt, returning
// a list of all users whose names do not contain the letters already typed
val completionHandler: CompletionHandler = {
    val allUsers = listOf("Alice", "Alex", "Albert", "Bob", "Charlie", "David")
    val prefix = it.argument.value

    CompletionResponse(allUsers.filter { it.startsWith(prefix) })
}


object ProvideCompletionOptionsForPrompt {
    @JvmStatic
    fun main() = println(
        // invoke/test the completion offline - just invoke it like a function
        completionHandler(
            CompletionRequest("prefix", "Al")
        )
    )
}
