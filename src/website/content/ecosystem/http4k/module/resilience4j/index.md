---
layout: module
type: module
ecosystem: http4k Core
title: Resilience4J
description: Feature overview of the http4k-resilience4j module
---


### Installation (Gradle)

```kotlin
dependencies {
    implementation(platform("org.http4k:http4k-bom:5.32.1.0"))
    implementation("org.http4k:http4k-resilience4j")
}
```

### About

This module provides configurable Filters to provide CircuitBreaking, RateLimiting, Retrying and Bulkheading, by integrating with the awesome [Resilience4J](http://resilience4j.github.io/resilience4j/) library.

### Circuit Breaking 
A Circuit Filter detects failures and then Opens for a set period to allow the underlying system to recover.

{{< kotlin file="example_circuit.kt" >}}

### Rate Limiting 
A RateLimit Filter monitors the number of requests over a set window.

{{< kotlin file="example_ratelimiter.kt" >}}

### Retrying 
A Retrying Filter retries requests if a failure is generated.

{{< kotlin file="example_retrying.kt" >}}


### Bulkheading 
A Bulkhead Filter limits the amount of parallel calls that can be executed.

{{< kotlin file="example_bulkheading.kt" >}}
