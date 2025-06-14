package content.ecosystem.ai.reference.anthropic

import org.http4k.ai.llm.chat.AnthropicAI
import org.http4k.ai.llm.chat.Chat
import org.http4k.ai.llm.chat.ChatRequest
import org.http4k.ai.llm.model.Message
import org.http4k.ai.llm.model.ModelParams
import org.http4k.ai.llm.tools.LLMTool
import org.http4k.client.JavaHttpClient
import org.http4k.connect.anthropic.AnthropicIApiKey
import org.http4k.connect.anthropic.AnthropicModels

val llm = Chat.AnthropicAI(AnthropicIApiKey.of("api-key"), JavaHttpClient())

val request = ChatRequest(
    Message.User("What's the weather like in London?"),
    params = ModelParams(
        modelName = AnthropicModels.Claude_Sonnet_3_7,
        tools = listOf(
            LLMTool(
                name = "get_weather",
                description = "Get current weather for a location",
                inputSchema = mapOf(
                    "type" to "object",
                    "properties" to mapOf(
                        "location" to mapOf("type" to "string")
                    ),
                    "required" to listOf("location")
                )
            )
        )
    )
)

val response = llm(request)
