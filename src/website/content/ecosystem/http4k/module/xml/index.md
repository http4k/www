---
category: Reference
type: ecosystem
ecosystem: http4k Core
title: XML
description: Feature overview of the modules providing XML support, including an auto-marshalling capability
---


### Installation (Gradle)

```kotlin
dependencies {
    {{< http4k_bom >}}
    // json.org XML:
    implementation("org.http4k:http4k-format-xml")

    // Jackson XML:
    implementation("org.http4k:http4k-format-jackson-xml")
}
```

### About
These modules provide auto-marshalling functionality to convert XML into arbitrary data classes. The 2 differ slightly in their behaviour, due to the underlying libraries used for implementation. We recommend using `http4k-format-jackson-xml` as it has more predictable and consistent behaviour.

### JacksonXml
- Provides bi-directional conversion
- Does NOT expose an XML DOM node model
- Generally requires `Jackson` field annotations to manipulate output format
- Provides extension point to map custom types using BiDiMapping registration (so supports all Java and Http4k primitives such as `Uri`)

#### JacksonXML Code 
- Provides extraction conversion only
- Exposes an XML DOM node model as a first-class citizen - so can read directly from a string into a DOM model
- Does not generate a wrapper element to represent the parent node
- Has trouble with repeating child-elements, depending on zero, one or many elements in the XML. This is due to the underlying library implementation
- Only handles objects with primitive JDK types

{{< kotlin file="jacksonAutoXml.kt" >}}

### Xml
As above, we recommend using `http4k-format-jackson-xml` as it has more predictable and consistent behaviour.
 
#### XML Code 

{{< kotlin file="autoXml.kt" >}}
