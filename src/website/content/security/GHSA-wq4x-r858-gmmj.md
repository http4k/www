---
title: "GHSA-wq4x-r858-gmmj: Thymeleaf template path traversal"
description: A ViewModel template name containing ../ segments could escape baseTemplateDir in ThymeleafTemplates, letting a client read arbitrary files the server process can read.
severity: MODERATE
GHSA: GHSA-wq4x-r858-gmmj
advisory: https://github.com/http4k/http4k/security/advisories/GHSA-wq4x-r858-gmmj
date: 2026-10-16
---

### Description

`ThymeleafTemplates().Caching(baseTemplateDir)` and `.HotReload(baseTemplateDir)` configured a plain Thymeleaf `FileTemplateResolver` with no containment check, and passed `ViewModel.template()` through verbatim. A template name such as `../secretdir/secret` was resolved relative to `baseTemplateDir` as a normal filesystem path, so the contents of any file readable by the server process could be rendered.

An earlier hardening commit (`725f1b9697`) added canonicalize-and-check containment to `Pug4jTemplates.HotReload`, but the equivalent check was never applied to `ThymeleafTemplates`. Handlebars, FreeMarker and Pebble are protected by the underlying libraries at the versions http4k pins.

Applications are only exploitable where some part of the value returned by `ViewModel.template()` (or the `::selector` suffix) is influenced by request data - for example per-tenant, per-locale or per-page template selection driven by a path or query parameter.

The fix makes all file-based template renderers reject template paths that resolve outside the base directory.

### Affected http4k modules & versions

- `http4k-template-thymeleaf`
    - 6.61.0.0 and below

### Mitigation

Users of affected versions should upgrade to the corresponding fixed version as below. http4k EE subscribers can access the fixed LTS versions through the dedicated http4k Maven instance. For more details about the http4k EE LTS versions, please contact [http4k enterprise support](mailto:enterprise@http4k.org).

| Version     | Fixed Version | Availability                                  |
|-------------|---------------|-----------------------------------------------|
| <= 6.61.0.0 | 6.62.0.0-ee   | Community & [Enterprise Support](/enterprise) |

Older, unsupported versions are also affected.

For deployments that cannot upgrade immediately, never derive `ViewModel.template()` from request data, or validate it against a fixed allow-list of template names before rendering.

### Resolution timeline

| Date/time (UTC)  | Notes                                                                  |
|------------------|------------------------------------------------------------------------|
| 12/06/2017 08:02 | `ThymeleafTemplates` introduced (commit `cab8cf85c8`)                  |
| 31/05/2026 17:06 | Containment check added to `Pug4jTemplates` only (commit `725f1b9697`) |
| 15/10/2026 12:55 | Reported privately via GitHub Security Advisories by @baeseungwon1010  |
| 15/10/2026 18:17 | Fix committed (commit `5a06cb6c04`)                                    |
| 15/10/2026 20:40 | Fix released in http4k v6.62.0.0-ee                                    |
| 16/10/2026 16:12 | Public disclosure                                                      |
