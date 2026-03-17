---
layout: howto
title: "Pimp your Connect API Clients with KSP!"
description: How to use KSP to generate extension functions for your Connect clients
tags: [ "http4k Connect" ]
---


http4k-connect ships with a KSP plugin to automate the generation of the client extension-methods that accompany each Connect client. This allows you to skip creating
those extensions manually and maintain the API of the client appears to contain methods for each Action.

## Generating extension methods for your clients

1 - Define your base Action (and interface) using the http4k base class and tag it with the Http4kConnectAction annotation:

{{< kotlin file="action.kt" >}}

2 - Define your API Client, tagging it with the Http4kConnectClient annotation:

{{< kotlin file="client.kt" >}}

3 - Install KSP into Gradle, apply it, and create a KSP configuration using the http4k-connect KSP plugin in your module:

```kotlin
plugins {
    kotlin("jvm") 
    id("com.google.devtools.ksp")
}

apply(plugin = "com.google.devtools.ksp")

dependencies {
    {{< http4k_bom >}}
    ksp("org.http4k:http4k-connect-ksp-generator")
}
```

4 - And that's it! When Gradle runs, the following extension function will be generated:

{{< kotlin file="generated.kt" >}}

... which allows anyone to call it as if it was a standard method:

{{< kotlin file="usage.kt" >}}
