---
category: Reference
type: ecosystem
ecosystem: http4k Connect
title: "AWS: IoT Data Plane"
description: Feature overview of the http4k Connect AWS IoT Data Plane modules
---

```kotlin
dependencies {
    {{< http4k_bom >}}
    implementation("org.http4k:http4k-connect-amazon-iotdataplane")
    implementation("org.http4k:http4k-connect-amazon-iotdataplane-fake")
}
```


The IoT Data Plane connector covers the messaging and Thing Shadow APIs - what devices and applications use to talk
to each other through IoT Core. It provides the following Actions:

     *  DeleteConnection
     *  DeleteThingShadow
     *  GetRetainedMessage
     *  GetThingShadow
     *  ListNamedShadowsForThing
     *  ListRetainedMessages
     *  Publish
     *  UpdateThingShadow

The client APIs utilise the `http4k-platform-aws` module for request signing, which means no dependencies on the incredibly fat
Amazon-SDK JARs. This means this integration is perfect for running Serverless Lambdas where binary size is a
performance factor.

Unlike most AWS services, the IoT data endpoint is account-specific (eg. `https://xxxxxxxx-ats.iot.<region>.amazonaws.com`),
so it cannot be derived from the Region and is passed to the client explicitly.

Shadow documents are opaque JSON as far as the API is concerned, so they are sent as raw bytes and returned as an
`InputStream` for the caller to parse with the JSON library of their choosing.

`Publish` exposes the full set of MQTT5 options - `qos`, `retain`, `contentType`, `payloadFormatIndicator`,
`messageExpiry`, `responseTopic`, `correlationData` and `userProperties`.

The Fake records every published message for test assertions, stores Thing Shadows, and serves the retained
messages that its retained publishes create.

The rest of IoT Core lives in sibling modules: the cloud-side control plane for Jobs and Streams in
[IoT Core](/ecosystem/connect/reference/amazon/iot/), and the device side of Jobs in [IoT Jobs Data Plane](/ecosystem/connect/reference/amazon/iotjobsdataplane/).

### Example usage

{{< kotlin file="example.kt" >}}

### Default Fake port: 45592

To start:

{{< kotlin file="fake.kt" >}}
