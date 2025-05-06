package content.news.http4k_mcp_has_landed

import org.http4k.mcp.model.Tool
import org.http4k.mcp.model.int
import org.http4k.mcp.model.localDate
import org.http4k.mcp.model.string

val cityArg = Tool.Arg.string().required("city", "City name")
val temperatureArg = Tool.Arg.int().required("temperature", "Temperature in Celsius")
val dateArg = Tool.Arg.localDate().required("date", "Date in yyyy-MM-dd format")
