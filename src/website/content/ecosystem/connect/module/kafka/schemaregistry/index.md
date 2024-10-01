---
layout: module
type: module
ecosystem: connect
title: Kafka Schema Registry
description: Feature overview of the http4k Connect Kafka Schema Registry modules
---

```kotlin
dependencies {
    implementation(platform("org.http4k:http4k-connect-bom:5.22.1.0"))
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

```
FakeSchemaRegistry().start()
```
