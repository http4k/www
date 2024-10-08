---
category: Reference
type: ecosystem
ecosystem: http4k Core
title: AWS
description: Feature overview of the http4k-aws module
---


### Installation (Gradle)

```kotlin
dependencies {
    {{< http4k_bom >}}
    implementation("org.http4k:http4k-aws")
}
```

### About
This module provides 2 things: a http4k compatible `SdkHttpClient` and a super-simple AWS request signing functionality for talking to AWS services.

1. With the `SdkHttpClient` you can use the standard Amazon SDKs libraries by plugging in a standard `HttpHandler`. This simplifies fault testing and means that you can print out the exact traffic which is going to AWS - which is brilliant for both debugging and writing Fakes. :)

#### Code

{{< kotlin file="example_sdk.kt" >}}

2. With the request signing functionality, once configured with the correct keys, the various AWS services are actually really simple to integrate with. They're just RESTy-type HTTPS services - the main difficulty is that all requests need to have their contents digitally signed with the AWS credentials to be authorised.

http4k provides a `Filter` which does this request signing process. Just decorate a standard HTTP client and then make the relevant calls:


#### Code

{{< kotlin file="example.kt" >}}
