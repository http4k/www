---
type: faq
eyebrow: faq
title: The questions we get asked most.
description: Straight answers about the toolkit, its API and the ecosystem. Can't find yours? The Kotlin Slack and our inbox are both open.
faq:
  - category: General
    items:
      - q: Is http4k a library or a framework?
        a: |
          Neither! Although it has many of the features of a framework, we consider the http4k ecosystem to be a generalised **Toolkit** which enables the creation of HTTP-based applications. It is incredibly unopinionated and has been designed to not enforce design decisions on the API user. We use http4k for applications both large and small, using no DI framework.
      - q: Is http4k currently used in production?
        a: |
          Absolutely! Whilst overall stats are hard to come by, we have built and run many production systems using http4k in many different industries. The biggest known usage of http4k is serving the global site traffic (rank ~700 globally) for a large academic publisher, easily serving 10s of millions of requests per day on a few nodes. At last check, the library was being downloaded millions of times a month from Maven Central. http4k also appears in the [Thoughtworks Tech Radar](https://www.thoughtworks.com/radar/languages-and-frameworks/http4k), which covers useful and upcoming technologies used in Thoughtworks-delivered projects.

          If you're running http4k in production and would like to be listed on the site as an adopter, please get in touch.
      - q: Does http4k support an Async model? I need webscale!
        a: |
          Currently there is no direct coroutine support in http4k. However, with the advent of project Loom on the JVM, we get a lot of the benefits of async model out of the box using Virtual Threads without the need to complicate the API with the use of suspend etc. As for scaling arguments, see the above answer relating to production usage, or checkout the [benchmark results](/performance/) to see how http4k compares to other JVM-based sync and async web libraries.
  - category: API
    items:
      - q: "I'm attempting to build HTTP messages using the API, but changes don't affect the object (e.g. calling `request.body(\"hello\")`)?"
        a: |
          http4k HTTP message objects are *immutable*, so you need to chain or reassign the value from the method call to get the updated version.
      - q: Where are all the useful Filters defined?
        a: |
          Filters are all in the `import org.http4k.filter` package and are located as methods on a singleton `object` relevant to their use:

          - `org.http4k.filter.CachingFilters.Request` & `org.http4k.filter.CachingFilters.Response`
          - `org.http4k.filter.ClientFilters`
          - `org.http4k.filter.DebuggingFilters`
          - `org.http4k.filter.RequestFilters`
          - `org.http4k.filter.ResponseFilters`
          - `org.http4k.filter.ServerFilters`
          - `org.http4k.filter.TrafficFilters`
  - category: Lenses & Auto-Marshalling
    items:
      - q: I am having a problem with the usage of Moshi, Jackson or GSON for auto marshalling
        a: |
          Please see the [custom FAQ](/ecosystem/http4k/reference/json/) for JSON handling questions.
      - q: My application uses Lenses, but when they fail I get an HTTP 500 instead of the promised 400.
        a: |
          You forgot to add the `ServerFilters.CatchLensFailure` filter to your Server stack.
  - category: OpenAPI Contracts
    items:
      - q: When I use binary uploads, my OpenAPI endpoint receives no data.
        a: |
          With binary attachments, you need to ensure that the pre-flight validation does not eat the stream. You can do this by instructing http4k to ignore the incoming body for validation purposes - set `preFlightExtraction = PreFlightExtraction.IgnoreBody` in the route's `meta` block.
  - category: Serverless
    items:
      - q: When using AWS Lambda, I get an "method is invalid" error when testing my lambda.
        a: |
          This comes from the fact that there are 2 different payload formats for AWS Lambda HTTP functions. We support both v1 and v2 formats, but recommend V2 is used as the JSON format is superior. To fix the problem, ensure that your Lambda function payload version matches the name of the AWS adapter function class being used (v1 or v2).
---
