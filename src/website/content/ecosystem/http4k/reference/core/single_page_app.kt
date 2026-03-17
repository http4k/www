package content.ecosystem.http4k.reference.core

import org.http4k.core.Response
import org.http4k.core.Status.Companion.OK
import org.http4k.routing.bind
import org.http4k.routing.routes
import org.http4k.routing.singlePageApp

val spaRoutes = routes(
    "/reference/api" bind { Response(OK).body("some api content") },
    singlePageApp()
)
