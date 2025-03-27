package content.news.http4k_mcp_has_landed

import org.http4k.lens.int
import org.http4k.lens.localDate
import org.http4k.mcp.model.Tool

val cityArg = Tool.Arg.required("city", "City name")
val temperatureArg = Tool.Arg.int().required("temperature", "Temperature in Celsius")
val dateArg = Tool.Arg.localDate().required("date", "Date in yyyy-MM-dd format")
