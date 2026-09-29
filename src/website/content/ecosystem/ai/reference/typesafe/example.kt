package content.ecosystem.ai.reference.typesafe

import org.http4k.ai.model.ApiKey
import org.http4k.client.JavaHttpClient
import org.http4k.connect.orThrow
import org.http4k.connect.typesafe.FakeTypeSafe
import org.http4k.connect.typesafe.Http
import org.http4k.connect.typesafe.Question
import org.http4k.connect.typesafe.TypeSafe
import org.http4k.connect.typesafe.ask
import org.http4k.connect.typesafe.systemOne
import org.http4k.core.HttpHandler
import org.http4k.filter.debug

const val USE_REAL_CLIENT = false

val department = Question.Choice(
    "department", "Which team should handle this",
    mapOf(
        "billing" to "Payment or subscription issues",
        "technical" to "Bugs or integration problems",
        "sales" to "Pricing or account questions"
    )
)

val frustration = Question.Score(
    "frustration", "How frustrated the customer appears",
    listOf("Calm, just stating facts", "Frustrated but civil", "Very angry, strong language")
)

val isUrgent = Question.Noul("is_urgent", "The message conveys urgency or time-sensitivity")

fun main() {
    // we can connect to the real service or the fake (drop in replacement)
    val http: HttpHandler = if (USE_REAL_CLIENT) JavaHttpClient() else FakeTypeSafe()

    // create a client
    val client = TypeSafe.Http(ApiKey.of("my-api-key"), http.debug())

    val complaint = "I've been trying to connect my Stripe account for 3 days and it keeps failing. I'm losing sales."

    // ask a batch of questions about the same state
    val answers = client.systemOne(complaint, department, frustration, isUrgent).orThrow()

    println("route to: " + answers.answerTo(department).choice)
    println("frustration: " + answers.answerTo(frustration).score)
    println("urgency: " + answers.answerTo(isUrgent).noul)

    // or just one, for its typed answer
    println(client.ask(complaint, isUrgent).orThrow().noul)
}
