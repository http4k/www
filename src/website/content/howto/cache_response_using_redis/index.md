---
layout: howto
title: "Cache a response with redis"
description: Using Redis as a response cache
tags: [ "http4k Core" ]
---

Often caching is taken care of in a reverse proxy or CDN, but sometimes this isn't
possible or practical for whatever reason. 

As an alternative, its possible to store responses in Redis, and serve responses from 
the cache. 

Of course, in http4k, this is just a simple Filter.

The example here is pretty basic, it stores the headers and string response in Redis,
and there is no consideration of `Vary` header. It assumes that there are always some headers!

The example will ignore errors connecting to Redis on a cache store or load - this
may or may not suit your needs.

### Gradle setup

```kotlin
dependencies {
    {{< http4k_bom >}}
    implementation("org.http4k:http4k-core")
    implementation("redis.clients:jedis:6.1.0")
}
```

#### Cache Filter Example

{{< kotlin file="redis_cache_filter_example.kt" >}}
