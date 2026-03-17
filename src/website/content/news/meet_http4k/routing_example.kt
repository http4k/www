package content.news.meet_http4k

import org.http4k.core.HttpHandler
import org.http4k.core.Method.DELETE
import org.http4k.core.Method.GET
import org.http4k.core.Method.POST
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status.Companion.OK
import org.http4k.routing.bind
import org.http4k.routing.path
import org.http4k.routing.routes

val routingApp: HttpHandler = routes(
    "/app" bind GET to decoratedApp,
    "/other" bind routes(
        "/delete" bind DELETE to { _: Request -> Response(OK) },
        "/post/{name}" bind POST to { request: Request ->
            Response(OK).body("you POSTed to ${request.path("name")}")
        }
    )
)
