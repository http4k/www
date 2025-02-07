---
layout: howto
title: "Request Contexts"
description: Recipes for using http4k with per-request context objects
tags: [ "http4k Core" ]
---

It is possible to attach objects to a request whilst it is being passed down through the layers of an application.

[//]: # (The system uses the http4k Lens system to attach arbitrary data to the request, which can then be accessed by any part of the application that has access to the request. Typically this is used for attaching things like request IDs, authorised user principals, or other context-specific data. Under the covers this is just a non-typesafe map attached to an implementation of `Request`, but the Lens system provides a typesafe way to interact with it.)

The basic concept is that requests and responses hold a bag of state. This state can be modified in Filters and then 
that state accessed inside other Filters or the terminating HttpHandler. The data can be manipulated using the lens mechanism (`RequestLens`) available in the `http4k-core` module.

### Gradle setup

```kotlin
dependencies {
    {{< http4k_bom >}}
    implementation("org.http4k:http4k-core")
}
```

#### Lens-based keys 

{{< kotlin file="lens_key_example.kt" >}}
