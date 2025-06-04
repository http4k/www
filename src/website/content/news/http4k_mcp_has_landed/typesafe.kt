package content.news.http4k_mcp_has_landed

import org.http4k.ai.mcp.model.Tool
import org.http4k.ai.mcp.model.int
import org.http4k.ai.mcp.model.localDate
import org.http4k.ai.mcp.model.string
import org.http4k.ai.mcp.util.McpJson.auto

val cityArg = Tool.Arg.string().required("city", "City name")
val temperatureArg = Tool.Arg.int().required("temperature", "Temperature in Celsius")
val dateArg = Tool.Arg.localDate().required("date", "Date in yyyy-MM-dd format")

data class Human(val name: String, val age: Int)

val complexArg = Tool.Arg.auto(Human("David", 21)).required("human dev")
