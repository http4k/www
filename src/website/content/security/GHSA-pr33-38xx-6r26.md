---
title: "GHSA-pr33-38xx-6r26: BasicCookieStorage did not enforce RFC 6265 cookie scoping"
description: BasicCookieStorage ignored cookie domain, path and Secure scoping, so a client talking to multiple origins could leak cookies across domains or send Secure cookies over plain HTTP.
severity: MODERATE
GHSA: GHSA-pr33-38xx-6r26
advisory: https://github.com/http4k/http4k/security/advisories/GHSA-pr33-38xx-6r26
date: 2026-06-16
---

### Description

`BasicCookieStorage` did not enforce the RFC 6265 scoping rules around cookie domain, path and the `Secure` attribute. A client using a single storage instance to talk to multiple origins could have cookies leak across domains, or have `Secure` cookies sent over plain HTTP.

Any client using `BasicCookieStorage` with cookies for more than one origin or scheme is affected. Single-origin uses are unaffected.

The fix introduces `DefaultCookieStorage` (RFC 6265 compliant) as the drop-in default. `BasicCookieStorage` is renamed `InsecureCookieStorage` and remains available for callers with a deliberate need for the old behaviour.

### Affected http4k modules & versions

- `http4k-core`
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

For deployments that cannot upgrade immediately, use a dedicated `BasicCookieStorage` instance per origin and scheme, or switch to a separate RFC 6265-compliant cookie store.

### Resolution timeline

| Date/time (UTC)  | Notes                                                                                                      |
|------------------|------------------------------------------------------------------------------------------------------------|
| 26/05/2026 19:38 | Discovered during the Claude-assisted security review pass; cookie storage rewritten (commit `6a9b44d743`) |
| 27/05/2026 09:07 | Fix released in http4k CE v6.48.0.0                                                                        |
| 01/06/2026 16:17 | Advisory `GHSA-pr33-38xx-6r26` opened on GitHub                                                            |
| 16/06/2026 14:06 | Public disclosure                                                                                          |
