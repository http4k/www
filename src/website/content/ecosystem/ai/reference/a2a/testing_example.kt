package content.ecosystem.ai.reference.a2a

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import dev.forkhandles.result4k.Success
import dev.forkhandles.result4k.valueOrNull
import org.http4k.ai.a2a.client.testA2AJsonRpcClient
import org.http4k.ai.a2a.model.A2ARole.ROLE_AGENT
import org.http4k.ai.a2a.model.A2ARole.ROLE_USER
import org.http4k.ai.a2a.model.AgentCard
import org.http4k.ai.a2a.model.Message
import org.http4k.ai.a2a.model.MessageId
import org.http4k.ai.a2a.model.Part
import org.http4k.ai.a2a.model.Version
import org.http4k.routing.a2aJsonRpc
import org.junit.jupiter.api.Test

class A2ATestingExample {

    private val agentCard = AgentCard("Test Agent", Version.of("1.0.0"), "For testing")

    // Create an A2A server as a PolyHandler
    private val server = a2aJsonRpc(agentCard, messageHandler = { request ->
        Message(MessageId.of("response-1"), ROLE_AGENT, listOf(Part.Text("Echo")))
    })

    // Create an in-memory test client - no network, no ports
    private val client = server.testA2AJsonRpcClient()

    @Test
    fun `can discover agent card`() {
        assertThat(client.agentCard(), equalTo(Success(agentCard)))
    }

    @Test
    fun `can send message and get response`() {
        val response = client.message(
            Message(MessageId.of("msg-1"), ROLE_USER, listOf(Part.Text("Hello")))
        ).valueOrNull()!! as Message

        assertThat(response.role, equalTo(ROLE_AGENT))
        assertThat(response.parts.first(), equalTo(Part.Text("Echo")))
    }
}
