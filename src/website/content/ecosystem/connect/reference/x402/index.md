---
category: Reference
type: ecosystem
ecosystem:
  - http4k Connect
  - http4k Enterprise
title: X402
tier: pro
description: Feature overview of the http4k Connect X402 modules
---

```kotlin
dependencies {
    {{< http4k_bom >}}
    implementation("org.http4k:http4k-connect-x402")
    implementation("org.http4k:http4k-connect-x402-fake")
}
```

http4k Connect provides support for the [x402 protocol](https://www.x402.org/), enabling HTTP 402 Payment Required flows for APIs. The module includes both client and server-side components for integrating cryptocurrency-based payments into your http4k applications.

The `X402Facilitator` client provides the following Actions:

- **Verify** - Verify a payment payload against payment requirements
- **Settle** - Settle a verified payment and receive a transaction hash
- **Supported** - Query the facilitator for supported payment schemes and networks

### Facilitator Client

Connect to an x402 facilitator service:

{{< kotlin file="facilitator.kt" >}}

### Client-side Filter

The `ClientFilters.X402PaymentRequired` filter automatically handles 402 responses by signing payment requirements and retrying the request. You provide an `X402Signer` implementation that signs payment requirements from your wallet:

{{< kotlin file="client.kt" >}}

### Server-side Filter

The `ServerFilters.X402PaymentRequired` filter protects endpoints by requiring payment. It verifies and settles payments via a facilitator before allowing access:

{{< kotlin file="server.kt" >}}

### X402Security

For route-level protection, use `X402Security` which wraps the server filter as an http4k `Security` instance:

{{< kotlin file="security.kt" >}}

### Default Fake port: 12794

To start:

{{< kotlin file="fake.kt" >}}
