package content.ecosystem.http4k.reference.model_context_protocol

import org.http4k.lens.localDate
import org.http4k.mcp.ToolResponse
import org.http4k.mcp.model.Content
import org.http4k.mcp.model.Tool
import org.http4k.mcp.server.capability.ToolCapability
import org.http4k.routing.bind
import java.time.LocalDate

// A tool that details a person's diary appointments.
fun DiaryTool(name: String): ToolCapability {
    val arg = Tool.Arg.localDate().required("date", "date in format yyyy-mm-dd")

    val tool = Tool(
        "diary_for_${name.replace(" ", "_")}",
        "details $name's diary appointments. Responds with a list of appointments for the given month",
        arg,
    )
    return tool bind {
        val date = arg(it)
        val appointmentContent = calendar[date]?.map { Content.Text("$date: $it") } ?: emptyList()

        ToolResponse.Ok(appointmentContent)
    }
}

// test data
private val calendar = mapOf(
    LocalDate.of(2025, 3, 21) to listOf(
        "08:00 - Breakfast meeting",
        "11:00 - Dentist appointment",
        "16:00 - Project review",
    )
)
