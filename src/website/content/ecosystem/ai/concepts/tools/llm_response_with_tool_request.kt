package content.ecosystem.ai.concepts.tools

import org.http4k.ai.llm.chat.ChatResponse
import org.http4k.ai.llm.model.Message
import org.http4k.ai.llm.tools.ToolRequest
import org.http4k.ai.model.ModelName
import org.http4k.ai.model.RequestId
import org.http4k.ai.model.ResponseId
import org.http4k.ai.model.ToolName

val llmResponseWithToolRequest = Message.Assistant(
    contents = emptyList(),
    toolRequests = listOf(
        ToolRequest(
            id = RequestId.of("call_123"),
            name = ToolName.of("get_weather"),
            arguments = mapOf("location" to "London")
        )
    )
)
val toolResponse = ChatResponse(
    message = llmResponseWithToolRequest,
    metadata = ChatResponse.Metadata(ResponseId.of("response_456"), ModelName.of("gpt-4"))
)
