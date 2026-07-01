package content.ecosystem.pro.reference.webauthn

import org.http4k.connect.model.Base64UriBlob
import org.http4k.core.Method.GET
import org.http4k.core.Response
import org.http4k.core.Status.Companion.OK
import org.http4k.core.Uri
import org.http4k.lens.RequestKey
import org.http4k.routing.bind
import org.http4k.routing.routes
import org.http4k.security.passkeys.Passkeys
import org.http4k.security.passkeys.model.PasskeyUser
import org.http4k.security.passkeys.model.RelyingParty
import org.http4k.security.passkeys.randomHandle
import org.http4k.security.passkeys.testing.InMemoryPasskeyPersistence
import org.http4k.security.passkeys.testing.InsecureCookieBasedPrincipals
import org.http4k.security.passkeys.webauthn4j.WebAuthn4jPasskeyVerifier
import org.http4k.server.SunHttp
import org.http4k.server.asServer

/**
 * Simplest possible passkey demo: register, then log in. Open: http://localhost:9000,
 */
fun main() {
    val rp = RelyingParty(id = "localhost", name = "http4k passkeys demo", origin = Uri.of("http://localhost:9000"))
    val handle = RequestKey.required<Base64UriBlob>("handle")

    val passkeys = Passkeys.passwordless(
        rp = rp,
        verifier = WebAuthn4jPasskeyVerifier(),
        persistence = InMemoryPasskeyPersistence(),
        principals = InsecureCookieBasedPrincipals("http4k", handle),
        // the registering user comes straight off the signup request - here just a ?username=
        user = { req ->
            req.query("username")?.takeIf(String::isNotBlank)?.let { PasskeyUser(Base64UriBlob.randomHandle(), it, it) }
        }
    )

    val app = routes(
        "/" bind GET to { Response(OK).header("content-type", "text/html; charset=utf-8").body(page) },
        "/passkeys" bind passkeys.routes
    )

    app.asServer(SunHttp(9000)).start().also { println("passkeys demo: http://localhost:9000") }.block()
}
