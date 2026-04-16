# {{ .Site.Title }} - {{ .Site.Params.description }}

## HTTP as a Function

An http4k server is just a regular function that gets invoked with a Request and returns a Response. In a single line of code you can switch your server between Java (built-in), Apache, Jetty, Netty, Undertow, Ktor, Ratpack or Helidon.

An http4k client is just a regular function that you invoke with a Request and receives a Response. In a single line of code you can switch your client implementation between Java (built-in), Apache, Fuel, Jetty, and OkHttp.

## Highlights

- **Tiny footprint** - The core library has no dependencies and it's all you need to create fully-functional applications. http4k APIs take advantage of Kotlin features and are powered by Functional Programming techniques.
- **Highly extendable** - Supports many Server, Serverless and HTTP Client technologies behind simple, consistent interfaces. http4k provides [over 100 pre-built integrations](/ecosystem/) for developers to choose from including Observability, JSON, and OpenAPI support.
- **First class testability** - Create lightning fast, rock solid test suites for individual endpoints, applications or multi-service systems, all running fully in-memory. http4k embraces advanced testing approaches such as [Approval](/ecosystem/http4k/reference/approvaltests/), [Chaos](/ecosystem/http4k/reference/chaos/) and [Service Virtualization](/ecosystem/http4k/reference/servicevirtualisation/).
- **Battle-tested in the wild** - http4k takes advantage of technologies that have millions of hours of production runtime. The toolkit has been used to build high volume applications in Banking, Publishing & Government projects.

## More than 150 integrations

http4k integrates with many tools, libraries and APIs for building Cloud-Native and AI powered applications. [Check it out](/ecosystem/).

## Corporate partners

http4k works with selected companies to fund and explore continued innovation around the project.
{{ range .Site.Data.sponsors }}
- [{{ .name }}]({{ .url }})
{{ end }}

## Professional Services
{{ range .Site.Data.cta }}
- **{{ .title }}** - {{ .description }} [{{ .text }}]({{ .link }})
{{ end }}
