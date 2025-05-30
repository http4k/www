---
category: Reference
type: ecosystem
ecosystem: http4k Connect
title: "Storage: S3"
description: Feature overview of the http4k Connect S3 Storage module
---

### Installation 

```kotlin
dependencies {
    {{< http4k_bom >}}
    implementation("org.http4k:http4k-connect-storage-s3")
}
```


This implementation uses the http4k Connect adapter to store the data in S3. All data is serialised to disk by
passing it though an http4k AutoMarshalling adapter (see the `http4k-format-XXX` modules). In the example below we use a
JSON adapter backed by Moshi (which is the default).

```kotlin

data class AnEntity(val name: String)

val awsCredentials = AwsCredentials("accessKey", "secret")
val bucketClient = S3Bucket.Http(BucketName.of("foobar"), Region.AP_EAST_1, { awsCredentials }, JavaHttpClient(), Clock.systemUTC())

val storage = Storage.S3(bucketClient, Moshi)

storage["myKey"] = AnEntity("hello")

println(storage["myKey"])

storage.removeAll("myKey")
```
