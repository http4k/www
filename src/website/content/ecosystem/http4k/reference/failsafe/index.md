---
category: Reference
type: ecosystem
ecosystem: http4k Core
title: Failsafe
description: Feature overview of the http4k-ops-failsafe module
---


### Installation (Gradle)

```kotlin
dependencies {
    {{< http4k_bom >}}
    implementation("org.http4k:http4k-ops-failsafe")
}
```

### About

This module provides a configurable Filter to provide fault tolerance (CircuitBreaking, RateLimiting, Retrying, Bulkheading, Timeouts etc.),
by integrating with the [Failsafe](https://failsafe.dev/) library.

### Basic example 

Here's an example that uses BulkHeading to demonstrate how easy it is to use the filter with configured Failsafe policies.

{{< kotlin file="example_bulkheading.kt" >}}

### Example of using multiple policies 

Using multiple Failsafe policies in the filter is just as easy, as the following example shows.

{{< kotlin file="example_multiple_policies.kt" >}}
