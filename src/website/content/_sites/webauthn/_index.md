---
sitemap:
    disable: true
title: "http4k WebAuthn"
tagline: "Kill the password. Ship passkeys."
layout: "product"
type: "product"
subdomain: webauthn
tier: pro
docs_link: https://http4k.org/ecosystem/http4k/reference/webauthn/
description: "**http4k WebAuthn** gives your users phishing-resistant passwordless login  with FIDO2 passkeys - the complete registration and authentication ceremonies, handled for you and fully testable."
navigation:
    -   name: docs
        title: Documentation
        url: https://http4k.org/ecosystem/enterprise/reference/webauthn/
    -   name: http4k Pro
        title: http4k Pro
        url: "https://http4k.org/pro"
    -   name: http4k Enterprise Edition
        title: http4k Enterprise Edition
        url: https://http4k.org/enterprise
features:
    -   title: "Truly<br/>Passwordless"
        icon: footprint
        colour: green
        description: "Let users sign in with a **fingerprint, face, security key, or device PIN**. No passwords to store, leak, reset, or phish. Supports passkey-only signup or **adding a passkey to an existing login**."
    -   title: Standards<br>Based
        icon: support
        colour: blue
        description: "Full **W3C WebAuthn / FIDO2** registration and authentication ceremonies, backed by **webauthn4j** and BouncyCastle for attestation, signature, challenge, origin, and sign-counter (clone detection) verification."
    -   title: Pluggable<br/>By Design
        icon: testability
        colour: indigo
        description: "Three small interfaces compose the flow - **PasskeyVerifier**, **PasskeyPersistence**, and **Principals**. Swap in your own credential store and session strategy without touching the ceremony logic."
    -   title: Testable<br>In Memory
        icon: supportive
        colour: orange
        description: "Ships with **InsecurePasskeyVerifier**, **InMemoryPasskeyPersistence**, and a fake authenticator so you can drive the entire passkey flow in fast, **out-of-container** tests - the http4k way."
how_tos:
    -   section: "Passkeys, start to finish"
        steps:
            -   description: "**Instantiate** - provide a verifier, a credential store, and a session strategy. That's the whole setup."
                alt: Instantiate Passkeys
                image: webauthn-step1.webp
            -   description: "**Mount** the pre-wired registration & authentication routes into your app - one line."
                alt: Mount the passkey routes
                image: webauthn-step2.webp
            -   description: "**Register** - the browser prompts the user to create a passkey with their fingerprint, face, or device PIN."
                alt: Passkey registration prompt
                image: webauthn-step3.webp
            -   description: "**Sign in** - one tap. The user authenticates with the passkey and your server establishes the session."
                alt: Passkey sign-in prompt
                image: webauthn-step4.webp
pricing_table:
    id: prctbl_1TnFtlG47sNzv4yX7yZwnkHx
    key: pk_live_51QVe22G47sNzv4yXpAdUo8zZKsS97wLXlkTOBr6WILnYRIm3UYQ1WhMwz3azZMoTRnUzOwebV1m5E4FDicDtGUaG001uo16uL0

---
