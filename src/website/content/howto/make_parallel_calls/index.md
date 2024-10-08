---
layout: howto
title: "Make HTTP calls in parallel"
description: Recipe to make HTTP calls in parallel using a ThreadPoolExecutor
tags: [ "http4k Core" ]
---

There are cases where an application needs to make multiple HTTP calls to other services as part of handling a particular request. 
As a general rule-of-thumb, we recommend people to avoid [premature optimisation], however sometimes the quantity of calls or performance of other services demand those to be executed in parallel.

In this example, we show how to use the extension function on [ExecutionService] to manage multiple HTTP calls in parallel, and synchronise their results to produce a single response.

This recipe also covers how to make [distributed tracing] work when tracing information is consumed by multiple threads.

{{< kotlin file="example.kt" >}}

[premature optimisation]: https://wiki.c2.com/?PrematureOptimization
[distributed tracing]: /howto/monitor_http4k/
[ThreadPoolExecutor]: https://www.baeldung.com/thread-pool-java-and-guava
