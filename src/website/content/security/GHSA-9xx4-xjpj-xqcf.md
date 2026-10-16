---
title: "GHSA-9xx4-xjpj-xqcf: Digest auth-int QoP omitted entity-body integrity"
description: The Digest auth-int digest was computed without hashing the request body, so an on-path attacker could tamper with the body of an authenticated request without invalidating the digest.
severity: MODERATE
GHSA: GHSA-9xx4-xjpj-xqcf
advisory: https://github.com/http4k/http4k/security/advisories/GHSA-9xx4-xjpj-xqcf
date: 2026-10-16
---

### Description

RFC 7616 defines the `auth-int` quality-of-protection to bind the request entity body into the digest: `A2 = method:request-uri:H(entity-body)`. http4k's `DigestEncoder` computed `ha2 = H(method:digestUri)` for both `auth` and `auth-int`, and `DigestAuthProvider.verify` had no access to the body, so selecting `auth-int` changed what was advertised to the peer but not what was actually protected.

An operator configuring `ServerFilters.DigestAuth(..., qop = listOf(Qop.AuthInt), ...)` over a non-TLS channel would be relying on `auth-int` for message integrity. An on-path attacker with no knowledge of the credentials could rewrite the body of an in-flight authenticated request (for example a transfer amount or target account) while leaving the method, URI, nonce and `Authorization` header untouched, and the request would be accepted as the victim's authenticated principal.

Exploitation requires a non-default QoP configuration, a non-TLS channel, and an active man-in-the-middle position.

The fix implements RFC 7616 `auth-int` properly: `DigestEncoder.invoke` gains a required `entityBody` parameter and `DigestAuthProvider.verify` now takes the `Request`, so the server recomputes the digest over the actual received body. Note that `auth-int` requires buffering the request body in memory to hash it.

### Affected http4k modules & versions

- `http4k-security-digest`
    - 4.9.10.0 to 6.59.0.0

### Mitigation

Users of affected versions should upgrade to the corresponding fixed version as below. http4k EE subscribers can access the fixed LTS versions through the dedicated http4k Maven instance. For more details about the http4k EE LTS versions, please contact [http4k enterprise support](mailto:enterprise@http4k.org).

| Version     | Fixed Version | Availability                                  |
|-------------|---------------|-----------------------------------------------|
| <= 6.59.0.0 | 6.60.0.0      | Community & [Enterprise Support](/enterprise) |

For deployments that cannot upgrade immediately, do not rely on `auth-int` for integrity: use TLS for the transport and configure `qop = listOf(Qop.Auth)`.

### Resolution timeline

| Date/time (UTC)  | Notes                                                                              |
|------------------|------------------------------------------------------------------------------------|
| 11/07/2021 20:48 | Vulnerability introduced alongside Digest `auth-int` support (commit `8a52b615b1`) |
| 15/09/2026 20:13 | Reported privately via GitHub Security Advisories by @manus-pi                     |
| 16/09/2026 06:57 | Fix committed (commit `f84d6c50a2`)                                                |
| 16/09/2026 09:08 | Fix released in http4k CE v6.60.0.0                                                |
| 16/10/2026 16:10 | Public disclosure                                                                  |
