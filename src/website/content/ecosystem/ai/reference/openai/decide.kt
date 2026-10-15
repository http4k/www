package content.ecosystem.ai.reference.openai

import org.http4k.ai.llm.decide.Decide
import org.http4k.ai.llm.decide.DecideRequest
import org.http4k.ai.llm.decide.OpenAI
import org.http4k.ai.llm.decide.Question
import org.http4k.ai.model.ApiKey
import org.http4k.client.JavaHttpClient
import org.http4k.connect.openai.OpenAIModels

val decide = Decide.OpenAI(ApiKey.of("api-key"), JavaHttpClient())

val decision = decide(
    DecideRequest(
        "The parcel arrived crushed and the contents are broken.",
        mapOf("refund" to Question.YesNo("The customer is entitled to a refund")),
        OpenAIModels.GPT_6_LUNA
    )
)
