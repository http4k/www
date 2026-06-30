package content.ecosystem.pro.reference.a2a

import content._sites.a2a.handler
import org.http4k.ai.a2a.MessageFilter
import org.http4k.ai.a2a.model.AgentCard
import org.http4k.ai.a2a.model.Version
import org.http4k.ai.a2a.then
import org.http4k.routing.a2aJsonRpc

// MessageFilter works like http4k's Filter but for A2A message handling
val logging = MessageFilter { next ->
    { request ->
        println("Received message: ${request.message.parts}")
        next(request).also { println("Response: $it") }
    }
}

val auth = MessageFilter { next ->
    { request ->
        // Check authorization from the HTTP request
        val token = request.http.header("Authorization")
        requireNotNull(token) { "Missing authorization" }
        next(request)
    }
}

// Compose filters and handler
val handler = logging.then(auth).then { request ->
    TODO("handle message")
}

val serverWithFilter = a2aJsonRpc(
    AgentCard("Filtered Agent", Version.of("1.0.0"), "With filters"),
    messageHandler = handler
)
