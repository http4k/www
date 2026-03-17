****---
category: Reference
type: ecosystem
ecosystem: http4k Connect
title: Slack
description: Feature overview of the http4k Connect Slack modules
---

```kotlin
dependencies {
    {{< http4k_bom >}}
    implementation("org.http4k:http4k-connect-slack")
}
```

The Slack connector provides the following Actions:

- Send Channel Message

### Example usage

{{< kotlin file="example.kt" >}}

### Default Fake port: 23660

To start:

{{< kotlin file="fake.kt" >}}
