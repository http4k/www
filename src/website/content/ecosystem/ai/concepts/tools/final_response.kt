package content.ecosystem.ai.concepts.tools

import org.http4k.ai.llm.chat.ChatResponse
import org.http4k.ai.llm.model.Content
import org.http4k.ai.llm.model.Message
import org.http4k.ai.model.ModelName
import org.http4k.ai.model.ResponseId

val finalResponse = ChatResponse(
    message = Message.Assistant(
        contents = listOf(
            Content.Text("The weather in London is currently 18°C and partly cloudy.")
        )
    ),
    metadata = ChatResponse.Metadata(ResponseId.of("response_456"), ModelName.of("gpt-4"))
)
