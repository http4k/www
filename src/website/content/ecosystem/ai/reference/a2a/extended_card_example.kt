package content.ecosystem.ai.reference.a2a

import org.http4k.ai.a2a.model.AgentCapabilities
import org.http4k.ai.a2a.model.AgentCard
import org.http4k.ai.a2a.model.AgentCardProvider
import org.http4k.ai.a2a.model.AgentSkill
import org.http4k.ai.a2a.model.SkillId
import org.http4k.ai.a2a.model.Version
import org.http4k.routing.a2aJsonRpc

val standardCard = AgentCard(
    name = "My Agent",
    version = Version.of("1.0.0"),
    description = "Public agent capabilities",
    capabilities = AgentCapabilities(extendedAgentCard = true)
)

val extendedCard = standardCard.copy(
    description = "Full agent capabilities (authenticated)",
    skills = listOf(
        AgentSkill(
            id = SkillId.of("admin"),
            name = "Admin Operations",
            description = "Privileged operations for authenticated users"
        )
    )
)

// AgentCardProvider serves both standard and extended cards
val agentCardServer = a2aJsonRpc(messageHandler = {
    // handle messages...
    TODO()
}, cards = AgentCardProvider(standardCard, extendedCard))
