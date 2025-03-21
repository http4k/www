---
layout: howto
title: "Use Auto Content Negotiation"
description: Recipes for using the Auto Content Negotiation Lens to marshall and unmarshall HTTP messages of various formats
tags: [ "http4k Core" ]
---

Example showing how to combine multiple body Lenses into a single facade that will simplify content negotiation for inbound and outbound messages.

### Gradle setup

Auto Content Negotiation is available in the core http4k module.

```kotlin
dependencies {
    {{< http4k_bom >}}
    implementation("org.http4k:http4k-format-core")
}
```

But it also integrates with the contract module.

```kotlin
dependencies {
    {{< http4k_bom >}}
    implementation("org.http4k:http4k-api-openapi")
}
```

### Rationale
Standard Body lenses work great in APIs that use a single message format (such as the ubiquitous JSON API),
but there are scenarios where you may want to offer the user their choice of format (e.g. XML, YAML, JSON).

While it is possible to use the `ACCEPT` and `CONTENT_TYPE` lenses to manually select the inbound and outbound body lenses,
the `AutoContentNegotiator` can do this for you.

### Using Auto Content Negotiation

The `AutoContentNegotiator` starts with your selection of body lenses, and wraps them together.
It can then be used to:
- Unmarshall `Request` bodies based on the `CONTENT_TYPE` header
- Select an outbound `BodyLens`, based on the `ACCEPT` header
- Add all the request and body formats to your contract

If the `CONTENT_TYPE` and `ACCEPT` headers are not present, or if there is no lens for the requested format,
then a default lens is used.

### Example 

{{< kotlin file="example.kt" >}}
