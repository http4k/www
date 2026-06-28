package content.ecosystem.http4k.reference.webauthn

import org.http4k.connect.model.Base64UriBlob
import org.http4k.core.Method.GET
import org.http4k.core.Response
import org.http4k.core.Status.Companion.OK
import org.http4k.core.Status.Companion.SEE_OTHER
import org.http4k.core.Uri
import org.http4k.core.then
import org.http4k.lens.RequestKey
import org.http4k.lens.location
import org.http4k.routing.bind
import org.http4k.routing.routes
import org.http4k.security.passkeys.Passkeys
import org.http4k.security.passkeys.model.PasskeyUser
import org.http4k.security.passkeys.model.RelyingParty
import org.http4k.security.passkeys.randomHandle
import org.http4k.security.passkeys.testing.InMemoryPasskeyPersistence
import org.http4k.security.passkeys.testing.InsecureCookieBasedPrincipals
import org.http4k.security.passkeys.testing.InsecurePasskeyVerifier
import org.http4k.server.SunHttp
import org.http4k.server.asServer

fun main() {
    val rp = RelyingParty(id = "localhost", name = "http4k passkeys demo", origin = Uri.of("http://localhost:9000"))

    // pluggable extension points - swap the testing impls for production ones
    val verifier = InsecurePasskeyVerifier()              // -> WebAuthn4jPasskeyVerifier() in production
    val persistence = InMemoryPasskeyPersistence()        // -> your own credential store
    val handle = RequestKey.required<Base64UriBlob>("handle")
    val session = InsecureCookieBasedPrincipals("http4k", handle)

    val passkeys = Passkeys.passwordless(
        rp = rp,
        verifier = verifier,
        persistence = persistence,
        principals = session,
        // map an incoming signup request to a new user identity
        user = { PasskeyUser(Base64UriBlob.randomHandle(), name = "alice", displayName = "Alice") },
        onUnauthenticated = { Response(SEE_OTHER).location(Uri.of("/?next=${it.uri.path}")) }
    )

    val app = routes(
        "/passkeys" bind passkeys.routes,                 // register/authenticate ceremony routes
        "/logout" bind GET to passkeys.logout,
        "/protected" bind GET to passkeys.authFilter.then { Response(OK).body("secret area") }
    )

    app.asServer(SunHttp(9000)).start().also { println("passkeys demo: http://localhost:9000") }.block()
}
