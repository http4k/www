package content.ecosystem.ai.reference.mcp

import dev.forkhandles.result4k.map
import dev.forkhandles.result4k.recover
import org.http4k.ai.mcp.ElicitationRequest
import org.http4k.ai.mcp.ElicitationResponse
import org.http4k.ai.mcp.ToolHandler
import org.http4k.ai.mcp.ToolResponse.Error
import org.http4k.ai.mcp.ToolResponse.Ok
import org.http4k.ai.mcp.model.Elicitation
import org.http4k.ai.mcp.model.Elicitation.Metadata.string.MaxLength
import org.http4k.ai.mcp.model.Elicitation.Metadata.string.MinLength
import org.http4k.ai.mcp.model.ElicitationAction.accept
import org.http4k.ai.mcp.model.int
import org.http4k.ai.mcp.model.string

val userName =
    Elicitation.string().required("name", "What is your name?", "The user's name", MinLength(1), MaxLength(10))
val userAge =
    Elicitation.int().required("age", "How old are you?", "The user's age", Elicitation.Metadata.integer.Min(18))

val greetingToolWithElicitation: ToolHandler = { req ->
    val request = ElicitationRequest.Form("Please fill in your details", userName, userAge)

    // at this point, the client will render
    req.client.elicit(request)
        .map {
            when (it) {
                is ElicitationResponse.Ok -> when (it.action) {
                    accept -> Ok("hello ${(userAge(it))}, when you are twice your age you will be ${2 * userAge(it)}!")
                    else -> Ok("hello stranger!")
                }

                is ElicitationResponse.Task -> error("not supported in this example")
            }
        }
        .recover { Error("error: $it") }
}
