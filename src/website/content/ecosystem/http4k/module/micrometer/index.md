---
layout: module
type: module
ecosystem: http4k Core
title: Micrometer
description: Feature overview of the http4k-metrics-micrometer module
---


### Installation (Gradle)

```kotlin
dependencies {
    implementation(platform("org.http4k:http4k-bom:5.32.1.0"))
    implementation("org.http4k:http4k-metrics-micrometer")
}
```

### About

This module provides configurable Filters to provide metrics for http4k apps, plugging into the awesome [Micrometer](http://micrometer.io/) library.

### Micrometer 

Both Server and Client filters are available for recording request counts and latency, optionally overriding values for the metric names, descriptions and request identification.

{{< kotlin file="example.kt" >}}
