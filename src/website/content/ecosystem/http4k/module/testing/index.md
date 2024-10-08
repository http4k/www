---
category: Reference
type: ecosystem
ecosystem: http4k Core
title: Testing http4k
description: How to test http4k endpoints and applications, and modules that support testing
---


The creators of http4k takes testing very seriously - so seriously that there really isn't that much to say here! 
The API has been designed to make it as simple as possible to test both individual endpoints and entire applications in a consistent fashion, which is aided by remembering that:

1. The input and output `Request/Response` objects are immutable.
1. `HttpHandlers` are just functions.
1. An entire http4k application is *just* an `HttpHandler`.

Because of the above, there really isn't much required in the way of "testing infrastructure" - no magic containers or test fixtures that you might find in other frameworks. 
Testing is just matter of calling the correct function! Additionally, because the server and client HttpHandler interfaces are symmetrical - moving between in and out of container contexts 
(or indeed even to another HTTP framework entirely) is just a matter of switching out the HttpHandler implementation from the constructed app (out of container) to an HTTP client (in-container).

That said, possibly the most useful thing is to demonstrate the process that we have developed to test micro-services. A simple example of the development process can be found 
[here](/tutorial/tdding_http4k/).

### Testing modules
We have developed the following modules to help with testing:

- [http4k-testing-approval](/ecosystem/http4k/module/approvaltests/): JUnit 5 extensions for [Approval testing](http://approvaltests.com/) of http4k Request and Response messages.
- [http4k-testing-chaos](/ecosystem/http4k/module/chaos/): API for declaring and injecting failure modes into http4k applications, allowing modelling and hence answering of "what if" style questions to help understand how code fares under failure conditions such as latency and dying processes.
- [http4k-testing-hamkrest](/ecosystem/http4k/module/hamkrest/): a set of composable Hamkrest matchers for matching http4k objects against.
- [http4k-testing-kotest](/ecosystem/http4k/module/kotest/): a set of composable Kotest matchers for matching http4k objects against.
- [http4k-testing-servitium](/ecosystem/http4k/module/servicevirtualisation/): a fully featured implementation of the [Servirtium] solution for Service Virtualization testing approach.
- [http4k-testing-strikt](/ecosystem/http4k/module/strikt/): a set of custom Strikt assertions for matching http4k objects against.
- [http4k-testing-webdriver](/ecosystem/http4k/module/webdriver/): an ultra-lightweight Selenium WebDriver implementation which can be used to test-drive http4k apps (ie. HttpHandlers).

[Servirtium]: https://servirtium.dev
