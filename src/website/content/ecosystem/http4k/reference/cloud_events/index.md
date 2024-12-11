---
category: Reference
type: ecosystem
ecosystem: http4k Core
title: Cloud Events
description: Feature overview of the http4k-api-cloudevents module
---

### Installation (Gradle)

```kotlin
dependencies {
    {{< http4k_bom >}}
    implementation("org.http4k:http4k-api-cloudevents")
}
```

The [Cloud Events](https://cloudevents.io/) spec defines a common format for Events produced by Cloud services.

http4k provides simple pluggability into the CloudEvents Java SDKs and custom event format libraries via the Lens system - making it trivial to both receive or send CloudEvents in the standard way.

### Example 

In this example we are using the Jackson JSONFormat which is included by default with the `http4k-api-cloudevents` module. If you want to also use the lenses to access typed EventData, you will also need this in your Gradle file:

```kotlin
dependencies {
    {{< http4k_bom >}}
    // to access the lenses in the Jackson module
    implementation("org.http4k:http4k-format-jackson")
}
```

#### Code

{{< kotlin file="example.kt" >}}
