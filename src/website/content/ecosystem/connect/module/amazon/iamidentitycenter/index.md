---
layout: module
type: module
ecosystem: http4k Connect
title: "AWS: IAM Identity Center"
description: Feature overview of the http4k Connect AWS IAM Identity Center modules
---

```kotlin
dependencies {
    {{< http4k_connect_bom>}}
    implementation("org.http4k:http4k-connect-amazon-iamidentitycenter")
    implementation("org.http4k:http4k-connect-amazon-iamidentitycenter-fake")
}
```


The IAMIdentityCenter connector provides the following Fakes:

## OIDC

Actions:
* RegisterClient
* StartDeviceAuthentication
* CreateToken

### Default Fake port: 34160

To start:

```kotlin
FakeOIDC().start()
```

## SSO

Actions:
* SSO: GetFederatedCredentials

### Default Fake port: 25813

To start:

```kotlin
FakeSSO().start()
```

## Interactive CLI login

The module provides a CredentialsProvider to do interactive login to

```kotlin
val provider = CredentialsProvider.SSO(
    SSOProfile(
        AwsAccount.of("01234567890"),
        RoleName.of("hello"),
        Region.US_EAST_1,
        Uri.of("http://foobar"),
    )
)
```
