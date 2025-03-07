---
category: Reference
type: ecosystem
ecosystem: http4k Core
title: "Format: JSON"
description: Feature overview of the JSON http4k-format modules, several of which support auto-marshalling into data classes.
---

### Installation (Gradle)

```kotlin
dependencies {
    {{< http4k_bom >}}
 
    // Argo:  
    implementation("org.http4k:http4k-format-argo")

    // Gson:  
    implementation("org.http4k:http4k-format-gson")

    // Jackson: 
    implementation("org.http4k:http4k-format-jackson")

    // Klaxon: 
    implementation("org.http4k:http4k-format-klaxon")

    // KondorJson: 
    implementation("org.http4k:http4k-format-kondor-json")

    // Moshi: 
    implementation("org.http4k:http4k-format-moshi")

    // KotlinX Serialization: 
    implementation("org.http4k:http4k-format-kotlinx-serialization")
}
```

### About
These modules add the ability to use JSON as a first-class citizen when reading from and to HTTP messages. Each 
implementation adds a set of standard methods and extension methods for converting common types into native JSON/XML 
objects, including custom Lens methods for each library so that JSON node objects can be written and read directly from
 HTTP messages:

### Notes on individual module choice
- As a default, we recommend using **Moshi** as an engine for JSON marshalling, as it is the most modern, lightweight and Kotlin-friendly library with automarshalling capabilities. It also has the option of [Kotshi](https://github.com/ansman/kotshi), which allows the compile-time generation of adapters for Kotlin data classes.
- If you are using OpenAPI contracts, the only current option is to use **Jackson**.
- **Kondor** is an excellent choice for manual mapping of classes to JSON, and does not use reflection.
- For the simplest use-cases, **Argo** is a good lightweight non-reflection choice, although it's API is Java-first.
- **GSON** support is provided in http4k, but is not recommended due to being mostly unsupported.
- We have found **KotlinSerialisation** is possibly the least friendly to use in the context of http4k.

#### Code

{{< kotlin file="example.kt" >}}

### Auto-marshalling capabilities

Some of the message libraries (eg. Moshi, Jackson, Kotlin serialization, GSON, XML, CSV ....) provide the mechanism to automatically marshall data objects 
to/from JSON and XML using reflection.

We can use this facility in http4k to automatically marshall objects to/from HTTP message bodies using **Lenses**. Note that this approach also sets the appropriate `Content-Type` header for the message.

#### Code

{{< kotlin file="autoJson.kt" >}}

serializing an object/class for a Response via `Lens.inject()` - this properly sets the `Content-Type` header to `application/json`:
{{< kotlin file="autoJsonResponse.kt" >}}

There is a utility to generate Kotlin data class code for JSON documents [here](http://toolbox.http4k.org/dataclasses). 
These data classes are compatible with using the `Body.auto<T>()` functionality. 

#### FAQ (aka gotchas) regarding Auto-marshalling capabilities

**Q. Where is the `Body.auto` method defined?**

**A.** `Body.auto` is an extension method which is declared on the parent singleton `object` for each of the message libraries that supports auto-marshalling - eg. `Jackson`, `Gson`, `Moshi` and `Xml`. All of these objects are declared in the same package, so you need to add an import similar to:
`import org.http4k.format.Jackson.auto`

**Q. Jackson: The Data class auto-marshalling is not working correctly when my JSON fields start with capital letters**

**A.** Because of the way in which the Jackson library works, uppercase field names are NOT supported. Either switch out to use `http4k-format-gson` (which has the same API), or annotate your Data class with `@JsonNaming(PropertyNamingStrategy.UpperCamelCaseStrategy.class)` or the fields with `@JsonAlias` or to get it work correctly.

**Q. Jackson: Boolean properties with names starting with "is" do not marshall properly**

**A.** This is due to the way in which the Jackson `ObjectMapper` is configured. Annotation of the fields in question should help, or using `ObjectMapper.disable(MapperFeature.AUTO_DETECT_IS_GETTERS)`

**Q. Moshi: Declared with `Body.auto<List<XXX>>().toLens()`, my auto-marshalled List doesn't extract properly!**

**A.** This occurs in Moshi when serialising bare lists to/from JSON and is to do with the underlying library being lazy in deserialising objects (using LinkedHashTreeMap) ()). Use `Body.auto<Array<MyIntWrapper>>().toLens()` instead. Yes, it's annoying but we haven't found a way to turn if off.

**Q. Kotlin Serialization: the standard mappings are not working on my data classes.**

**A.** This happens because http4k adds the standard mappings to Kotlin serialization as contextual serializers. This can be solved by marking the fields as `@Contextual`.

**Q. Gson: the data class auto-marshalling does not fail when a null is populated in a Kotlin non-nullable field**

**A.** This happens because http4k uses straight GSON demarshalling, of JVM objects with no-Kotlin library in the mix. The nullability generally gets checked at compile-type and the lack of a Kotlin sanity check library exposes this flaw. No current fix - apart from to use the Jackson demarshalling instead!

This can be demonstrated by the following, where you can see that the output of the auto-unmarshalling a naked JSON is NOT 
the same as a native Kotlin list of objects. This can make tests break as the unmarshalled list is NOT equal to the native list.

As shown, a workaround to this is to use `Body.auto<Array<MyIntWrapper>>().toLens()` instead, and then compare using 
`Arrays.equal()`



{{< kotlin file="list_gotcha.kt" >}}

[http4k]: https://http4k.org
