package content.tutorial.build_an_a2a_agent

import org.http4k.ai.a2a.model.A2ARole.ROLE_AGENT
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
import org.http4k.routing.a2aJsonRpc
import java.util.UUID

fun RecipeAgent() = a2aJsonRpc(recipeAgentCard, messageHandler = { request ->
    val query = request.message.parts.filterIsInstance<Part.Text>().joinToString(" ") { it.text }
    val taskId = TaskId.of(UUID.randomUUID().toString())
    val contextId = ContextId.of(UUID.randomUUID().toString())

    ResponseStream(
        sequenceOf(
            Task(
                id = taskId,
                status = TaskStatus(state = TASK_STATE_WORKING),
                contextId = contextId,
                history = listOf(request.message)
            ),
            Task(
                id = taskId,
                status = TaskStatus(
                    state = TASK_STATE_COMPLETED,
                    message = Message(
                        messageId = MessageId.random(),
                        role = ROLE_AGENT,
                        parts = listOf(Part.Text("Found recipes for: $query\n\n1. Pasta Carbonara\n2. Tomato Basil Soup\n3. Grilled Vegetables"))
                    )
                ),
                contextId = contextId
            )
        )
    )
})

