package content.ecosystem.ai.reference.a2a

import dev.forkhandles.result4k.valueOrNull
import org.http4k.ai.a2a.client.HttpA2AClient
import org.http4k.ai.a2a.model.A2ARole.ROLE_USER
import org.http4k.ai.a2a.model.Message
import org.http4k.ai.a2a.model.MessageId
import org.http4k.ai.a2a.model.Part
import org.http4k.ai.a2a.model.Task
import org.http4k.ai.a2a.model.TaskState
import org.http4k.core.Uri

fun main() {
    HttpA2AClient(Uri.of("http://localhost:8080")).use { client ->
        // Send a message that creates a task
        val task = client.message(
            Message(MessageId.random(), ROLE_USER, listOf(Part.Text("Start processing")))
        ).valueOrNull()!! as Task

        // Get task by ID
        val retrieved = client.tasks().get(task.id).valueOrNull()!!
        println("Task state: ${retrieved.status.state}")

        // List tasks with filtering
        val page = client.tasks().list(
            status = TaskState.TASK_STATE_WORKING,
            pageSize = 10
        ).valueOrNull()!!
        println("Found ${page.totalSize} working tasks")

        // Cancel a task
        val cancelled = client.tasks().cancel(task.id).valueOrNull()!!
        println("Cancelled: ${cancelled.status.state}")
    }
}
