---
title: Performance
type: performance
tagline: "A thin layer over the metal - and it shows."
description: "The http4k server backends are a very thin adapter over the raw server APIs, so they run at very low overhead compared to the bare server. We'll be straight with you though: our north star is developer experience, not topping a leaderboard. Happily, you rarely have to choose."
stats:
    - TechEmpower Round 22
    - 13/41 JVM composite
    - pluggable backends
bench_intro:
    - "http4k is entered into the [TechEmpower Framework Benchmarks](https://www.techempower.com/benchmarks/) - an independent project that runs frameworks through a series of realistic tests. No custom tuning of the underlying servers: the default app HttpHandler is plugged into each backend, exactly as you'd write it."
    - "JVM command-line options were tuned to take advantage of JVM features. The full implementation is [public on GitHub](https://github.com/TechEmpower/FrameworkBenchmarks/tree/master/frameworks/Kotlin/http4k)."
results_intro: "Rankings below are filtered to JVM libraries, best backend shown per test. Each links to the live TechEmpower data. Lower rank is better."
composite:
    rank: 13
    total: 41
    href: "https://www.techempower.com/benchmarks/#section=data-r22&l=xan3i3-cn3"
results:
    - label: DB query + HTML render
      rank: 25
      total: 146
      href: "https://www.techempower.com/benchmarks/#section=data-r22&l=xan3i3-cn3"
    - label: Multiple DB queries
      rank: 23
      total: 145
      href: "https://www.techempower.com/benchmarks/#section=data-r22&l=xan3i3-cn3"
    - label: Single DB query
      rank: 25
      total: 151
      href: "https://www.techempower.com/benchmarks/#section=data-r22&l=xan3i3-cn3"
    - label: Random DB updates
      rank: 41
      total: 138
      href: "https://www.techempower.com/benchmarks/#section=data-r22&l=xan3i3-cn3"
    - label: JSON serialization
      rank: 59
      total: 152
      href: "https://www.techempower.com/benchmarks/#section=data-r22&l=xan3i3-cn3"
    - label: Plaintext pipelining
      rank: 84
      total: 153
      href: "https://www.techempower.com/benchmarks/#section=data-r22&l=xan3i3-cn3"
results_note: "Backends & drivers vary per test (Apache, Jetty Loom, Netty; PostgreSQL / Vert.x with a Hikari pool; Rocker templating). See the [full Round 22 data](https://www.techempower.com/benchmarks/#section=data-r22&l=xan3i3-cn3)."
honest_para: "Chasing the very top of a benchmark table tends to produce hostile, un-http4k-like APIs. We'd rather give you friendly, testable, composable code that still lands comfortably in the top half of the JVM pack. For the vast majority of real systems, http4k is nowhere near your bottleneck - your database is."
callout_lead: "Benchmark your own app."
callout_text: "TechEmpower simulates simple real-world scenarios, but your app can behave drastically differently. If performance is critical, try different engines with your own workload - and remember startup time varies a lot between backends too."
cta_title: "Pick the backend, keep the code."
cta_text: "Swap Netty for Jetty Loom for Apache with one line, and benchmark what actually matters: your app."
cta_primary_label: "Explore the backends"
cta_primary_href: "/howto/use_a_server_backend/"
cta_secondary_label: "Read the ethos"
cta_secondary_href: "/overview/"
---
