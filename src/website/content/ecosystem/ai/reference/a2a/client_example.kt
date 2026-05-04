package content.ecosystem.ai.reference.a2a

import dev.forkhandles.result4k.valueOrNull
import org.http4k.ai.a2a.client.HttpA2AClient
import org.http4k.ai.a2a.client.RestA2AClient
import org.http4k.ai.a2a.model.A2ARole.ROLE_USER
import org.http4k.ai.a2a.model.Message
import org.http4k.ai.a2a.model.MessageId
import org.http4k.ai.a2a.model.Part
import org.http4k.core.Uri

fun main() {
    // JSON-RPC client
    val jsonRpcClient = HttpA2AClient(Uri.of("http://localhost:8080"))

    // Or REST client
    val restClient = RestA2AClient(Uri.of("http://localhost:8080"))

    jsonRpcClient.use { client ->
        // Discover agent capabilities
        val card = client.agentCard().valueOrNull()!!
        println("Connected to: ${card.name} v${card.version}")

        // Send a message
        val response = client.message(
            Message(
                messageId = MessageId.random(),
                role = ROLE_USER,
                parts = listOf(Part.Text("Hello, agent!"))
            )
        ).valueOrNull()!!

        println("Response: $response")
    }
}
