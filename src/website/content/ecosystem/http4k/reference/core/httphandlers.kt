package content.ecosystem.http4k.reference.core

import org.http4k.core.Method
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status.Companion.OK

val simpleHandler = { request: Request -> Response(OK).body("Hello, ${request.query("name")}!") }
val get = Request(Method.GET, "/").query("name", "John Doe")
val response = simpleHandler(get)

val printStatus = println(response.status)
val printBody = println(response.bodyString())
