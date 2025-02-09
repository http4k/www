---
layout: howto
title: "Migrate from http4k 5.x to 6.x"
description: A user guide to upgrading from http4k 5.x to 6.x
tags: [ "http4k Core", "http4k Connect" ]
---

## About http4k versioning

http4k follows a strict 4-point semantic versioning policy:

# `A.B.C.D`

where...
- **A** = **Major** version - breaking changes and deprecation removals
- **B** = **Breaking** version - breaking API or behavioral changes
- **C** = **Enhancement** version - new features and library updates
- **D** = **Patch** version - bug fixes

This means that we intend to only introduce breaking changes in the A and B releases. This allows users to predictably
upgrade at their own pace in the knowledge that they will not encounter any unexpected breaking changes.

## Recommended approach

The migration from v5 to v6 is a **A** release, which means that there are breaking changes. We suggest the following
method:

1. Look carefully at the list of breaking changes between your current version and the latest http4k version in the
   [CHANGELOG](https://www.http4k.org/ecosystem/changelog/) so you have some scope of the task.
2. Ensure that your code is covered by tests, run your build!
3. Upgrade your minimum Java version to 21, as per the new minimum Java version. If you still require access to security
   upgrades for Java 8-20 support, please see details of our LTS programme available
   in [http4k Enterprise Edition](https://http4k.org/enterprise/).
4. Upgrade your http4k dependencies to the latest v5 release - this is v5.47.0.0.
5. Deal with any deprecations - each change should have an "replace with" that can be applied using the IDE.
6. Migrate any module coordinate changes in the v5 release. You can refer to the module migration guide below.
7. Run your build!
8. Upgrade to the latest v6x.x.x release.
9. Deal with any further deprecations and breaking changes. Most of these are likely to be in the form of repackaging,
   so should be easy to fix.
10. Run your build one last time.
11. Celebrate!

If you have any need of migration support from the http4k team, please feel free to [get in touch](mailto:enterprise@http4k.org) or ask in
the [http4k Slack channel](https://slack.http4k.org/).

## Module migration

As a part of the V6 release, several modules have been rehoused to new Maven coordinates within http4k Community
edition (and retaining the Apache2 license). In preparation, we have introduced these modules to late version of v5 to
give users the time to migrate without taking on-board other breaking changes from v6 .

| SOURCE MODULE - v5.X.X.X   | DESTINATION MODULE(S) - v6.X.X.X                                    |
|----------------------------|---------------------------------------------------------------------|
| http4k-aws                 | http4k-platform-aws                                                 |
| http4k-azure               | http4k-platform-azure                                               |
| http4k-cloudevents         | http4k-api-cloudevents                                              |
| http4k-cloudnative         | Split into http4k-config, http4k-platform-core, http4k-platform-k8s |
| http4k-contract            | http4k-api-openapi                                                  |
| http4k-contract-jsonschema | http4k-api-jsonschema                                               |
| http4k-contract-ui-redoc   | http4k-api-ui-redoc                                                 |
| http4k-contract-ui-swagger | http4k-api-ui-swagger                                               |
| http4k-failsafe            | http4k-ops-failsafe                                                 |
| http4k-gcp                 | http4k-platform-gcp                                                 |
| http4k-graphql             | http4k-api-graphql                                                  |
| http4k-htmx                | http4k-web-htmx                                                     |
| http4k-jsonrpc             | http4k-api-jsonrpc                                                  |
| http4k-metrics-micrometer  | http4k-ops-micrometer                                               |
| http4k-opentelemetry       | http4k-ops-opentelemetry                                            |
| http4k-resilience4j        | http4k-ops-resilience4j                                             |

