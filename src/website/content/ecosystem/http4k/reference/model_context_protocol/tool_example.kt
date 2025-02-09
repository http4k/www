package content.ecosystem.http4k.reference.model_context_protocol

import org.http4k.lens.localDate
import org.http4k.lens.with
import org.http4k.mcp.ToolHandler
import org.http4k.mcp.ToolRequest
import org.http4k.mcp.ToolResponse
import org.http4k.mcp.model.Content
import org.http4k.mcp.model.Tool
import java.time.LocalDate

// A tool that details a person's diary appointments.
class DiaryTool(val name: String) {

    // argument inputs for the tool
    val arg = Tool.Arg.localDate().required("date", "date in format yyyy-mm-dd")

    // the description of the tool exposed to clients
    val tool: Tool = Tool(
        "diary_for_${name.replace(" ", "_")}",
        "details $name's diary appointments. Responds with a list of appointments for the given month",
        arg,
    )

    // handles the actual call to tht tool
    val handler: ToolHandler = {
        val calendarData = mapOf(
            LocalDate.of(2025, 3, 21) to listOf(
                "08:00 - Breakfast meeting",
                "11:00 - Dentist appointment",
                "16:00 - Project review"
            )
        )

        val date = arg(it)
        val appointmentContent = calendarData[date]?.map { Content.Text("$date: $it") } ?: emptyList()

        ToolResponse.Ok(appointmentContent)
    }
}

// invoke/test the tool offline - just invoke it like a function
fun main() =
    println(
        DiaryTool("David").handler(
            ToolRequest().with(
                Tool.Arg.localDate().required("date") of LocalDate.parse("2025-03-21")
            )
        )
    )
