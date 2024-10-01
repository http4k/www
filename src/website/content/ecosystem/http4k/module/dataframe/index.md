---
layout: module
type: module
ecosystem: http4k Core
title: KotlinX DataFrame
description: Feature overview of the DataFrame http4k-format module, allowing for automatic extraction of DataFrames from HTTP messages.
---


### Installation (Gradle)

```kotlin
dependencies {
    implementation(platform("org.http4k:http4k-bom:5.32.1.0"))
    implementation("org.http4k:http4k-format-dataframe")
}
```

### About
This module adds the ability to use [Kotlin DataFrames](https://kotlin.github.io/dataframe) as a first-class citizen when reading from HTTP messages. Extraction from the HTTP message body is done automatically when using a lens with a DataFrame type.

#### Code

{{< kotlin file="example.kt" >}}

[http4k]: https://http4k.org
