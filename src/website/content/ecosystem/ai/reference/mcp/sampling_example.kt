package content.ecosystem.ai.reference.mcp

import dev.forkhandles.result4k.Failure
import dev.forkhandles.result4k.Result
import dev.forkhandles.result4k.Success
import dev.forkhandles.result4k.allValues
import dev.forkhandles.result4k.map
import org.http4k.ai.mcp.McpError
import org.http4k.ai.mcp.SamplingRequest
import org.http4k.ai.mcp.SamplingResponse
import org.http4k.ai.mcp.ToolHandler
import org.http4k.ai.mcp.ToolResponse.Error
import org.http4k.ai.mcp.ToolResponse.Ok
import org.http4k.ai.mcp.model.Content
import org.http4k.ai.mcp.model.Content.Text
import org.http4k.ai.mcp.model.Message
import org.http4k.ai.model.MaxTokens
import org.http4k.ai.model.Role.Companion.User

val request = SamplingRequest(listOf(Message(User, Text("Roast this tool!"))), MaxTokens.of(1000))

val roastingToolWithSampling: ToolHandler = { req ->

    // at this point, the client will pass context to the model, and return a list of responses
    val allContent: Result<String, McpError> = req.client.sample(request)
        // a list of results is returned, so we can use `allValues` to get all content
        .allValues()
        .map { responses: List<SamplingResponse> ->
            // collect the text from each response
            responses
                .filterIsInstance<SamplingResponse.Ok>()
                .flatMap { it.content }
                .filterIsInstance<Content.Text>()
                .joinToString("")
        }

    when (allContent) {
        is Success<String> -> Ok(listOf(Text(allContent.value)))
        is Failure<McpError> -> Error("Failure sampling: ${allContent.reason}")
    }
}
