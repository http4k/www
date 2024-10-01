---
layout: module
type: module
ecosystem: connect
title: GitHub
description: Feature overview of the http4k Connect GitHub module
---

The GitHub connector currently provides basic action interfaces and support for verifying webhook signatures only.

### Installation

```kotlin
dependencies {
    implementation(platform("org.http4k:http4k-connect-bom:5.22.1.0"))
    implementation("org.http4k:http4k-connect-github")
}
```
