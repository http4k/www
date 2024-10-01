---
layout: howto
title: "Server-as-a-Function"
description: The simplest example of an http4k application 
---
This example is the simplest possible "server" implementation. Note that we are not spinning up a server-backend here - but the entire application(!) is testable by firing HTTP requests at it as if it were.

### Gradle setup

```kotlin
dependencies {
    implementation(platform("org.http4k:http4k-bom:5.32.1.0"))
    implementation("org.http4k:http4k-core")
}
```

### Code

{{< kotlin file="example.kt" >}}
