package content.tutorial.build_an_a2a_agent

import org.http4k.ai.a2a.model.AgentCapabilities
import org.http4k.ai.a2a.model.AgentCard
import org.http4k.ai.a2a.model.AgentSkill
import org.http4k.ai.a2a.model.SkillId
import org.http4k.ai.a2a.model.Version
import org.http4k.connect.model.MimeType


val recipeAgentCard = AgentCard(
    name = "Recipe Agent",
    version = Version.of("1.0.0"),
    description = "An agent that helps users find and explore recipes",
    capabilities = AgentCapabilities(streaming = true),
    defaultInputModes = listOf(MimeType.of("text/plain")),
    defaultOutputModes = listOf(MimeType.of("text/plain")),
    skills = listOf(
        AgentSkill(
            id = SkillId.of("find-recipe"),
            name = "Find Recipe",
            description = "Search for recipes by ingredients or cuisine",
            tags = listOf("cooking", "recipes", "search")
        ),
        AgentSkill(
            id = SkillId.of("nutrition"),
            name = "Nutrition Info",
            description = "Get nutritional breakdown for a recipe",
            tags = listOf("nutrition", "health")
        )
    )
)
