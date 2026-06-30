package content.ecosystem.pro.reference.a2a

import org.http4k.ai.a2a.model.AgentCapabilities
import org.http4k.ai.a2a.model.AgentCard
import org.http4k.ai.a2a.model.AgentProvider
import org.http4k.ai.a2a.model.AgentSkill
import org.http4k.ai.a2a.model.SkillId
import org.http4k.ai.a2a.model.Version
import org.http4k.connect.model.MimeType
import org.http4k.core.Uri

val agentCard = AgentCard(
    name = "Recipe Agent",
    version = Version.of("1.0.0"),
    description = "An agent that helps users with recipes and cooking",
    provider = AgentProvider(organization = "http4k", url = Uri.of("https://http4k.org")),
    documentationUrl = Uri.of("https://http4k.org/docs/a2a"),
    capabilities = AgentCapabilities(
        streaming = true,
        pushNotifications = true,
        extendedAgentCard = true
    ),
    defaultInputModes = listOf(MimeType.of("text/plain")),
    defaultOutputModes = listOf(MimeType.of("text/plain"), MimeType.of("application/json")),
    skills = listOf(
        AgentSkill(
            id = SkillId.of("find-recipe"),
            name = "Find Recipe",
            description = "Search for recipes by ingredients or cuisine",
            tags = listOf("cooking", "recipes", "search")
        ),
        AgentSkill(
            id = SkillId.of("nutrition-info"),
            name = "Nutrition Info",
            description = "Get nutritional information for a recipe",
            tags = listOf("nutrition", "health")
        )
    )
)
