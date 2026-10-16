---
title: "GHSA-m4w9-hjfw-vwj4: HmacSha256.hash computed a plain unkeyed digest"
description: Despite its Hmac naming, HmacSha256.hash computed a plain unkeyed SHA-256 digest. It is deprecated in favour of the explicit Sha256.hash and Sha256.hmac.
severity: LOW
GHSA: GHSA-m4w9-hjfw-vwj4
advisory: https://github.com/http4k/http4k/security/advisories/GHSA-m4w9-hjfw-vwj4
date: 2026-06-16
---

### Description

The `HmacSha256` class contained two functions:

- `hash(payload)` - a plain unkeyed SHA-256 digest. It takes no key parameter, so it was never an HMAC despite the class name.
- `hmacSHA256(key, data)` - a properly keyed HMAC-SHA256.

A downstream caller who read the class name and used `HmacSha256.hash` as a message authentication code without noticing it takes no key would get no authentication. There is no internal misuse in http4k itself: both production usages of `HmacSha256.hash` (AWS SigV4 canonical-request hashing and `x-amz-content-sha256`) are spec-correct uses of plain SHA-256, and every keyed call passes a real key.

The fix introduces `Sha256.hash(input)` (unkeyed digest) and `Sha256.hmac(key, input)` (keyed HMAC-SHA256). `HmacSha256` is deprecated; existing callers continue to work via deprecation shims.

### Affected http4k modules & versions

- `http4k-core`
    - 6.48.x and below

### Mitigation

Upgrade to the fixed version and migrate to `Sha256.hash` or `Sha256.hmac` according to intent.

| Version    | Fixed Version | Availability                                  |
|------------|---------------|-----------------------------------------------|
| < 6.49.0.0 | 6.49.0.0      | Community & [Enterprise Support](/enterprise) |

If you cannot upgrade and need a real HMAC-SHA256, use `javax.crypto.Mac.getInstance("HmacSHA256")` with a `SecretKeySpec`, or the existing `hmacSHA256(key, data)`, which was always correctly implemented.

### Resolution timeline

| Date/time (UTC)  | Notes                                                                                                     |
|------------------|-----------------------------------------------------------------------------------------------------------|
| 28/05/2026 01:05 | Discovered during the Claude-assisted security review pass; `HmacSha256` deprecated (commit `1aa7e587d1`) |
| 30/05/2026 12:50 | Fix released in http4k CE v6.49.0.0                                                                       |
| 01/06/2026 16:15 | Advisory `GHSA-m4w9-hjfw-vwj4` opened on GitHub                                                           |
| 16/06/2026 14:06 | Public disclosure                                                                                         |
