package content.ecosystem.pro.reference.a2a

import org.http4k.ai.a2a.model.A2ARole.ROLE_AGENT
import org.http4k.ai.a2a.model.AgentCard
import org.http4k.ai.a2a.model.Message
import org.http4k.ai.a2a.model.MessageId
import org.http4k.ai.a2a.model.Part
import org.http4k.ai.a2a.model.Version
import org.http4k.routing.a2aJsonRpc
import org.http4k.server.Helidon
import org.http4k.server.asServer

fun main() {
    val agentCard = AgentCard(
        name = "My Agent",
        version = Version.of("1.0.0"),
        description = "An example A2A agent"
    )

    // The MessageHandler is a simple function (MessageRequest) -> MessageResponse
    val server = a2aJsonRpc(agentCard) { req ->
        Message(
            messageId = MessageId.random(),
            role = ROLE_AGENT,
            parts = listOf(Part.Text("Hello from My Agent!"))
        )
    }

    // For REST protocol binding, use a2aRest() instead:
    // val server = a2aRest(agentCard) { request -> ... }

    server.asServer(Helidon(8080)).start()
}
