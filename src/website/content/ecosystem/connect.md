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

## Want to add a new API Client or Storage backend?

### Notes for adding a new Client & Fake
- Use the `Example` project client and fake as a template module.
- The naming of the modules is: `http4k-connect-<vendor>-<system>`. We are also grouping the systems by vendor in directory structure. To add the modules in the right place in `settings.gradle.kts` use the functions provided.
- The work for adding other `http4k-connect` Gradle dependencies is already done in the core `build.gradle` file. You just need to add external dependencies into the module gradle file if there are any. If not, feel free to omit it.
- Fakes should extend `ChaoticHttpHandler`, which adds in the `misbehave()` and `behave()` functions to enable the Chaotic behaviour.
- Each Fake should implement the `FakeSystemContract`.
- Tests against external systems should be added wherever possible to prove the contracts are in place, or adding Docker* setup to run them.  *This is work in progress.

### Notes for adding Storage implementations
- The naming of the modules is: `http4k-connect-storage-<type>`. To add the module in the right place in `settings.gradle.kts` use the function provided.
- There is a contract `StorageContract` to prove that the implementation.
- Testcontainers can be used to prove out testing for various storage backends
