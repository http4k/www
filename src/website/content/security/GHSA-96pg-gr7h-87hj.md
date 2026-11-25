---
title: "GHSA-96pg-gr7h-87hj: Fory deserialization instantiated arbitrary classes named in the payload"
description: The default Fory configuration disabled class registration, so a crafted request body could make the server instantiate any class on its classpath and run its constructor.
severity: HIGH
GHSA: GHSA-96pg-gr7h-87hj
advisory: https://github.com/http4k/http4k/security/advisories/GHSA-96pg-gr7h-87hj
date: 2026-11-25
---

### Description

`http4k-format-fory` built its default `Fory` instance with `requireClassRegistration(false)`. With registration disabled, Apache Fory reads class names from
the binary payload and resolves them against the server's classpath, invoking the named class's constructor during deserialization. Apache Fory's own
documentation warns that this mode can lead to remote code execution.

Any application that passed untrusted bytes to `Fory.autoBody<T>()`, `Body.auto<T>()`, `WsMessage.auto<T>()`, `HttpMessage.binary<T>()` or `Fory.asA()` is
affected, provided the target type has a polymorphic field somewhere in its object graph - for example an `Any?` or interface-typed property, or a collection
such as `List<Any>`. An unauthenticated attacker can embed the name of any class on the server's classpath in that field and have its constructor executed.
Reaching code execution, file access or SSRF requires a suitable gadget class to be present in the application's dependencies; Fory's built-in deny-list blocks
the well-known JDK gadgets.

The fix (`[Break]`) enables `requireClassRegistration(true)` by default. The `Fory` object is replaced by `Fory { register<MyType>() }`, and every marshalled
type must be registered explicitly, either with `register<T>()` or with the standard `AutoMappingConfiguration` mappings. Payloads naming unregistered classes
are no longer instantiated.

### Affected http4k modules & versions

- `http4k-format-fory`
    - 6.58.0.0 to 6.64.0.0-ee

### Mitigation

Users of affected versions should upgrade to the corresponding fixed version as below. http4k EE subscribers can access the fixed LTS versions through the
dedicated http4k Maven instance. For more details about the http4k EE LTS versions, please contact [http4k enterprise support](mailto:enterprise@http4k.org).

| Version        | Fixed Version | Availability                                  |
|----------------|---------------|-----------------------------------------------|
| <= 6.64.0.0-ee | 6.65.0.0-ee   | Community & [Enterprise Support](/enterprise) |

For deployments that cannot upgrade immediately, stop using the built-in `Fory` object and construct an instance with class registration enabled, registering
every type you send or receive:

```kotlin
// Named ZoneIds are really the package-private java.time.ZoneRegion, which must be mapped explicitly
@Suppress("UNCHECKED_CAST")
private val zoneRegion = ZoneId.of("Europe/London").javaClass as Class<ZoneId>

val SafeFory = ConfigurableFory(
    ForyKotlin.builder()
        .withXlang(false)
        .requireClassRegistration(true)
        .buildThreadSafeFory()
        .asConfigurable()
        .withStandardMappings()
        .text(BiDiMapping(zoneRegion, ZoneId::of, ZoneId::toString))
        .done()
        .apply {
            register(MyRequest::class.java, MyRequest::class.java.name)
            register(MyResponse::class.java, MyResponse::class.java.name)
        }
)
```

Then use `SafeFory` in place of `Fory` everywhere (for lens extensions, inside `with(SafeFory) { ... }`), and remove any `import org.http4k.format.Fory.auto` or
`import org.http4k.format.Fory.binary`. Both ends of a connection must use the same registered configuration. Alternatively, do not accept Fory-encoded bodies
or WebSocket messages from untrusted clients.

### Resolution timeline

| Date/time (UTC)  | Notes                                                            |
|------------------|------------------------------------------------------------------|
| 13/08/2026 11:10 | Fory class registration change reverted (commit `8179b28014`)    |
| 19/08/2026 18:13 | `http4k-format-fory`  released in http4k CE v6.58.0.0            |
| 23/11/2026 18:54 | Reported privately via GitHub Security Advisories by @immanuwell |
| 23/11/2026 20:01 | Fix committed (commit `4c913276eb`)                              |
| 23/11/2026 22:30 | Fix released in http4k v6.65.0.0-ee                              |
| 25/11/2026 10:00 | Public disclosure                                                |
