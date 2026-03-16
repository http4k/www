---
category: Reference
type: ecosystem
ecosystem: http4k Connect
title: Mattermost
description: Feature overview of the http4k Connect Mattermost modules
---

```kotlin
dependencies {
    {{< http4k_bom >}}
    implementation("org.http4k:http4k-connect-mattermost")
}
```

The Mattermost connector provides the following Actions:

- TriggerWebhook

### Example usage

{{< kotlin file="example.kt" >}}

### Default Fake port: 54786

To start:

{{< kotlin file="fake.kt" >}}
