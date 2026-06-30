package content.ecosystem.pro.reference.a2a

import org.http4k.ai.a2a.model.A2ARole.ROLE_AGENT
import org.http4k.ai.a2a.model.AgentCapabilities
import org.http4k.ai.a2a.model.AgentCard
import org.http4k.ai.a2a.model.ContextId
import org.http4k.ai.a2a.model.Message
import org.http4k.ai.a2a.model.MessageId
import org.http4k.ai.a2a.model.Part
import org.http4k.ai.a2a.model.ResponseStream
import org.http4k.ai.a2a.model.Task
import org.http4k.ai.a2a.model.TaskId
import org.http4k.ai.a2a.model.TaskState.TASK_STATE_COMPLETED
import org.http4k.ai.a2a.model.TaskState.TASK_STATE_WORKING
import org.http4k.ai.a2a.model.TaskStatus
import org.http4k.ai.a2a.model.Version
import org.http4k.routing.a2aJsonRpc

val streamingAgent = a2aJsonRpc(
    AgentCard(
        name = "Streaming Agent",
        version = Version.of("1.0.0"),
        description = "Agent with streaming support",
        capabilities = AgentCapabilities(streaming = true)
    ),
    messageHandler = { request ->
        val taskId = TaskId.of("task-1")
        val contextId = ContextId.of("context-1")

        // Return a ResponseStream to stream multiple updates to the client
        ResponseStream(
            sequenceOf(
                Task(
                    id = taskId,
                    status = TaskStatus(state = TASK_STATE_WORKING),
                    contextId = contextId,
                    history = listOf(request.message)
                ),
                Message(
                    messageId = MessageId.random(),
                    role = ROLE_AGENT,
                    parts = listOf(Part.Text("Processing your request..."))
                ),
                Task(
                    id = taskId,
                    status = TaskStatus(state = TASK_STATE_COMPLETED),
                    contextId = contextId
                )
            )
        )
    }
)
