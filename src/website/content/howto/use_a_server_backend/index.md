---
layout: howto
title: "Use a Server backend"
description: Recipes for using http4k with the various Server backends
tags: [ "http4k Core" ]
---

This example shows how to both serve an application HttpHandler using an embedded HTTP server and to query it using an HTTP client. All server-backend implementations are launched in an identical manner (in 1LOC) using implementations of the `ServerConfig` interface - and a base implementation of this interface is provided for each server backend.

Alternatively, any http4k application can be mounted into any Servlet container using the `asServlet()` extension method. This is the mechanism used in the Jetty11 implementation. for this, you need to add the `http4k-server-servlet` dependency.

### Gradle setup

```kotlin
dependencies {
    {{< http4k_bom >}}
    implementation("org.http4k:http4k-core")
    implementation("org.http4k:http4k-client-apache")
    implementation("org.http4k:http4k-server-jetty")
}
```

### Code

{{< kotlin file="example.kt" >}}
