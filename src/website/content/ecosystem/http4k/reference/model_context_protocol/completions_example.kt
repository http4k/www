package content.ecosystem.http4k.reference.model_context_protocol

import org.http4k.mcp.CompletionResponse
import org.http4k.mcp.model.Completion
import org.http4k.mcp.model.Reference
import org.http4k.mcp.server.capability.CapabilityPack
import org.http4k.routing.bind
import org.http4k.routing.compose

fun ProvideCompletionOptionsForPrompt(): CapabilityPack {
    val reference = Reference.Prompt("Greet")

    return compose(
        reference bind {
            CompletionResponse(Completion(listOf("Alice", "Bob", "Charlie", "David")))
        }
    )
}
