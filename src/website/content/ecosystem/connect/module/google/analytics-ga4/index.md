---
layout: module
type: module
ecosystem: http4k Connect
title: "Google: Analytics GA4"
description: Feature overview of the http4k Connect Google Analytics GA4 modules
---

### Installation

```kotlin
dependencies {
    {{< http4k_connect_bom >}}
    implementation("org.http4k:http4k-connect-google-analytics-ga4")
    implementation("org.http4k:http4k-connect-google-analytics-ga4-fake")
}
```

The GA connector provides the following Actions:

     *  PageView
     *  Event

### Default Fake port: 35628

To start:

```kotlin
FakeGoogleAnalytics().start()
```

