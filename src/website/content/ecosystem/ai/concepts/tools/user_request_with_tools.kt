package content.ecosystem.ai.concepts.tools

import org.http4k.ai.llm.chat.ChatRequest
import org.http4k.ai.llm.model.Message
import org.http4k.ai.llm.model.ModelParams
import org.http4k.ai.llm.tools.LLMTool
import org.http4k.ai.model.ModelName

val weatherTool = LLMTool(
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

val userRequest = Message.User("What's the weather like in London?")

val modelParameters = ModelParams(modelName = ModelName.of("gpt-4"), tools = listOf(weatherTool))

val request = ChatRequest(userRequest, params = modelParameters)
