---
title: TDDing http4k
description: A step-by-step guide to TDDing a simple http4k application
weight: 2
---

This post is a guide to *how* we build http4k applications test first to provide excellent test coverage driven by decoupled tests. 

### Application Design

For this example, we will use an example of a Maths app with the following requirements:

* The app must add 2 numbers together via an HTTP call
* The app must multiply 2 numbers together via an HTTP call
* Answers generated by the service will be logged (via HTTP POST) to another server - the Recorder.

Apps can generally be split into 3 tiers:

1. Endpoint: `HttpHandlers` are constructed individually, by providing a builder function which takes the business-level dependencies. 
1. Application: Builder function which takes the transport-level dependencies, and converts them into business-level dependencies. All routes are constructed and collected in this tier.
1. Server: Builder function which takes the configuration for environmental concerns such as ports and downstream urls.

The tutorial is split into 4 sections:

- [Part 1: Building a walking skeleton](part1/)
- [Part 2: Adding an endpoint](part2/)
- [Part 3: Adding another endpoint](part3/)
- [Part 4: Adding an external dependency](part4/)

[http4k]: https://http4k.org
