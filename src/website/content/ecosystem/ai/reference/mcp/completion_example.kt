package content.ecosystem.ai.reference.mcp

import org.http4k.ai.mcp.CompletionFilter
import org.http4k.ai.mcp.CompletionHandler
import org.http4k.ai.mcp.CompletionRequest
import org.http4k.ai.mcp.CompletionResponse
import org.http4k.ai.mcp.model.Reference
import org.http4k.ai.mcp.then

// the reference of the completion
val promptReference = Reference.Prompt("Greet")

// this function provides completion options for the "Greet" prompt, returning
// a list of all users whose names do not contain the letters already typed
val completionHandler: CompletionHandler = {
    val allUsers = listOf("Alice", "Alex", "Albert", "Bob", "Charlie", "David")
    val prefix = it.argument.value

    CompletionResponse(allUsers.filter { it.startsWith(prefix) })
}

// use a Filter to perform logging/tracing/metrics
val loggingCompletion = CompletionFilter { next ->
    {
        println("Called with: $it")
        val response = next(it)
        println("Result was: $it")
        response
    }
}.then(completionHandler)


object ProvideCompletionOptionsForPrompt {
    @JvmStatic
    fun main() = println(
        // invoke/test the completion offline - just invoke it like a function
        loggingCompletion(
            CompletionRequest("prefix", "Al")
        )
    )
}
