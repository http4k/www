package content.ecosystem.ai.reference.a2a

import org.http4k.ai.a2a.model.AgentCapabilities
import org.http4k.ai.a2a.model.AgentCard
import org.http4k.ai.a2a.model.Version
import org.http4k.ai.a2a.server.notification.PushNotificationSender
import org.http4k.ai.a2a.server.storage.PushNotificationConfigStorage
import org.http4k.ai.a2a.server.storage.TaskStorage
import org.http4k.ai.a2a.server.storage.withPushNotifications
import org.http4k.routing.a2aJsonRpc

// Enable push notifications in the agent capabilities
val agentCardWithPush = AgentCard(
    name = "Notifying Agent",
    version = Version.of("1.0.0"),
    description = "Agent with push notification support",
    capabilities = AgentCapabilities(pushNotifications = true)
)

val pushConfigs = PushNotificationConfigStorage.InMemory()
val pushSender = PushNotificationSender.Http()  // sends POST to configured webhook URLs

// Wrap task storage to automatically send push notifications on task updates
val tasksWithPush = TaskStorage.InMemory().withPushNotifications(pushConfigs, pushSender)

val serverWithPush = a2aJsonRpc(
    agentCard = agentCardWithPush,
    tasks = tasksWithPush,
    pushNotifications = pushConfigs,
    messageHandler = { request -> TODO() }
)
