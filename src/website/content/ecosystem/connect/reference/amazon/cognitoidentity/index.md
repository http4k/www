---
category: Reference
type: ecosystem
ecosystem: http4k Connect
title: "AWS: Cognito Identity"
description: Feature overview of the http4k Connect AWS Cognito Identity modules
---

```kotlin
dependencies {
    {{< http4k_bom >}}
    implementation("org.http4k:http4k-connect-amazon-cognitoidentity")
    implementation("org.http4k:http4k-connect-amazon-cognitoidentity-fake")
}
```


The Cognito Identity connector covers the Identity Pool APIs which exchange a set of logins for temporary AWS
credentials. It provides the following Actions:

     *  GetCredentialsForIdentity
     *  GetId

The client APIs utilise the `http4k-platform-aws` module for request signing, which means no dependencies on the incredibly fat
Amazon-SDK JARs. This means this integration is perfect for running Serverless Lambdas where binary size is a
performance factor.

The returned `TemporaryCredentials` convert to http4k `AwsCredentials` with `asHttp4k()`, so they can be passed
straight to another http4k Connect client as its `CredentialsProvider`.

Note that this is the Identity Pool API - User Pools are covered by [Cognito](/ecosystem/connect/reference/amazon/cognito/).

### Example usage

{{< kotlin file="example.kt" >}}

## # Fake

The Fake is backed by a `Storage<StoredIdentity>` and issues one identity per pool and set of logins, as the real
service does - so repeating a `GetId` with the same logins returns the same `IdentityId`. `GetCredentialsForIdentity`
returns fixed credentials with a configurable expiry, and rejects an unknown identity with a
`ResourceNotFoundException`.

### Default Fake port: 15167

To start:

{{< kotlin file="fake.kt" >}}
