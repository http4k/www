---
layout: module
type: module
ecosystem: connect
title: Google Analytics UA
description: Feature overview of the http4k Connect Google Analytics UA modules
---

#### Installation

```kotlin
dependencies {
    implementation(platform("org.http4k:http4k-connect-bom:5.22.1.0"))
    implementation("org.http4k:http4k-connect-google-analytics-ua")
    implementation("org.http4k:http4k-connect-google-analytics-ua-fake")
}
```
The GA connector provides the following Actions:

     *  PageView
     *  Event

### Default Fake port: 35628

To start:

```
FakeGoogleAnalytics().start()
```

