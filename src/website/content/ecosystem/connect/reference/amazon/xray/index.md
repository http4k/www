---
category: Reference
type: ecosystem
ecosystem: http4k Connect
title: "AWS: X-Ray"
description: Feature overview of the http4k Connect AWS X-Ray modules
---

```kotlin
dependencies {
    {{< http4k_bom >}}
    implementation("org.http4k:http4k-connect-amazon-xray")
    implementation("org.http4k:http4k-connect-amazon-xray-fake")
}
```


The X-Ray connector covers the read APIs used to query traces which have already been recorded. It provides the
following Actions:

     *  BatchGetTraces
     *  GetTraceSummaries

The client APIs utilise the `http4k-platform-aws` module for request signing, which means no dependencies on the incredibly fat
Amazon-SDK JARs. This means this integration is perfect for running Serverless Lambdas where binary size is a
performance factor.

Segment documents are returned as the raw JSON that X-Ray stores, so callers parse whichever fields they care about.
The root-cause structures of a trace summary are not modelled. As at the real service, `BatchGetTraces` accepts at most
5 trace ids per call.

### Example usage

{{< kotlin file="example.kt" >}}

## # Fake

The Fake is backed by a `Storage<StoredTrace>`, so traces can be seeded directly into the store for tests to query.
It evaluates `annotation.<key> = "<value>"` filter expressions and refuses any other expression, rather than
silently answering with every trace in the window.

### Default Fake port: 51591

To start:

{{< kotlin file="fake.kt" >}}
