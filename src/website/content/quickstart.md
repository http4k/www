---
title: Quickstart
description: How to get started quickly with http4k - your way!
---

Depending on your learning situation, there are a number of options to get started with **http4k**...

# Help - I'm new and scared!

**Follow a tutorial**: There is a step-by-step [beginner tutorial](/tutorial/your_first_http4k_app/). This will get you up and running with a basic buildable project.

# I know what I'm doing but would like a helping hand...

## From within the IDE
You can create new **http4k** projects directly from IntelliJ by installing our http4k IntelliJ plugin from the [JetBrains Marketplace](https://plugins.jetbrains.com/plugin/25243-http4k-project-wizard/edit) or directly from the IDE itself. This will allow you to generate a fully set-up http4k project for
either Server or Serverless platforms, including Gradle files and using any of the 150+ http4k ecosystem modules.

## OR... 
## Using a friendly web GUI
We have developed a set of useful tools for Developers working with the **http4k** toolset to turbo-charge app development. Collectively, this is known as the <a href="https://toolbox.http4k.org">http4k Toolbox</a>. These tools include:

- A **Project Wizard** that generates entire bootstrap Server and Serverless project source folders - including
  fully working starter code, build tooling, extra modules and packaging options.
- From **OpenAPI v2 & V3** specification JSON/YAML, generate an entire working **http4k** Server, Client and Model objects (generated from JSON Schema) in with either of the standard and [http4k-connect](https://connect.http4k.org) client styles.
- **Generate Kotlin Data Class** definitions from an inputted JSON, YAML, or XML document.
- **Convert any HTTP request to http4k code** - a bit like "convert to CURL" but with your favourite HTTP toolkit!

# I've got a project and want to add http4k to it
**Add http4k into an existing project**: This simple example demonstrates how to serve and consume HTTP services using **http4k**. To install, add these dependencies to your **Gradle** file:

```kotlin
dependencies {
    {{< http4k_bom >}}
    implementation("org.http4k:http4k-core")
    implementation("org.http4k:http4k-server-undertow")
    implementation("org.http4k:http4k-client-apache")
}
```

The following creates a simple endpoint, binds it to a Undertow server then starts, queries, and stops it.

{{< kotlin file="example.kt" >}}

# I want to see what http4k can do!
We have a page dedicated to how **http4k** can be used in a variety of scenarios. See [http4k In Action](/in-action).
