---
category: Reference
type: ecosystem
ecosystem: http4k Connect
title: "Kafka: Rest Proxy"
description: Feature overview of the http4k Connect Kafka Rest Proxy modules
---

#### Installation

```kotlin
dependencies {
    {{< http4k_bom >}}
    implementation("org.http4k:http4k-connect-kafka-rest")
    implementation("org.http4k:http4k-connect-kafka-rest-fake")
}
```

There are 2 distinct APIs in the Rest proxy:

## v2

The main `KafkaRest` connector provides the following v2 Actions:

- CreateConsumer
- DeleteConsumer
- GetOffsets
- GetPartitions
- SeekOffsets
- CommitOffsets
- ConsumeRecords
- ProduceMessages
- SubscribeToTopics

In addition, you can use a `KafkaRestConsumer` which provides the following Actions:

- ConsumeRecords
- Delete
- GetOffsets
- SeekOffsets
- CommitOffsets
- SubscribeToTopics

## Record formats

The following formats of Kafka records are supported currently. Partition keys are optional and null by default:

### JSON

All keys and messages will be auto-marshalled to JSON using the standard Moshi instance (which supports most common JDK
types):

{{< kotlin file="records_json_v2.kt" >}}

### AVRO

Support for `GenericContainer` classes (auto-generated from schema). The Key and Value schemas will be extracted from
the Key and Value and sent with the message automatically.

{{< kotlin file="records_avro.kt" >}}

### Binary

Record contents are specified using Base64 type for wire transport:

{{< kotlin file="records_binary_v2.kt" >}}

## Notes on message production

Messages can be sent to the broker with or without PartitionIds. If you want to use a strategy for partitioning, the
`Partitioner` interface can be implemented and used as below. `RoundRobin` and `Sticky` (key-hash % Partitions)
strategies
come out of the box.

{{< kotlin file="produce_v2.kt" >}}

To keep things simple with respect to partition allocation and rebalancing, the above code will fetch the available
partitions on each send to the REST proxy using the `/topics/$topic/partitions` call. This is obviously not very
efficient,
but can be reimplemented as needed using any caching strategy which you might wish to implement.

# v3 (Confluent API)

The main `KafkaRest` connector provides the following v2 Actions:

- GetPartitions
- GetTopic
- GetTopics
- ProduceRecords

## Record formats

The following formats of Kafka records are supported currently. Partition keys are optional and null by default:

### JSON

All keys and messages will be auto-marshalled to JSON using the standard Moshi instance (which supports most common JDK
types):

{{< kotlin file="records_json_v3.kt" >}}

### Binary

Record contents are specified using Base64 type for wire transport:

{{< kotlin file="records_binary_v3.kt" >}}

## Notes on message production

Messages can be sent to the broker with or without PartitionIds. If you want to use a strategy for partitioning, the
`Partitioner` interface can be implemented and used as below. `RoundRobin` and `Sticky` (key-hash % Partitions)
strategies
come out of the box.

{{< kotlin file="produce_v3.kt" >}}

To keep things simple with respect to partition allocation and rebalancing, the above code will fetch the available
partitions on each send to the REST proxy using the `/kafka/v3/clusters/$id/topics/$topic/partitions` call. This is
obviously not very efficient,
but can be reimplemented as needed using any caching strategy which you might wish to implement.

## Fake

The Fake provides the all of the above endpoints listed for v2 and v3, which is enough for basic consumer lifecycle and
production and consumption
of records. Note that consumers by default will start at the start of the topic stream, although they can be committed
to.

"auto.commit.enable" is enabled by default but can be set to "false" for manual committing of offsets.

### Default Fake port: 30091

To start:

{{< kotlin file="fake.kt" >}}
