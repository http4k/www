---
layout: howto
title: "Create a custom JSON marshaller"
description: Recipes for using JSON in http4k applications with a variety of popular JSON APIS
tags: [ "http4k Core" ]
---

### Gradle setup

```kotlin
dependencies {
    {{< http4k_bom >}}
    implementation("org.http4k:http4k-core")
    implementation("org.http4k:http4k-format-jackson")
}
```

### Custom auto-mapping JSON configurations

**http4k** declares an extended set of "primitive" types which it can marshall out of the box - this includes the
various http4k primitives (Uri, Status), as well as a bunch of common types from the JDK such as the DateTime classes
and Exceptions. These primitives types cannot be marshalled as top-level JSON structures on their own so should be
contained in a custom wrapper class before transmission.

You can declare your own custom marshaller by reimplementing the Json instance and adding mappings for your own types -
either uni or bi-directional.

This ability to render custom types through different JSON marshallers allows API users to provide different "views" for
different purposes - for example we may wish to hide the values of some fields in the output, as below:

### Example - Representing MicroTypes/TinyTypes as Strings in JSON

MicroTypes (aka Tiny Types) are popular in Kotlin providing type-safety throughout a codebase, ensuring amongst other things that method 
parameters are not accidentally permuted. An example of a simple microtype might be:

{{< kotlin file="microtype.kt" >}}

Using the standard mapper, a `Customer` "Bob", would be represented as the json

{{< kotlin file="microtype_usage.kt" >}}

```json
{
    "name": {
        "value": "Bob"
    }
}
```

However, it might be preferable to represent `CustomerName` as a plain string:

```json
{
    "name": "Bob"
}
```

To achieve this, there are a few simple steps - this example uses Jackson, but there are equivalent configuration
schemes for the other supported JSON libraries

1. Use the http4k `ConfigurableJackson` to get a base configuration

{{< kotlin file="jackson_skeleton.kt" >}}

2. Modify it to meet your needs, registering type adapters for your types

{{< kotlin file="jackson_config.kt" >}}

3. Reference this configuration in your code - particularly where using the `Body.auto<xxx>` pattern

{{< kotlin file="jackson_usage.kt" >}}

### Example - Representing MicroTypes using Values4k as Strings in JSON

This example uses value types from [Values4k](https://github.com/fork-handles/forkhandles/tree/trunk/values4k)

Firstly, define a value type using the standard values4k mechanism - note that the companion
object extends ValueFactory - this will be referenced in the type adapter later. The ValueFactory
also provides a number of convenience methods `CustomerName.of()`, `parse()`, `unwrap()`, and a mechanism
to validate the format of strings - very convenient to ensure that values are semantically valid throughout the entire system.

{{< kotlin file="values4k_type.kt" >}}

Then, define a `ConfigurableJackson` (Moshi...) with a type adaptor for your type

{{< kotlin file="values4k_jackson.kt" >}}

A full worked example is shown below.

#### Code

{{< kotlin file="example.kt" >}}
