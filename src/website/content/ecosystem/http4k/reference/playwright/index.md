---
category: Reference
type: ecosystem
ecosystem: http4k Core
title: "Testing: Playwright"
description: Feature overview of the http4k-testing-playwright module
---


### Installation (Gradle)

```kotlin
dependencies {
    {{< http4k_bom >}}
    implementation("org.http4k:http4k-testing-playwright")
}
```

### About

A JUnit extension for simply testing your http4k applications using the Playwright browser-automation library.

Create your application as normal and pass to the JUnit extension when registering it. The application is then launched
on a random port and a connected browser object injected into the test - requests to non-http URLs are automatically
routed to your app.

#### Code

{{< kotlin file="example.kt" >}}

[http4k]: https://http4k.org
