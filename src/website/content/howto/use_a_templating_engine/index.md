---
layout: howto
title: "Use a templating engine"
description: Recipes for using server-side templating engines with http4k applications, including hot-reload functionality
---
Example showing how to use the Templating modules - in this case Handlebars, both by standard response manipulation and via a typesafe view lens.

### Gradle setup

```kotlin
dependencies {
    {{< http4k_bom >}}
    implementation("org.http4k:http4k-core")
    implementation("org.http4k:http4k-template-handlebars")
}
```

### Code

{{< kotlin file="example.kt" >}}
