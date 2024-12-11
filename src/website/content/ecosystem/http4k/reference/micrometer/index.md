---
category: Reference
type: ecosystem
ecosystem: http4k Core
title: "Ops: Micrometer"
description: Feature overview of the http4k-ops-micrometer module
---


### Installation (Gradle)

```kotlin
dependencies {
    {{< http4k_bom >}}
    implementation("org.http4k:http4k-ops-micrometer")
}
```

### About

This module provides configurable Filters to provide metrics for http4k apps, plugging into the awesome [Micrometer](http://micrometer.io/) library.

### Micrometer 

Both Server and Client filters are available for recording request counts and latency, optionally overriding values for the metric names, descriptions and request identification.

{{< kotlin file="example.kt" >}}
