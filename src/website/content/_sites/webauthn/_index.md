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
#how_tos:
#    -   section: "Passkeys in three pieces"
#        steps:
#            -   description: "**Step 1** - Describe your server with a **RelyingParty**, then pick your **PasskeyVerifier**, **PasskeyPersistence**, and **Principals** implementations."
#            -   description: "**Step 2** - Build a **Passkeys** with `passwordless(...)` or `onTopOfExistingLogin(...)` and mount its pre-wired ceremony **routes**."
#            -   description: "**Step 3** - Protect anything with **authFilter** and clear sessions with **logout**. Your users now sign in with a passkey."
pricing_table:
    id: prctbl_1TnFtlG47sNzv4yX7yZwnkHx
    key: pk_live_51QVe22G47sNzv4yXpAdUo8zZKsS97wLXlkTOBr6WILnYRIm3UYQ1WhMwz3azZMoTRnUzOwebV1m5E4FDicDtGUaG001uo16uL0

---
