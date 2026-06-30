package content.ecosystem.pro.reference.a2a

import org.http4k.ai.a2a.model.A2ARole.ROLE_AGENT
import org.http4k.ai.a2a.model.AgentCard
import org.http4k.ai.a2a.model.ContextId
import org.http4k.ai.a2a.model.Message
import org.http4k.ai.a2a.model.MessageId
import org.http4k.ai.a2a.model.Part
import org.http4k.ai.a2a.model.Task
import org.http4k.ai.a2a.model.TaskId
import org.http4k.ai.a2a.model.TaskState.TASK_STATE_COMPLETED
import org.http4k.ai.a2a.model.TaskStatus
import org.http4k.ai.a2a.model.Version
import org.http4k.ai.a2a.server.storage.TaskStorage
import org.http4k.routing.a2aJsonRpc

// Use the built-in in-memory storage (default)
val tasks = TaskStorage.InMemory()

val serverForStorage = a2aJsonRpc(
    agentCard = AgentCard("My Agent", Version.of("1.0.0"), "Example"),
    tasks = tasksWithPush,
    messageHandler = { request ->
        val task = Task(
            id = TaskId.of("my-task"),
            status = TaskStatus(state = TASK_STATE_COMPLETED),
            contextId = ContextId.of("my-context"),
            history = listOf(
                request.message,
                Message(MessageId.random(), ROLE_AGENT, listOf(Part.Text("Done!")))
            )
        )
        // Store task for later retrieval via GetTask/ListTasks
        tasksWithPush.store(task)
        task
    }
)
