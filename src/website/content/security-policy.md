---
title: Security Policy
description: Details of the http4k security policy
---

#### How to Report Potential Security Vulnerabilities

If you discover any potential security vulnerabilities in the http4k ecosystem, please report them through GitHub's
private vulnerability reporting feature.

#### Viewing Security Vulnerabilities

All identified and resolved security vulnerabilities will be made available publicly at https://http4k.org/security/.

#### Guidelines for Reporting a Vulnerability

If you think you've found a security vulnerability in http4k, please follow the process described above. Below are some
examples to help clarify what might be considered a vulnerability and what is typically not.

#### Examples of Vulnerabilities

For specific examples of vulnerabilities, refer to our dedicated security page at https://http4k.org/security/.

#### Examples of Non-Vulnerabilities

##### Unsafe Deserialization

In the context of http4k, deserialization itself is not considered a security vulnerability unless http4k passes data
from an untrusted source (such as HTTP parameters) into a deserialization method in a way that results in a security
issue, like a CVE. While deserialization of arbitrary types is necessary in many applications, it must be done securely,
and it is up to developers to ensure that this process is safe within their own codebases.

#### Vulnerabilities in Dependencies

If you discover vulnerabilities in http4k's dependencies, these should be reported directly to the respective project
maintainers.

#### Vulnerable Dependency Versions

The http4k team strives to keep dependencies updated, even if a specific version has no vulnerabilities. However, we do
not consider the inclusion of a vulnerable dependency in http4k itself to be a vulnerability, as developers are free to
override dependency versions. It’s the responsibility of the dependency maintainers to release updates with security
patches, and we will incorporate these changes in subsequent http4k releases.
