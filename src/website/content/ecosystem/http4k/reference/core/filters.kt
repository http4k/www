package content.ecosystem.http4k.reference.core

import org.http4k.core.Filter
import org.http4k.core.HttpHandler
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status.Companion.OK
import org.http4k.core.then
import org.http4k.filter.ServerFilters

val filterHandler = { _: Request -> Response(OK) }

val myFilter = Filter { next: HttpHandler ->
    { request: Request ->
        val start = System.currentTimeMillis()
        val response = next(request)
        val latency = System.currentTimeMillis() - start
        println("I took $latency ms")
        response
    }
}
val latencyAndBasicAuth: Filter = ServerFilters.BasicAuth("my realm", "user", "password").then(myFilter)
val filterApp: HttpHandler = latencyAndBasicAuth.then(filterHandler)
