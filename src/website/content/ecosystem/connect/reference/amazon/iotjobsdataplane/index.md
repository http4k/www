---
category: Reference
type: ecosystem
ecosystem: http4k Connect
title: "AWS: IoT Jobs Data Plane"
description: Feature overview of the http4k Connect AWS IoT Jobs Data Plane modules
---

```kotlin
dependencies {
    {{< http4k_bom >}}
    implementation("org.http4k:http4k-connect-amazon-iotjobsdataplane")
    implementation("org.http4k:http4k-connect-amazon-iotjobsdataplane-fake")
}
```


The IoT Jobs Data Plane connector is the device side of Jobs - the API a Thing uses to find out what work it has been
given and to report back on it. It provides the following Actions:

     *  DescribeJobExecution
     *  GetPendingJobExecutions
     *  StartNextPendingJobExecution
     *  UpdateJobExecution

The client APIs utilise the `http4k-platform-aws` module for request signing, which means no dependencies on the incredibly fat
Amazon-SDK JARs. This means this integration is perfect for running Serverless Lambdas where binary size is a
performance factor.

The service signs as `iot-jobs-data` rather than `iot`, and is addressed at
`https://data.jobs.iot.<region>.amazonaws.com` - derived from the Region, but overridable for accounts using a
custom endpoint.

`DescribeJobExecution` accepts the reserved `JobId.NEXT` (`$next`) to peek at the job the device would be given
next. It is read-only, so it is safe to poll on every connect, unlike `StartNextPendingJobExecution` which claims
the execution.

This module is the device half of a pair: the cloud-side API which creates the Jobs lives in
[IoT Core](/ecosystem/connect/reference/amazon/iot/). `FakeIotJobsDataPlane` takes the same `Storage<StoredJob>` as `FakeIot`, so one store passed
to both gives the control plane and the device API a single jobs state to work against.

### Example usage

{{< kotlin file="example.kt" >}}

### Default Fake port: 34570

To start:

{{< kotlin file="fake.kt" >}}
