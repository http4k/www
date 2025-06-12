package content.ecosystem.ai.concepts.tools

import org.http4k.ai.model.ToolName

data class LLMTool(
    val name: ToolName,
    val description: String,
    val inputSchema: Map<String, Any> = emptyMap(),
    val outputSchema: Map<String, Any>? = null
)
