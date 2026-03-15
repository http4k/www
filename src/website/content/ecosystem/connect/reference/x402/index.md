---
category: Reference
type: ecosystem
ecosystem: http4k Connect
title: X402
description: Feature overview of the http4k Connect X402 modules
---

```kotlin
dependencies {
    {{< http4k_bom >}}
    implementation("org.http4k:http4k-connect-x402")
    implementation("org.http4k:http4k-connect-x402-fake")
}
```

### Default Fake port: 12794

To start:

{{< kotlin file="fake.kt" >}}
