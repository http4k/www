---
title: "TDDing http4k Part 2: Adding an endpoint"
description: A step-by-step guide to TDDing a simple http4k application
weight: 2
series: TDDing http4k
---

Starting with another EndToEnd test, we can then drill-down into the functional behaviour of the system by introducing
OCT (Out of Container) tests and converting the e2e test to just test endpoint wiring (so far). The common assertions have
also been converted to reusable extension methods on Response.

### Requirements:
- Implement an "add" service, which will sum a number of integer values.

### Tests:

{{< kotlin file="tests.kt" >}}

### Production:

{{< kotlin file="project.kt" >}}

Next: [Part 3: Adding another endpoint](/tutorial/tdding_http4k/part3/)
