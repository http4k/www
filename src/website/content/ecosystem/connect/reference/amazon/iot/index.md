---
category: Reference
type: ecosystem
ecosystem: http4k Connect
title: "AWS: IoT Core"
description: Feature overview of the http4k Connect AWS IoT Core modules
---

```kotlin
dependencies {
    {{< http4k_bom >}}
    implementation("org.http4k:http4k-connect-amazon-iot")
    implementation("org.http4k:http4k-connect-amazon-iot-fake")
}
```


The IoT Core connector covers the control plane - the cloud-side API for managing Jobs and Streams. It provides
the following Actions:

     *  CancelJob
     *  CreateJob
     *  CreateStream
     *  DeleteJob
     *  DeleteStream
     *  DescribeEndpoint
     *  DescribeJob
     *  DescribeJobExecution
     *  DescribeStream
     *  ListJobExecutionsForThing
     *  ListStreams
     *  UpdateStream

The client APIs utilise the `http4k-platform-aws` module for request signing, which means no dependencies on the incredibly fat
Amazon-SDK JARs. This means this integration is perfect for running Serverless Lambdas where binary size is a
performance factor.

Jobs carry an inline JSON `document` - `documentSource` (an S3 link instead of an inline document) is not supported,
and neither are the rollout, retry, abort and scheduling configs. Streams are the mechanism devices use to pull files
over MQTT.

This module is the cloud half of a pair: the device half lives in
[IoT Jobs Data Plane](/ecosystem/connect/reference/amazon/iotjobsdataplane/), and the messaging and Thing Shadow APIs are in
[IoT Data Plane](/ecosystem/connect/reference/amazon/iotdataplane/). `FakeIot` takes a `Storage<StoredJob>`, so passing the same store to
`FakeIotJobsDataPlane` gives both sides of a Job a single state to work against.

### Example usage

{{< kotlin file="example.kt" >}}

### Default Fake port: 50270

To start:

{{< kotlin file="fake.kt" >}}
