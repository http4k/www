package content.ecosystem.http4k.reference.mcp

import org.http4k.lens.with
import org.http4k.mcp.ToolHandler
import org.http4k.mcp.ToolRequest
import org.http4k.mcp.ToolResponse
import org.http4k.mcp.model.Content
import org.http4k.mcp.model.Tool
import org.http4k.mcp.model.localDate
import java.time.LocalDate


// argument input typesafe lenses for the tool
val toolArg = Tool.Arg.localDate().required("date", "date in format yyyy-mm-dd")

// the description of the tool exposed to clients
fun toolDefinitionFor(name: String): Tool = Tool(
    "diary_for_${name.replace(" ", "_")}",
    "details $name's diary appointments. Responds with a list of appointments for the given month",
    toolArg,
)

// handles the actual call to tht tool
val diaryToolHandler: ToolHandler = {
    val calendarData = mapOf(
        LocalDate.of(2025, 3, 21) to listOf(
            "08:00 - Breakfast meeting",
            "11:00 - Dentist appointment",
            "16:00 - Project review"
        )
    )

    val date = toolArg(it)
    val appointmentContent = calendarData[date]?.map { Content.Text("$date: $it") } ?: emptyList()

    ToolResponse.Ok(appointmentContent)
}

object DiaryTool {
    @JvmStatic
    fun main() = println(
        // invoke/test the tool offline - just invoke it like a function
        diaryToolHandler(
            ToolRequest().with(Tool.Arg.localDate().required("date") of LocalDate.parse("2025-03-21"))
        )
    )
}
