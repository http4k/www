package content._sites.a2a

import org.http4k.ai.a2a.model.AgentCapabilities
import org.http4k.ai.a2a.model.AgentCard
import org.http4k.ai.a2a.model.AgentSkill
import org.http4k.ai.a2a.model.SkillId
import org.http4k.ai.a2a.model.Version


            val agentCard = AgentCard(
                name = "Recipe Agent",
                version = Version.of("1.0.0"),
                description = "Helps with recipes",
                capabilities = AgentCapabilities(streaming = true),
                skills = listOf(
                    AgentSkill(
                        SkillId.of("find-recipe"), "Find Recipe", "Search recipes by ingredient"
                    )
                )
            )
