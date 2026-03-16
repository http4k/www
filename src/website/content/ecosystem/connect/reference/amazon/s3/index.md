---
category: Reference
type: ecosystem
ecosystem: http4k Connect
title: "AWS: S3"
description: Feature overview of the http4k Connect AWS S3 modules
---

```kotlin
dependencies {
    {{< http4k_bom >}}
    implementation("org.http4k:http4k-connect-amazon-s3")
    implementation("org.http4k:http4k-connect-amazon-s3-fake")
}
```


The S3 connector consists of 2 interfaces:

- `S3` for global operations, providing the following Actions:

    * CreateBucket
    * HeadBucket
    * ListBuckets

- `S3Bucket` for bucket level operations, providing the following Actions:

    * CopyObject
    * CreateObject
    * DeleteBucket
    * DeleteObject
    * DeleteObjectTagging
    * GetObject
    * GetObjectTagging
    * HeadObject
    * ListObjectsV2
    * PutObject
    * PutObjectTagging
    * RestoreObject

### Example usage

{{< kotlin file="example.kt" >}}

The client APIs utilise the `http4k-platform-aws` module for request signing, which means no dependencies on the incredibly fat
Amazon-SDK JARs. This means this integration is perfect for running Serverless Lambdas where binary size is a
performance factor.

### How the Fake works with bucket-level operations

S3 is a bit of a strange beast in that it each bucket gets its own virtual hostname. This makes running a Fake an
interesting challenge without messing around with DNS and hostname files.

This implementation supports both global and bucket level operations by inspecting the subdomain of the X-Forwarded-For
header, which is populated by the S3 client built into this module.

In the case of a missing header (if for instance a non-http4k client attempts to push some data into it without the
x-forwarded-for header), it creates a global bucket which is then used to store all of the data for these unknown
requests.

### Default Fake ports:

- Global: default port: 26467
- Bucket: default port: 42628

{{< kotlin file="fake.kt" >}}

### Connecting to a local S3 emulator

Services like [LocalStack](https://docs.localstack.cloud/user-guide/aws/s3/) or
[MinIO](https://min.io/docs/minio/container/index.html) can emulate AWS services locally.
However, for S3 bucket operations you either need to use a specific pre-configured bucket hostname 
like `http://<bucket-name>.s3.localhost.localstack.cloud:4566`, or you configure the `S3Bucket` to always 
perform path-style requests like this:

```kotlin
val s3Bucket = S3Bucket.Http(
    bucketName = bucketName, 
    bucketRegion = region,
    credentialsProvider = { credentials },
    overrideEndpoint = Uri.of("http://localhost:4566"),
    forcePathStyle = true // always use path-style requests
)
```

### Pre-Signed Requests

Http4k supports pre-signed requests with the generic `AwsRequestPreSigner` class.
However, `http4k-connect` provides a simplified interface for common S3 Bucket operations with the `S3BucketPresigner`.

```kotlin
fun main() {    
    // create pre-signer
    val preSigner = S3BucketPreSigner(
        bucketName = BucketName.of("foobar"),
        region = Region.of("us-east-1"),
        credentials = AwsCredentials("accessKeyId", "secretKey")
    )

    val key = BucketKey.of("keyName")
    
    // create a pre-signed PUT
    val put = preSigner.put(
        key = key,
        duration = Duration.ofMinutes(5), // how long the URL is valid for
        headers = listOf("content-type" to "application.json")  // add optional signed headers
    )
    println(put.uri)
    
    // create a pre-signed GET
    val get = preSigner.get(
        key = key,
        duration = Duration.ofMinutes(5)
    )
    println(get)

    // share these URIs to your clients so they can perform the operations without credentials
}
```
