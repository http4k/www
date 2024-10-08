---
type: ecosystem
category: Concept
ecosystem: http4k Connect
title: Connect Overview 
description: An explanation of the Connect Pattern underpinning http4k Connect
weight: -1
---

The main bulk of non-operationally focussed application code in a modern Server-based HTTP microservice can be broken
down into a few broad areas:

1. Inbound Server-side APIs, routing and unmarshalling of incoming requests
2. Business logic functions
3. Data-access querying and mutations
4. API Client code for outbound remote API communication

#### Structuring our inbound APIs

For **1)** - the Server-side - we tend to model the application as a set of separate HTTP entrypoint classes/functions which are composed into a whole to
represent
the incoming HTTP API, either explicitly or via some meta-programming such as annotations. So for example, using http4k, we might create and start our server
with:

{{< kotlin file="pre/structure.kt" >}}

In this case, the splitting up of the server-side API into separate functions allows us to maintain a decent grip on our
application as a whole and also to be able to easily test the various endpoints in the application independently of
the rest - e.g. we don't need to provide a Bearer token to access our API calls if we have access to directly test `echo()` and `health()`.

Additionally, because we have modularised the code in this way, it is also reusable in other contexts - we can put common endpoint code such as `health()` into
a shared location and reuse them across our fleet of microservices.

#### Structuring our outbound APIs

When it comes to **4)** of the list above - API Client code for other remote APIs - we don't generally have a pattern in place to use the same structure. HTTP
clients to remote systems are usually constructed as monolithic classes with many methods, all built around a singularly configured HTTP API Client. Let's say we
want to talk to the GitHub API, we would normally build an API Client like so:

{{< kotlin file="pre/apiClient.kt" >}}

This is all quite sensible - there is a shared HTTP client which is configured to send requests to the API with the correct `Accept` header. Unfortunately
though, as our usage of the API grows, so will the size of the `GitHubApi` class - it may gain many (10s or even 100s of individual) functions, all of which
generally provide singular access to a single API call. We end up with a monolith object which can be thousands of lines long if left unchecked.

As there is generally no interaction between these functions - it would be desirable to structure the code in a similar way to how we structured our inbound
API - in a modular, easily testable and reusable fashion. Even so, we also want to find a way to build functions which combine one or more calls to the API. 

#### Introducing http4k Connect

This is where the Connect pattern and http4k Connect will help us. In essence, Connect allows the splitting of an API Client monolith into individual Actions and a shared API Client object which centralises the communication with the API.

#### Action
The fundamental unit of work in http4k Connect is the `Action` interface, which represents a single interaction with the remote system, generified by the type of the return object `R`. Each action contains the state of the data that needs to be transmitted, and also how to marshall the data within the action to and from the underlying HTTP API.

```kotlin
interface Action<R> {
    fun toRequest(): Request
    fun toResult(response: Response): R
}
```

For our `GitHubApi` API client, we create the superinterface using a [Result4k](https://github.com/fork-handles/forkhandles/tree/trunk/result4k) result monad type to catch exceptions:

{{< kotlin file="post/action.kt" >}}

... and  implementations of the Actions for the API along with the result type. Note that
the Actions are modelled as Kotlin data classes - this allows us to easily compare them if we want to in tests:

{{< kotlin file="post/actions.kt" >}}

#### Reimagining API Clients

A Connect API Client represents the common base protocol for interacting with the remote API - it will deal with server host location, authorisation and other
headers, and perform the actual HTTP interactions. Each API Client is modelled as a simple interface with a single generic method accepting the generic Action type.

Note here the presence of the Kotlin `companion object` - it is meant to be empty and is there precisely to give us a point to hook other code onto in a moment. This is to make life easier for the API user.

{{< kotlin file="post/apiClient.kt" >}}

Our first usage of the companion object is to rewrite our previous version as an anonymous implementation of the `GitHubApi` and attach it to our API Client interface,
returned by the `Http()` factory function. All dependencies required by the API Client are passed in here and closed over. Note that we explicitly pass in the HTTP
client instead of constructing it inside the function - access to this is critical if we want to be able to decorate the API Client with call logging or other operational concerns:

{{< kotlin file="post/apiClientImpl.kt" >}}

#### Using the API Client

Apart from the usage of the Companion Object as a hook, construction of our API Clien looks similar to the previous version - we have not exposed any more
concrete types (there is still just `GitHubApi`). However, calling the API does look different - because of the operator function `invoke()`, we now treat the
Server as a simple function which takes Action instances:

{{< kotlin file="post/usingClient.kt" >}}

This change may leave a slight bad taste in the mouth as the API is no longer as IDE discoverable. Luckily, Kotlin has another trick up it's sleeve here which will help us...

#### Extension Functions

We can get back our old API very simply by creating an extension function for each Action that mimics the signature of the Action itself and delegates to the `invoke()` call in the client:

{{< kotlin file="post/extension.kt" >}}

Even better, for actions which consist more than one API call such as `getLatestUser()` below, we can just create more extension functions which delegate down to the individual actions. These functions can be added to `GitHubApi` instances at the global level, or just in the contexts or modules which make sense. The
extension function effectively allow us to compose our own custom `GitHubApi` Adapter out of the individual Action parts that we are interested in:

{{< kotlin file="post/composite.kt" >}}

#### Summary
Actions and API Clients are the core building blocks of http4k Connect. They allow us to structure our API Client code in a modular, testable and reusable way, and also to compose our own custom API Clients out of the individual parts that we are interested in. The Connect pattern is a powerful way to structure our API Client code in a way that is similar to how we structure our inbound APIs, and also to build functions which combine one or more calls to the API.
