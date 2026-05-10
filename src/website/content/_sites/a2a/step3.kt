package content._sites.a2a

import org.http4k.ai.a2a.client.testA2AJsonRpcClient
import org.http4k.ai.a2a.model.A2ARole.ROLE_USER
import org.http4k.ai.a2a.model.Message
import org.http4k.ai.a2a.model.MessageId
import org.http4k.ai.a2a.model.Part.Text
import org.http4k.routing.a2aJsonRpc


val server = a2aJsonRpc(agentCard, messageHandler = handler)
val testClient = server.testA2AJsonRpcClient()
val response = testClient.message(
    Message(MessageId.random(), ROLE_USER, listOf(Text("Find pasta recipes")))
)
