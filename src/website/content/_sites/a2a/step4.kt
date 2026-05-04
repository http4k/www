package content._sites.a2a

import dev.forkhandles.result4k.valueOrNull
import org.http4k.ai.a2a.client.HttpA2AClient
import org.http4k.ai.a2a.model.A2ARole
import org.http4k.ai.a2a.model.Message
import org.http4k.ai.a2a.model.MessageId
import org.http4k.ai.a2a.model.Part
import org.http4k.core.Uri

val client = HttpA2AClient(Uri.of("http://localhost:8080"))
val card = client.agentCard().valueOrNull()!!
val response = client.message(
    Message(MessageId.random(), A2ARole.ROLE_USER, listOf(Part.Text("Find pasta recipes")))
)
