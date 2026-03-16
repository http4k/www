package content.ecosystem.http4k.reference.core

import org.http4k.core.Method.GET
import org.http4k.core.Method.POST
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status.Companion.INTERNAL_SERVER_ERROR
import org.http4k.core.Status.Companion.OK
import org.http4k.routing.bind
import org.http4k.routing.path
import org.http4k.routing.routes
import org.http4k.server.Jetty
import org.http4k.server.asServer

val router = routes(
    "/hello" bind routes(
        "/{name:.*}" bind GET to { request: Request -> Response(OK).body("Hello, ${request.path("name")}!") }
    ),
    "/fail" bind POST to { request: Request -> Response(INTERNAL_SERVER_ERROR) }
).asServer(Jetty(8000)).start()
