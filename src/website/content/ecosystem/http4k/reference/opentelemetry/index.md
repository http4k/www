---
category: Reference
type: ecosystem
ecosystem: http4k Core
title: OpenTelemetry
description: Feature overview of the http4k-ops-http4k-opentelemetry module
---


### Installation (Gradle)

```kotlin
dependencies {
    {{< http4k_bom >}}
    implementation("org.http4k:http4k-ops-http4k-opentelemetry")
}
```

### About

This module provides configurable Filters to provide distributed tracing and metrics for http4k apps, plugging into the awesome [OpenTelemetry](https://opentelemetry.io/) APIs.

`OpenTelemetry is a collection of tools, APIs, and SDKs. You use it to instrument, generate, collect, and export telemetry data (metrics, logs, and traces) for analysis in order to understand your software's performance and behavior.`

### Tracing 

OpenTelemetry provides a pluggable interface for tracing propagation, so you can easily switch between different implementations such as AWS X-Ray, B3 and Jaeger etc.

{{< kotlin file="example_tracing.kt" >}}

### Metrics 

Both Server and Client filters are available for recording request counts and latency, optionally overriding values for the metric names, descriptions and request identification.

{{< kotlin file="example_metrics.kt" >}}
