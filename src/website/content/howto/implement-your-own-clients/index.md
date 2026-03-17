---
layout: howto
title: "Implement your own clients"
description: How to implement your own clients for the Connect pattern
tags: [ "http4k Connect" ]
---

It is very easy to implement your own clients to follow the pattern. For the system `MySystem`, you would need to:

1. Depend on the `http4k-connect-core` artifact
2. Add an Action interface and implementation:
{{< kotlin file="action.kt" >}}
3. Add your client interface and HTTP implementation:
{{< kotlin file="client.kt" >}}

See also the guide on using KSP to generate extension functions for your clients!
