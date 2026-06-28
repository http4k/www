---
category: Reference
type: ecosystem
ecosystem: http4k Enterprise
tier: pro
title: "Security: WebAuthn"
description: Feature overview of the http4k-security-webauthn module
---

### Installation (Gradle)

```kotlin
dependencies {
    {{< http4k_bom >}}
    implementation("org.http4k:http4k-security-webauthn")
}
```

### About

Passwordless authentication using [WebAuthn](https://www.w3.org/TR/webauthn-2/)/FIDO2 **passkeys**. The module handles
the full registration and authentication ceremonies, session management, and cryptographic verification of credentials,
so users can sign in with a fingerprint, face, security key, or device PIN instead of a password.

### Usage

Everything is orchestrated through the `Passkeys` type, which exposes two ways to wire passkeys into your app:

- **`Passkeys.passwordless(...)`** - passkey-only signup and login, with no prior session required.
- **`Passkeys.onTopOfExistingLogin(...)`** - let an already-logged-in user add a passkey to their account.

Either way you get a pre-wired set of `routes` for the browser-side ceremonies:

| Route                        | Purpose                                                                |
|------------------------------|------------------------------------------------------------------------|
| `POST /register/options`     | Begin registration - issue a challenge and creation options            |
| `POST /register`             | Complete registration - verify and store the new credential            |
| `POST /authenticate/options` | Begin authentication - issue a challenge                               |
| `POST /authenticate`         | Complete authentication - verify the signature and establish a session |

Plus an `authFilter` to protect routes and a `logout` handler to clear sessions.

### Extension points

The behaviour is composed from three pluggable interfaces, each with a production and a testing implementation:

| Interface            | Responsibility                                         | Production                  | Testing                         |
|----------------------|--------------------------------------------------------|-----------------------------|---------------------------------|
| `PasskeyVerifier`    | Cryptographic verification of attestation & signatures | `WebAuthn4jPasskeyVerifier` | `InsecurePasskeyVerifier`       |
| `PasskeyPersistence` | Storing & retrieving credentials and ceremony state    | _your store_                | `InMemoryPasskeyPersistence`    |
| `Principals`         | Reading/writing/clearing the user session              | _your session_              | `InsecureCookieBasedPrincipals` |

Supporting model types include `RelyingParty` (your server identity), `PasskeyUser` (the user identity),
`WebAuthnPolicy` (configurable security policy - user verification, attestation, resident key, etc.) and the sealed
`PasskeyError` describing ceremony failures (bad signature, challenge/origin mismatch, sign-counter regression for clone
detection, and so on).


[http4k]: https://http4k.org
