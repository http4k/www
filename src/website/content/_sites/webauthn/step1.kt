package content._sites.webauthn

import org.http4k.connect.model.Base64UriBlob
import org.http4k.core.Request
import org.http4k.core.Uri
import org.http4k.lens.RequestKey
import org.http4k.security.passkeys.PasskeyPersistence
import org.http4k.security.passkeys.PasskeyVerifier
import org.http4k.security.passkeys.Passkeys
import org.http4k.security.passkeys.Principals
import org.http4k.security.passkeys.model.PasskeyUser
import org.http4k.security.passkeys.model.RelyingParty
import org.http4k.security.passkeys.randomHandle
import org.http4k.security.passkeys.testing.InMemoryPasskeyPersistence
import org.http4k.security.passkeys.testing.InsecureCookieBasedPrincipals
import org.http4k.security.passkeys.webauthn4j.WebAuthn4jPasskeyVerifier


val passkeyPersistence: PasskeyPersistence = InMemoryPasskeyPersistence()

val principals: Principals = InsecureCookieBasedPrincipals("acme", RequestKey.required("handle"))

val toUser: (Request) -> PasskeyUser? = { PasskeyUser(Base64UriBlob.randomHandle(), "name", "name") }
val toPasskeyUser: (Base64UriBlob) -> PasskeyUser? =
    { PasskeyUser(Base64UriBlob.randomHandle(), "name", "name") }

val passkeyVerifier: PasskeyVerifier = WebAuthn4jPasskeyVerifier()

            val relyingParty = RelyingParty("http4k", "http4k", Uri.of("https://http4k.org"))

            val noPasswordRequired = Passkeys.passwordless(
                relyingParty, passkeyVerifier, passkeyPersistence, principals, toUser
            )

            val addedToExisting = Passkeys.onTopOfExistingLogin(
                relyingParty, passkeyVerifier, passkeyPersistence, principals, toPasskeyUser
            )


fun main() {
    addedToExisting
    noPasswordRequired
}
