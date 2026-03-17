package content.ecosystem.http4k.reference.core

import org.http4k.core.Method.POST
import org.http4k.core.Request
import org.http4k.core.toCurl
import org.http4k.core.toBody

val curl = Request(POST, "http://httpbin.org/post").body(listOf("foo" to "bar").toBody()).toCurl()
// curl -X POST --data "foo=bar" "http://httpbin.org/post"
