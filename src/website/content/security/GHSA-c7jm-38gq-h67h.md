---
title: "GHSA-c7jm-38gq-h67h: DigestAuth defaulted to an always-true nonce verifier"
description: ServerFilters.DigestAuth and DigestAuthProvider accepted every nonce by default, so default deployments had no replay protection on Digest authentication.
severity: MODERATE
GHSA: GHSA-c7jm-38gq-h67h
advisory: https://github.com/http4k/http4k/security/advisories/GHSA-c7jm-38gq-h67h
date: 2026-06-16
---

### Description

`ServerFilters.DigestAuth` and the underlying `DigestAuthProvider` both defaulted their `nonceVerifier` parameter to `{ true }` - every nonce was accepted regardless of value, age or prior use. Any deployment using the default configuration had no replay protection: a captured `Authorization: Digest …` response could be replayed indefinitely against the same protected resource.

Any application using `ServerFilters.DigestAuth` or `DigestAuthProvider` with the default `nonceVerifier` is affected. The broken default has been present since `DigestAuthProvider` was introduced (commit `8a52b615b1`, 2021). Exploitation requires the attacker to first capture a valid Digest response.

The fix (`[Break]`) removes the default value for `nonceVerifier` from both `ServerFilters.DigestAuth` and `DigestAuthProvider`, so callers must supply a real verifier explicitly.

### Affected http4k modules & versions

- `http4k-security-digest`
    - 6.47.x and below
    - 5.41.0.0 and below
    - 4.50.0.0 and below

### Mitigation

Users of affected versions should upgrade to the corresponding fixed version as below. http4k EE subscribers can access the fixed LTS versions through the dedicated http4k Maven instance. For more details about the http4k EE LTS versions, please contact [http4k enterprise support](mailto:enterprise@http4k.org).

| Version     | Fixed Version | Availability                                  |
|-------------|---------------|-----------------------------------------------|
| < 6.48.0.0  | 6.48.0.0      | Community & [Enterprise Support](/enterprise) |
| <= 5.41.0.0 | 5.42.0.0      | [Enterprise Support Only](/enterprise)        |
| <= 4.50.0.0 | 4.51.0.0      | [Enterprise Support Only](/enterprise)        |

Older, unsupported versions are also affected.

For deployments that cannot upgrade immediately, explicitly supply a `nonceVerifier` that tracks issued nonces, enforces a TTL, and rejects re-use.

### Resolution timeline

| Date/time (UTC)  | Notes                                                                                             |
|------------------|---------------------------------------------------------------------------------------------------|
| 11/07/2021 20:48 | Vulnerability introduced alongside `DigestAuthProvider` (commit `8a52b615b1`)                     |
| 26/05/2026 18:32 | Discovered during the Claude-assisted security review pass; default removed (commit `4f904b4692`) |
| 27/05/2026 09:07 | Fix released in http4k CE v6.48.0.0 and http4k EE LTS v5.42.0.0 / v4.51.0.0                       |
| 01/06/2026 16:12 | Advisory `GHSA-c7jm-38gq-h67h` opened on GitHub                                                   |
| 16/06/2026 14:06 | Public disclosure                                                                                 |
