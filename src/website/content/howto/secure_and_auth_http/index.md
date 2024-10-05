---
layout: howto
title: "Authentication for HTTP services"
description: Recipes for how to secure and authenticate HTTP services
---
### Gradle setup

```kotlin
dependencies {
    {{< http4k_bom >}}
    implementation("org.http4k:http4k-core")

    // for OAuth examples
    implementation("org.http4k:http4k-security-oauth")
}
```

http4k provides a set of Filters for authenticating into other HTTP services. Usage of these filters is shown below to authenticate into a service. Each authentication type is generally available using both dynamic and static credential provision and checking mechanisms.

### Code

{{< kotlin file="example.kt" >}}
