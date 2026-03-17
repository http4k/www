package content.news.meet_http4k

import org.http4k.core.Body
import org.http4k.core.Method.GET
import org.http4k.core.Request
import org.http4k.format.Jackson.auto

data class Email(val value: String)
data class Message(val subject: String, val from: Email, val to: Email)

val messageLens = Body.auto<Message>().toLens()
val body = """{"subject":"hello","from":{"value":"bob@git.com"},"to":{"value":"sue@git.com"}}"""
val message: Message = messageLens(Request(GET, "/").body(body))
