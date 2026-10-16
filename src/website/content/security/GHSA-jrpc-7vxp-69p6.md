---
title: "GHSA-jrpc-7vxp-69p6: reverseProxy() defaulted to substring Host matching"
description: reverseProxy() matched virtual hosts by substring on the Host header by default, so a public-facing deployment could route Host admin.evil.com to a vhost configured as admin.
severity: LOW
GHSA: GHSA-jrpc-7vxp-69p6
advisory: https://github.com/http4k/http4k/security/advisories/GHSA-jrpc-7vxp-69p6
date: 2026-06-16
---

### Description

`reverseProxy()` and `reverseProxyRouting()` matched configured vhosts by substring on the `Host` header (the `Contains` matcher) by default. The intended use of these functions is outbound dispatch (e.g. matching AWS service subdomains) and test-time composition of fake backend networks, where the `Host` is set by the calling application rather than an attacker, so the loose match has no exploit surface.

If `reverseProxy()` was deployed as a public-facing inbound HTTP handler with two or more virtual hosts, an external attacker could send `Host: admin.evil.com` and reach a vhost configured as `admin`, bypassing routing-based authorization.

The fix changes the default matcher to `Exact`. Callers that genuinely need substring matching must now pass `matcher = Contains` explicitly.

### Affected http4k modules & versions

- `http4k-core`
    - 6.48.x and below
    - 5.41.0.0 and below
    - 4.50.0.0 and below

### Mitigation

Users of affected versions should upgrade to the corresponding fixed version as below. http4k EE subscribers can access the fixed LTS versions through the dedicated http4k Maven instance. For more details about the http4k EE LTS versions, please contact [http4k enterprise support](mailto:enterprise@http4k.org).

| Version     | Fixed Version | Availability                                  |
|-------------|---------------|-----------------------------------------------|
| < 6.49.0.0  | 6.49.0.0      | Community & [Enterprise Support](/enterprise) |
| <= 5.41.0.0 | 5.42.0.0      | [Enterprise Support Only](/enterprise)        |
| <= 4.50.0.0 | 4.51.0.0      | [Enterprise Support Only](/enterprise)        |

Older, unsupported versions are also affected.

For deployments that cannot upgrade immediately, wrap `reverseProxy()` with a host allow-list filter that requires an exact match against expected vhost names before delegating.

### Resolution timeline

| Date/time (UTC)  | Notes                                                                                                        |
|------------------|--------------------------------------------------------------------------------------------------------------|
| 03/07/2025 18:58 | Matcher concept introduced with `Contains` as default (commit `0121b05537`)                                  |
| 27/05/2026 21:21 | Discovered during the Claude-assisted security review pass; default changed to `Exact` (commit `54c6385615`) |
| 30/05/2026 12:50 | Fix released in http4k CE v6.49.0.0 and http4k EE LTS v5.42.0.0 / v4.51.0.0                                  |
| 01/06/2026 16:19 | Advisory `GHSA-jrpc-7vxp-69p6` opened on GitHub                                                              |
| 16/06/2026 14:06 | Public disclosure                                                                                            |
