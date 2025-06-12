package content.ecosystem.ai.concepts.tools

import org.http4k.ai.llm.chat.ChatRequest
import org.http4k.ai.llm.model.Message
import org.http4k.ai.model.RequestId
import org.http4k.ai.model.ToolName

val followUpRequest = ChatRequest(
    messages = listOf(
        userRequest,
        llmResponseWithToolRequest,
        Message.ToolResult(
            id = RequestId.of("call_123"),
            tool = ToolName.of("get_weather"),
            text = "London: 18°C, partly cloudy"
        )
    ),
    params = modelParameters
)
