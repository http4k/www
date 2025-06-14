---
category: Reference
type: ecosystem
ecosystem: http4k AI
title: "OpenAI"
description: Feature overview of the http4k AI OpenAI modules
---

### Installation

```kotlin
dependencies {
    { { < http4k_bom > } }

    // for the Universal LLM adapter
    implementation("org.http4k:http4k-ai-llm-openai")

    // for the low-level OpenAI API client
    implementation("org.http4k:http4k-connect-ai-openai")

    // for the FakeOpenAI server
    implementation("org.http4k:http4k-connect-ai-openai-fake")
}
```

The http4k-ai OpenAI integrations provide:

- Universal LLM adapter
- Low-level API Client
- FakeOpenAI server which can be used as testing harness for the API Client

## Universal LLM adapter

The Universal LLM adapter converts the http4k LLM interface into the underlying API, allowing you to swap out providers
without changing your application code.

{{< kotlin file="universal_adapter.kt" >}}

It also allows pluggable support for any OpenAI-compatible model provider by implementing the `OpenAICompatibleClient` interface.

## Low-level API Client

The OpenAI connector provides the following Actions:

* GetModels
* ChatCompletion
* CreateEmbeddings
* GenerateImage

New actions can be created easily using the same transport.

The client APIs utilise the OpenAI API Key (Bearer Auth). There is no reflection used anywhere in the library, so
this is perfect for deploying to a Serverless function.

### Example usage

```kotlin
const val USE_REAL_CLIENT = false

fun main() {
    // we can connect to the real service or the fake (drop in replacement)
    val http: HttpHandler = if (USE_REAL_CLIENT) JavaHttpClient() else FakeOpenAI()

    // create a client
    val client = OpenAI.Http(OpenAIToken.of("foobar"), http.debug())

    // all operations return a Result monad of the API type
    val result: Result<Models, RemoteFailure> = client
        .getModels()

    println(result)
}
```

Other examples can be
found [here](https://github.com/http4k/http4k-connect/tree/master/ai/openai/fake/src/examples/kotlin).

## Fake OpenAI Server

The Fake OpenAI provides the below actions and can be spun up as a server, meaning it is perfect for using in test
environments without using up valuable request tokens!

* GetModels
* ChatCompletion
* GenerateImage

### Security

The Fake server endpoints are secured with a BearerToken header, but the value is not checked for anything other than
presence.

### Image generation

Image generation also can be set to either URL or base-64 data return. In the case of URLs, the Fake also doubles as a
webserver for serving the images (so you can request an image and then load it from the server). Resolution PNG images
of 256x/512x/1024x are supported.

### Generation of responses

By default, a random LoremIpsum generator creates chat completion responses for the Fake. This behaviour can be
overridden to generate custom response formats (eg. structured responses) if required. To do so, create instances of
the `ChatCompletionGenerator` interface and return as appropriate.

### Default Fake port: 45674

To start:

```kotlin
FakeOpenAI().start()
```
