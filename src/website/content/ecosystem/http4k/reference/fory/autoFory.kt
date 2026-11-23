package content.ecosystem.http4k.reference.fory

import org.http4k.core.Body
import org.http4k.core.Method.GET
import org.http4k.core.Request
import org.http4k.core.with
import org.http4k.format.Fory
import org.http4k.format.register

data class Email(val value: String)
data class Message(val subject: String, val from: Email, val to: Email)

val MyFory = Fory {
    register<Email>()
    register<Message>()
}

fun main() {
    with(MyFory) {
        val messageLens = Body.auto<Message>().toLens()

        val myMessage = Message("hello", Email("bob@git.com"), Email("sue@git.com"))

        // 1. Lens-first approach
        val request = messageLens(myMessage, Request(GET, "/"))

        println(messageLens(request) == myMessage)

        // 2. with()/of() approach
        val request2 = Request(GET, "/").with(messageLens of myMessage)

        // 3. convenience functions
        val request3 = Request(GET, "/").binary(myMessage)
        println(request3.binary<Message>() == myMessage)
    }
}
