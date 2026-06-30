package content.ecosystem.pro.reference.a2a

import dev.forkhandles.result4k.valueOrNull
import org.http4k.ai.a2a.client.HttpA2AClient
import org.http4k.ai.a2a.model.A2ARole.ROLE_USER
import org.http4k.ai.a2a.model.Message
import org.http4k.ai.a2a.model.MessageId
import org.http4k.ai.a2a.model.Part
import org.http4k.ai.a2a.model.ResponseStream
import org.http4k.ai.a2a.model.Task
import org.http4k.core.Uri

fun main() {
    HttpA2AClient(Uri.of("http://localhost:8080")).use { client ->
        val stream = client.messageStream(
            Message(
                messageId = MessageId.random(),
                role = ROLE_USER,
                parts = listOf(Part.Text("Process this data"))
            )
        ).valueOrNull()!! as ResponseStream

        // Iterate over streaming responses
        stream.forEach { item ->
            when (item) {
                is Task -> println("Task ${item.id}: ${item.status.state}")
                is Message -> println("Message: ${item.parts}")
                else -> {}
            }
        }
    }
}
