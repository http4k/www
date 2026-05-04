package content._sites.a2a

import org.http4k.ai.a2a.MessageHandler
import org.http4k.ai.a2a.model.A2ARole.ROLE_AGENT
import org.http4k.ai.a2a.model.Message
import org.http4k.ai.a2a.model.MessageId
import org.http4k.ai.a2a.model.Part


val handler: MessageHandler = {
    Message(
        messageId = MessageId.random(),
        role = ROLE_AGENT,
        parts = listOf(Part.Text("Found 3 recipes!"))
    )
}
