---
layout: howto
title: "Deploy WebJars"
description: Recipe for using WebJars with http4k 
tags: [ "http4k Core" ]
---

### Gradle setup

```kotlin
dependencies {
    {{< http4k_bom >}}
    implementation("org.http4k:http4k-core")

    // for the example...
    implementation("org.webjars:swagger-ui", version = "3.43.0")
}
```

[WebJars](https://www.webjars.org/) is a library to ship pre-packaged Web assets on your classpath by just adding the dependency. The assets are rehoused under the META-INF directory and end up with URLs such as: 

http://localhost:8080/webjars/swagger-ui/3.43.0/index.html

http4k integrates this functionality into the core library and ships with the `webJars()` router plugin to activate. As the plugin is just an `HttpHandler`, the simplest example is just to launch WebJars directly as a Server:

```kotlin
webJars().asServer(SunHttp(8080)).start()
```

... or a more standard use-case is to mix it into your application routing as in the example below:

### Code

{{< kotlin file="example.kt" >}}
