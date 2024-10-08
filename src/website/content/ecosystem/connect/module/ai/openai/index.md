---
category: Reference
type: ecosystem
ecosystem: http4k Connect
title: "AI: OpenAI"
description: Feature overview of the http4k Connect OpenAI modules
---

### Installation

```kotlin
dependencies {
    {{< http4k_connect_bom >}}
    implementation("org.http4k:http4k-connect-ai-openai")
    implementation("org.http4k:http4k-connect-ai-openai-fake")
}
```

The http4k-connect OpenAI integration provides:
- OpenAI API Client
- FakeOpenAI server which can be used as testing harness

## OpenAI API connector

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

Other examples can be found [here](https://github.com/http4k/http4k-connect/tree/master/ai/openai/fake/src/examples/kotlin).

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
