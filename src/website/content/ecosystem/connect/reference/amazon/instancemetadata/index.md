---
category: Reference
type: ecosystem
ecosystem: http4k Connect
title: "AWS: Instance Metadata Service"
description: Feature overview of the http4k Connect AWS Instance Metadata Service modules
---

```kotlin
dependencies {
    {{< http4k_bom >}}
    implementation("org.http4k:http4k-connect-amazon-instancemetadata")
    implementation("org.http4k:http4k-connect-amazon-instancemetadata-fake")
}
```


The [Instance Metadata Service](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/ec2-instance-metadata.html) V1 connector provides the following Actions:

     *  GetAmiId
     *  GetHostName
     *  GetInstanceIdentityDocument
     *  GetInstanceType
     *  GetLocalHostName
     *  GetLocalIpv4
     *  GetPublicHostName
     *  GetPublicIpv4
     *  GetSecurityCredentials
     *  ListSecurityCredentials

### Example usage

{{< kotlin file="example.kt" >}}

### Credentials Provider

The Instance Metadata Service also offers a `CredentialsProvider`.
If the application is running inside an Amazon EC2 environment,
this provider can authorize AWS requests using credentials from the instance profile.

{{< kotlin file="example.kt" >}}
:warning: The `Ec2InstanceProfile` provider should always be last in the chain,
since it will time out if not in an Amazon EC2 environment.


### Region Provider ###

The Instance Metadata Service also offers a `RegionProvider`.
If the application is running inside an Amazon EC2 environment,
this provider can detect the current AWS region.

{{< kotlin file="region_provider.kt" >}}

:warning: The `Ec2InstanceProfile` provider should always be last in the chain,
since it will time out if not in an Amazon EC2 environment.


### Default Fake port: 63407

To start:

{{< kotlin file="fake.kt" >}}
