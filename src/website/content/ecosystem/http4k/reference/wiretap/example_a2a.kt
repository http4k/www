package content.ecosystem.http4k.reference.wiretap

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import dev.forkhandles.result4k.Success
import dev.forkhandles.result4k.valueOrNull
import org.http4k.ai.a2a.client.A2AClient
import org.http4k.ai.a2a.model.A2ARole.ROLE_AGENT
import org.http4k.ai.a2a.model.A2ARole.ROLE_USER
import org.http4k.ai.a2a.model.AgentCard
import org.http4k.ai.a2a.model.Message
import org.http4k.ai.a2a.model.MessageId
import org.http4k.ai.a2a.model.Part
import org.http4k.ai.a2a.model.Version
import org.http4k.protocol.A2A
import org.http4k.wiretap.junit.Intercept
import org.http4k.wiretap.junit.RenderMode.Always
import org.http4k.wiretap.junit.a2a
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.RegisterExtension

class A2ATest {

    private val agentCard = AgentCard("My Agent", Version.of("1.0.0"), "Example agent")
    private val reply = Message(MessageId.of("reply-1"), ROLE_AGENT, listOf(Part.Text("Hello back!")))

    @RegisterExtension
    @JvmField
    val intercept = Intercept.a2a(Always) {
        A2A(agentCard) { reply }
    }

    // A2AClient is injected - connects to the A2A server under test
    @Test
    fun `can interact with A2A agent`(client: A2AClient) {
        assertThat(client.agentCard(), equalTo(Success(agentCard)))

        val response = client.message(
            Message(MessageId.of("msg-1"), ROLE_USER, listOf(Part.Text("Hello")))
        ).valueOrNull()!!

        assertThat(response, equalTo(reply))
    }
}
