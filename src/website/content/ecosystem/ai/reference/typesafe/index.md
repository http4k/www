---
category: Reference
type: ecosystem
ecosystem: http4k AI
title: "TypeSafe"
description: Feature overview of the http4k AI TypeSafe modules
---

### Installation

```kotlin
dependencies {

    {{< http4k_bom >}}

    // for the low-level TypeSafe API client
    implementation("org.http4k:http4k-connect-ai-typesafe")

    // for the FakeTypeSafe server
    implementation("org.http4k:http4k-connect-ai-typesafe-fake")
}
```

The http4k-ai TypeSafe integration provides:

- Low-level API Client
- FakeTypeSafe server which can be used as a testing harness for the API Client

## Low-level API Client

The TypeSafe connector provides the following Actions:

* GetModels
* SystemOne

New actions can be created easily using the same transport.

Instead of prompts and free text, the API is driven by typed `Question`s asked about a state object, each of which
returns a matching `Answer`:

| Question | Asks for                              | Answer                                          |
|----------|---------------------------------------|-------------------------------------------------|
| `Choice` | one of a set of named criteria        | the choice, per-criterion probabilities, confidence |
| `Score`  | a position on an ordered scale        | the score, the legend it was scored against, confidence |
| `Noul`   | the truth of a single statement       | a probability                                   |

Questions are answered by the TypeSafe Jev models, whose names are held in `JevModels` - `JevLatest` (the default for
all requests), `JevPreview` and pinned versions such as `Jev_1_13_0`. Pass a different one to any question via the
`model` parameter, and use the `GetModels` action to list what the API currently offers.

The client APIs utilise the TypeSafe API Key (Bearer Auth). There is no reflection used anywhere in the library, so
this is perfect for deploying to a Serverless function.

### Example usage

{{< kotlin file="example.kt" >}}

State and criteria can be your own domain types instead of strings - use `Entry` to convert the JSON in questions and
answers back into them:

{{< kotlin file="typed.kt" >}}

Other examples can be
found [here](https://github.com/http4k/http4k/tree/master/connect/ai/typesafe/fake/src/examples/kotlin).

## Fake TypeSafe Server

The Fake TypeSafe provides the below actions and can be spun up as a server, meaning it is perfect for using in test
environments without using up valuable request tokens!

* GetModels
* SystemOne

### Security

The Fake server endpoints are secured with an API key header, but the value is not checked for anything other than
presence.

### Generation of responses

By default, the Fake serves model cards for `JevLatest` and `JevPreview`, and answers with the first criterion of each
question. This behaviour can be overridden to script
answers (eg. to drive a particular test case) by passing a `QuestionAnswerer` to the Fake, which receives the state and
the question being asked.

### Default Fake port: 61761

To start:

{{< kotlin file="fake.kt" >}}
