---
category: Reference
type: ecosystem
ecosystem: http4k Core
title: "Ops: OpenFeature"
description: Feature overview of the http4k-ops-openfeature module
---


### Installation (Gradle)

```kotlin
dependencies {
    {{< http4k_bom >}}
    implementation("org.http4k:http4k-ops-openfeature")
}
```

### About

This module integrates [OpenFeature](https://openfeature.dev/) feature flag evaluation into the http4k filter chain. A `ServerFilter` evaluates all flags for the incoming request once and stores the snapshot in the `Request`, which typed `OpenFeatureFlag` Lenses can then read in a type-safe manner. An `Http4kOpenFeatureProvider` is also supplied for those wishing to plug http4k into the official OpenFeature SDK as a `FeatureProvider`.

### OpenFeature

The `ServerFilters.PopulateOpenFeatureContext` filter accepts any `OpenFeature` client (typically from `http4k-connect-openfeature`, real or fake) and a function deriving an `EvaluationContext` from the request. Downstream handlers extract values via `OpenFeatureFlag` Lenses — each supporting `required`, `optional`, and `defaulted` variants.

{{< kotlin file="example.kt" >}}
