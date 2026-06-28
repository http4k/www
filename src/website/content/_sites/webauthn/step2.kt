package content._sites.webauthn

import org.http4k.core.Method.GET
import org.http4k.core.Response
import org.http4k.core.Status.Companion.OK
import org.http4k.core.then
import org.http4k.routing.bind
import org.http4k.routing.routes


    val app = routes(
        "/passkeys" bind noPasswordRequired.routes,
        "/account" bind GET to noPasswordRequired.authFilter.then { Response(OK).body("members only") },
        "/logout" bind GET to noPasswordRequired.logout,
        "/" bind GET to { Response(OK).body("public home") }
    )
