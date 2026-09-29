package content.ecosystem.ai.reference.typesafe

import org.http4k.connect.orThrow
import org.http4k.connect.typesafe.Entry
import org.http4k.connect.typesafe.FakeTypeSafe
import org.http4k.connect.typesafe.Question
import org.http4k.connect.typesafe.chosen
import org.http4k.connect.typesafe.invoke
import org.http4k.connect.typesafe.systemOne

data class SupportCase(val message: String, val orderId: Int)

data class Severity(val label: String, val description: String)

val severityLevels = listOf(
    Severity("minor", "Cosmetic or a workaround exists"),
    Severity("major", "Blocks a key workflow"),
    Severity("critical", "The customer cannot operate")
)

val severity = Question.Score("severity", "How severe is the problem", severityLevels)

val impact = Question.Choice(
    "impact", "How badly does this affect the customer",
    severityLevels.associateBy { it.label }
)

fun main() {
    val client = FakeTypeSafe().client()

    val case = SupportCase("The dashboard crashes on login", 4242)

    val response = client.systemOne(case, severity, impact).orThrow()

    val severityLens = Entry<Severity>()

    println("scored against: " + severityLens(response.answerTo(severity).legend))
    println("impact: " + response.answerTo(impact).chosen<Severity>(impact).description)
}
