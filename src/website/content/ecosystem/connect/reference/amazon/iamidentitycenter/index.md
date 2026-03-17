---
category: Reference
type: ecosystem
ecosystem: http4k Connect
title: "AWS: IAM Identity Center"
description: Feature overview of the http4k Connect AWS IAM Identity Center modules
---

```kotlin
dependencies {
    {{< http4k_bom >}}
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

{{< kotlin file="fake_oidc.kt" >}}

## SSO

Actions:
* SSO: GetFederatedCredentials

### Default Fake port: 25813

To start:

{{< kotlin file="fake_sso.kt" >}}

## Interactive CLI login

The module provides a CredentialsProvider to do interactive login to

{{< kotlin file="interactive_login.kt" >}}
