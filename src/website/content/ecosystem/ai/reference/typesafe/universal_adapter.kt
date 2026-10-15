package content.ecosystem.ai.reference.typesafe

import dev.forkhandles.result4k.map
import org.http4k.ai.llm.decide.Decide
import org.http4k.ai.llm.decide.DecideRequest
import org.http4k.ai.llm.decide.Question
import org.http4k.ai.llm.decide.TypeSafe
import org.http4k.ai.model.ApiKey
import org.http4k.connect.typesafe.FakeTypeSafe
import org.http4k.connect.typesafe.JevModels

val decide = Decide.TypeSafe(ApiKey.of("api-key"), FakeTypeSafe())

val triage = DecideRequest(
    "I've been trying to connect my Stripe account for 3 days and it keeps failing. I'm losing sales.",
    mapOf(
        "department" to Question.Choice(
            "Which team should handle this",
            mapOf(
                "billing" to "Payment or subscription issues",
                "technical" to "Bugs or integration problems"
            )
        ),
        "frustration" to Question.Score(
            "How frustrated the customer appears",
            listOf(Question.Score.Level("calm"), Question.Score.Level("angry", "Strong language"))
        ),
        "is_urgent" to Question.YesNo("The message conveys urgency or time-sensitivity")
    ),
    JevModels.JevLatest
)

val triageAnswers = decide(triage).map { it.answers }
