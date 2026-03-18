---
category: Reference
type: ecosystem
ecosystem: http4k Connect
title: Machine Payments Protocol
tier: pro
description: Feature overview of the http4k Connect MPP modules
---

```kotlin
dependencies {
    {{< http4k_bom >}}
    implementation("org.http4k:http4k-connect-mpp")
}
```

http4k Connect provides support for the [Machine Payments Protocol (MPP)](https://paymentauth.org/), enabling HTTP 402 Payment Required flows for APIs. MPP supports multiple payment methods (Stripe, crypto, etc.) and provides a protocol-level standard for machine-to-machine payments.

Unlike x402, MPP has no facilitator service — `MppSigner` and `MppVerifier` are fun interfaces that you implement directly to handle signing and verification for your chosen payment method.

### Client-side Filter

The `ClientFilters.MppPaymentRequired` filter automatically handles 402 responses by signing the challenge from the server and retrying the request. You provide an `MppSigner` implementation that creates a `Credential` from a `Challenge`:

{{< kotlin file="client.kt" >}}

### Server-side Filter

The `ServerFilters.MppPaymentRequired` filter protects endpoints by requiring payment. It verifies credentials directly via your `MppVerifier` implementation before allowing access:

{{< kotlin file="server.kt" >}}

### MppSecurity

For route-level protection, use `MppSecurity` which wraps the server filter as an http4k `Security` instance:

{{< kotlin file="security.kt" >}}
