---
category: Reference
type: ecosystem
ecosystem: http4k Core
title: "Compression"
description: Feature overview of the http4k-compression module, which adds Brotli and Zstandard content-encodings
---

### Installation (Gradle)

```kotlin
dependencies {
    {{< http4k_bom >}}

    implementation("org.http4k:http4k-compression")

    // if you need Brotli, add the native library for each platform you run on:
    // native-linux-x86_64, native-linux-aarch64, native-osx-x86_64, native-osx-aarch64, native-windows-x86_64
    runtimeOnly("com.aayushatharva.brotli4j:native-linux-x86_64:1.24.0")
}
```

### About

http4k Core ships negotiating `Compression` and `Decompression` filters which work with any `ContentEncoding`. Gzip and Deflate are built in. This module adds two more:

- `ContentEncoding.Brotli(quality)` - quality from 0 (fastest) to 11 (smallest). Default is 4.
- `ContentEncoding.Zstd(level)` - level from 1 (fastest) to 22 (smallest). Default is 3. Decoding enforces the 8MB window limit from RFC 9659, which bounds decoder memory per request.

Pass encodings in order of preference. The server picks the best one the client accepts (by `accept-encoding`) and decompresses requests sent in any of them.

The filters are:

- **Server:** `ServerFilters.Compression`, or `ResponseFilters.Compression` and `RequestFilters.Decompression` separately. Pass `shouldCompress` to choose which responses get compressed, such as `compressibleContentTypes(...)`.
- **Client:** `ClientFilters.Compression` compresses requests with the first encoding and decompresses responses. `ClientFilters.Decompression` only decompresses responses.
- **SSE:** `ServerFilters.SseCompression` (in `http4k-realtime-core`) compresses event streams message by message. `PolyFilters.Compression` applies HTTP and SSE compression together.

Decompression is capped by `maxDecompressedSize`. The deprecated `GZip`/`GunZip` filters map to these filters with `ContentEncoding.Gzip()`.

#### Code

{{< kotlin file="example.kt" >}}
