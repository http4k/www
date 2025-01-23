---
draft: true
sitemap:
    disable: true
title : "http4k Connect"
type: "product"
layout: "product"
tagline: Functional, modular and testable API Clients 
description: http4k Connect is a lightweight API Client toolkit which includes libraries for connecting to popular third-party cloud services and AI backends
docs_link: https://www.http4k.org/ecosystem/connect/
features:
    - title: Tiny</br> footprint
      icon: supportive
      colour: pink
      description: In true http4k-style, Connect clients rely on minimal dependencies and zero reflection, perfect for lightweight and security-conscious applications.
    - title: Total</br> modularisation
      icon: footprint
      colour: violet
      description: Extension of the clients is as simple as combining function calls or implementing a single data class with just 2 simple methods.
    - title: Kotlin-</br>centric
      icon: support
      colour: indigo
      description: Every API Client leverages Result types and is exception-safe, so you can finally code like it's Kotlin that you're writing!
    - title: Battle-tested</br> in the wild
      icon: testability
      colour: blue
      description: Connect API Clients have been used to build high volume applications in Banking, Publishing, eCommerce & Government projects.
    - title: First class</br> testability
      icon: support
      colour: green
      description: Most API Clients come with an in-memory cloud-service fake which can be used for lightning fast test suites or spun up as a server.</br></br>All fakes are chaos-enabled, so you can test what happens to your code in failure scenarios.
    - title: Total</br> control
      icon: testability
      colour: yellow
      description: Most API Client libraries hide their HTTP client from you, stopping you from implementing observability or customisation.</br></br>Connect allows any http4k client module to be plugged in, so you can leverage the full power of http4k
    - title: Ultimate</br> type safety
      icon: supportive
      colour: orange
      description: API Clients leverage TinyTypes to provide typesafe coding and automatic serialisation.</br></br>Stop relying on unsafe, stringly-typed APIs.
    - title: Simple Key/value</br> storage
      icon: footprint
      colour: red
      description: Ships with a storage abstraction for simple key-value storage, and adapters for popular backends.</br></br>Plugin in-memory, S3, JDBC or Redis with a single line of code!
how_tos:
    - section: It's simple to use and extend the built-in API Clients...
      steps:
          - description: 1. Instantiate your API Client and use, injecting your own HTTP client for observability.
            image: using-client.png
            alt: Using a Connect API Client
          - description: 2. Is your API action not supported? No problem - simply create your own by extension!
            image: extending-client.png
            alt: Extending a Connect API Client
    - section: ... or modularise your own API Clients in 4 easy steps ...
      steps:
          - description: 1. An <b>API Action</b> is just an interface with methods for marshalling the contents of HTTP messages.
            image: action.png
            alt: Defining our Actions
          - description: 2. An <b>API Client</b> is just a class with a single function and handles with the transport for the remote API.
            image: api.png
            alt: Defining an API action
          - description: 3. Implement any individual Action for your API with <b>a single class</b> and a <b>extension method</b>. Compose multiple calls together without bloating your API Client!
            image: operation.png
            alt: Defining a custom Operation
          - description: 4. <b>Instantiate the API Client</b> by passing in the HTTP client and other transport requirements, and call it as normal. Exceptions are trapped in the returned Result.
            image: usage.png
            alt: Defining an API action
---
