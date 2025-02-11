---
category: Reference
type: ecosystem
ecosystem: http4k Core
title: Bridges
description: Feature overview of the http4k-bridge modules
---

### Installation (Gradle)

```kotlin
dependencies {
    {{< http4k_bom >}}
    // Helidon: 
    implementation("org.http4k:http4k-bridge-helidon")
    
    // Servlet (Jakarta):
    implementation("org.http4k:http4k-bridge-jakarta")
    
    // Ktor: 
    implementation("org.http4k:http4k-bridge-ktor")
    
    // Micronaut: 
    implementation("org.http4k:http4k-bridge-micronaut")
    
    // Ratpack: 
    implementation("org.http4k:http4k-bridge-ratpack")
    
    // Servlet (javax): 
    implementation("org.http4k:http4k-bridge-servlet")

    // Spring: 
    implementation("org.http4k:http4k-bridge-spring")
    
    // Vertx: 
    implementation("org.http4k:http4k-bridge-vertx")
}
```
Bridge modules provide a layer of integration with other web frameworks, allowing http4k applications to be deployed alongside them.

The integration allows teams to incrementally introduce http4k into existing applications, creating a path of migration from other migrations to http4k.

We currently provide bridges for the following frameworks:
- Spring (see [migration example](https://github.com/http4k/examples/tree/master/http4k-core/migration-spring))
- Ktor (see [migration example](https://github.com/http4k/examples/tree/master/http4k-core/migration-ktor))
- Micronaut (see [migration example](https://github.com/http4k/examples/tree/master/http4k-core/migration-micronaut))
- Helidon
- Vertx
- Ratpack
- Servlet (jakarta and javax)

### General migration strategy

1. Add the bridge module for the framework you are using.
2. Create a "fallback" `HttpHandler` as starting point for the migration.
3. Use the bridge to route requests to the fallback handler when no other routes are found.
4. Recreate a particular route in http4k, and remove it from the framework when it is ready.
5. Repeat step 4 until all routes are migrated.
6. Change the application entry point to serve from the http4k `HttpHandler` rather than the framework.
7. Remove the bridge module and other usages of the framework.
