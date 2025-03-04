//package content.ecosystem.http4k.reference.model_context_protocol
//
//import org.http4k.mcp.CompletionHandler
//import org.http4k.mcp.CompletionRequest
//import org.http4k.mcp.CompletionResponse
//import org.http4k.mcp.model.Completion
//import org.http4k.mcp.model.CompletionArgument
//import org.http4k.mcp.model.Reference
//
//object ProvideCompletionOptionsForPrompt {
//    // the reference of the completion
//    val reference = Reference.Prompt("Greet")
//
//    // this function provides completion options for the "Greet" prompt, returning
//    // a list of all users whose names do not contain the letters already typed
//    val handler: CompletionHandler = {
//        val allUsers = listOf("Alice", "Alex", "Albert", "Bob", "Charlie", "David")
//        val prefix = it.argument.value
//
//        CompletionResponse(Completion(allUsers.filter { it.startsWith(prefix) }))
//    }
//
//}
//
//// invoke/test the completion offline - just invoke it like a function
//fun main() =
//    println(
//        ProvideCompletionOptionsForPrompt.handler(
//            CompletionRequest(Reference.Prompt("Greet"), CompletionArgument("prefix", "Al"))
//        )
//    )
