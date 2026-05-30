---
category: Reference
type: ecosystem
ecosystem: http4k Connect
title: OpenFeature
description: Feature overview of the http4k Connect OpenFeature modules
---

```kotlin
dependencies {
    {{< http4k_bom >}}
    implementation("org.http4k:http4k-connect-openfeature")
}
```

The OpenFeature connector is a client speaking the standard [OpenFeature Remote Evaluation Protocol (OFREP)](https://openfeature.dev/specification/appendix-c), and provides the following Actions:

- Evaluate Flag
- Evaluate All Flags

It also ships with a `Cached` wrapper that caches evaluation results in a pluggable `Storage` for a configurable TTL.

### Example usage

{{< kotlin file="example.kt" >}}

### Default Fake port: 43778

To start:

{{< kotlin file="fake.kt" >}}
