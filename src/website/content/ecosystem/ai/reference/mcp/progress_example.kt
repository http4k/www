package content.ecosystem.ai.reference.mcp

import org.http4k.ai.mcp.CompletionHandler
import org.http4k.ai.mcp.CompletionRequest
import org.http4k.ai.mcp.CompletionResponse

val progress: CompletionHandler = { req ->
    req.client.progress(50, 100.0, "half way done")
    val allUsers = listOf("Alice", "Alex", "Albert", "Bob", "Charlie", "David")
    val prefix = req.argument.value

    CompletionResponse(allUsers.filter { it.startsWith(prefix) })
}
