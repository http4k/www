---
category: Reference
type: ecosystem
ecosystem: http4k Connect
title: "AWS: Simple Queue Service"
description: Feature overview of the http4k Connect AWS Simple Queue Service modules
---

```kotlin
dependencies {
    {{< http4k_bom >}}
    implementation("org.http4k:http4k-connect-amazon-sqs")
    implementation("org.http4k:http4k-connect-amazon-sqs-fake")
}
```


The SQS connector provides the following Actions:

     *  CreateQueue
     *  DeleteMessage
     *  DeleteQueue
     *  GetQueueAttributes
     *  ListQueues
     *  ReceiveMessage
     *  SendMessage

The client APIs utilise the `http4k-platform-aws` module for request signing, which means no dependencies on the incredibly fat
Amazon-SDK JARs. This means this integration is perfect for running Serverless Lambdas where binary size is a
performance factor.

### Example usage

{{< kotlin file="example.kt" >}}

Note that the FakeSQS is only suitable for very simple scenarios (testing and deployment for single consumer only) and
does NOT implement real SQS semantics such as VisibilityTimeout or maximum number of retrieved messages (it delivers all
undeleted messages to each consumer). Fake SQS queues are, as such, all inherently FIFO queues.

### Default Fake port: 37391

To start:

{{< kotlin file="fake.kt" >}}
