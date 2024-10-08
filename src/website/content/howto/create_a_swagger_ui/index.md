---
layout: howto
title: "Redoc and Swagger UI"
description: Create a Redoc or Swagger UI for your REST API
---
Http4k makes it easy to include Swagger UI or Redoc in your application.
These UIs can often replace traditional hand-written documentation for API consumers to learn your API,
and can even serve as useful debugging tools.

## Build the OpenAPI spec

Swagger UI and Redoc both require an **OpenApi** v2 or v3 description to function.
Http4k can generate a description for your API with the `http4k-contract` module,
but any hand-crafted or external description can be used as well.

For more detail on generating **OpenAPI** descriptions, see:

- [Http4k Reference: Contracts](/ecosystem/http4k/reference/contracts/)
- [Integrate with OpenAPI](/howto/integrate_with_openapi/)

### Example 

This simple description will be used for all examples in this guide:

{{< kotlin file="exampleContract.kt" >}}

## Build the UI

The `http4-contract` module includes functions to configure and serve Swagger UI, Redoc, or both.
These "lite" UIs are thin; meaning most of the assets are pulled from an external Public CDN.

### Installation (Gradle)

```kotlin
dependencies {
    {{< http4k_bom >}}
    implementation("org.http4k:http4k-contract")
}
```

### Example 

{{< kotlin file="exampleLite.kt" >}}

## Bundle the UI with Webjars

The "lite" UIs included in the `http4k-contract` module are great for serverless APIs, where binary size is a major concern.
For more control over the assets, http4k has optional modules to bundle the assets into your application.

### Installation (Gradle)

```kotlin
dependencies {
    {{< http4k_bom >}}
    implementation("org.http4k:http4k-contract-ui-swagger")
    implementation("org.http4k:http4k-contract-ui-redoc")
}
```

You can pick and choose whether you want Redoc, Swagger UI, or both bundled with your application.

### Example 

{{< kotlin file="exampleWebjar.kt" >}}
