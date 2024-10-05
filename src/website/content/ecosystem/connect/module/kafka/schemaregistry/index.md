---
layout: module
type: module
ecosystem: http4k Connect
title: "Kafka: Schema Registry"
description: Feature overview of the http4k Connect Kafka Schema Registry modules
---

```kotlin
dependencies {
    {{< http4k_connect_bom >}}
    implementation("org.http4k:http4k-connect-kafka-schemaregistry")
    implementation("org.http4k:http4k-connect-kafka-schemaregistry-fake")
}
```

The main `SchemaRegistry` connector provides the following Actions:

- CheckSchemaRegistered
- GetSchemaById
- GetSubjects
- GetSubjectVersion
- GetSubjectVersions
- RegisterSchema

## Fake
The Fake provides the above actions.

### Default Fake port: 41466
To start:

```kotlin
FakeSchemaRegistry().start()
```
