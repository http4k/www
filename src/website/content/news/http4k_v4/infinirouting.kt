package content.news.http4k_v4

import org.http4k.core.Method.POST
import org.http4k.core.Response
import org.http4k.core.Status.Companion.OK
import org.http4k.routing.bind
import org.http4k.routing.body
import org.http4k.routing.header
import org.http4k.routing.queries
import org.http4k.routing.routes

val routingApp = routes("/{name}" bind POST to (
    header("host") { it == "http4k.org" } bind routes(
        queries("queryName") bind { Response(OK).body("i had a query") },
        body { body: String -> body.length > 50 } bind { Response(OK).body("I was long") }
    ))
)
