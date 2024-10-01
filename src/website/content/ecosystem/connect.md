---
title: http4k Connect
description: http4k Connect is designed to facilitate seamless integration with external systems and services from the client viewpoint. It is optimised for zero-reflection, ideal for native and serverless use. It ships with in-memory fakes for testing without Docker and keeps dependencies minimal for small binary sizes.
ecosystem: http4k Connect
type: ecosystem
weight: 2
---

<img src="https://connect.http4k.org/img/logo-intro.png" class="blogImageMid" alt="http4k connect logo">

http4k Connect is a lightweight API Client toolkit which includes libraries for connecting to popular third-party cloud
services and AI backends using [http4k](https://http4k.org) compatible APIs, along with Fake implementations for usage
during local
testing. These are all underpinned by a variation on the
uniform [Server as a Function](https://monkey.org/~marius/funsrv.pdf) model powered by the `HttpHandler` interface
exposed by [http4k](https://http4k.org), so you can:

1. Take advantage of the simple and powerful SaaF model and APIs used in http4k.
1. Plug everything together completely in-memory and take advantage of this powerful model.
1. Have access to the underlying HTTP clients (and hence add metrics or logging).
1. Run stateful Fake implementations of 3rd party systems locally or in test environments.

Although centered around usage in http4k-based projects, http4k-connect does not require this and the libraries are
usable from any JVM application.

## Rationale

Although convenient, many API Client libraries introduce many heavyweight dependencies or contain a plethora of
non-required functionality, which can have a large effect on binary size. As an alternative, http4k-connect provides
lightweight versions of popular APIs covering standard use-cases.

### Installation

```kotlin
dependencies {
    // install the platform...
    implementation(platform("org.http4k:http4k-connect-bom:5.21.0.0"))

    // ...then choose an API Client
    implementation("org.http4k:http4k-connect-amazon-s3")

    // ...a fake for testing
    testImplementation("org.http4k:http4k-connect-amazon-s3-fake")

    // ...and a storage backend (optional)
    testImplementation("org.http4k:http4k-connect-storage-redis")
}
```

<div class="github">
The main documentation has moved to the http4k Connect [site](https://connect.http4k.org)
</div>

## Supported APIs and Fakes:

See the [Module Reference](https://connect.http4k.org/guide/reference/) for the complete list of supported services.

<div class="github">
- [Example Template](./example) -> `"org.http4k:http4k-connect-example"` / `"org.http4k:http4k-connect-example-fake"`
</div>

### Want to add a new API Client or Storage backend?

Read the [guide](https://connect.http4k.org/contributing/).