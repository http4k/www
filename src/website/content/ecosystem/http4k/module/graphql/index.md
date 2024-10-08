---
category: Reference
type: ecosystem
ecosystem: http4k Core
title: GraphQL module
description: Feature overview of the http4k-graphql module.
---


### Installation (Gradle)

```kotlin
dependencies {
    {{< http4k_bom >}}
    implementation("org.http4k:http4k-graphql")

    // for the example below you will also need this dependency...
    implementation("com.expediagroup:graphql-kotlin-schema-generator", version = "5.3.2"
}
```


### About
This module provides http4k integration for the excellent [GraphQL-java](https://www.graphql-java.com/) library, allowing you to either serve or consume [GraphQL] services using a simple adapter API.

As with the [ethos](/overview/) of http4k, the uniform Server/Client GraphQLHandler model means that you can test applications entirely in-memory without binding to a port. Http4k also ships with a page serving the GraphQL playground which can be added as a simple route.

#### Code

{{< kotlin file="example.kt" >}}

[http4k]: https://http4k.org
[GraphQL]: https://graphql.org
