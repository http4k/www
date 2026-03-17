package content.news.meet_http4k

import org.http4k.core.HttpHandler
import org.http4k.core.Method.GET
import org.http4k.core.Method.POST
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status.Companion.OK
import org.http4k.core.then
import org.http4k.filter.ServerFilters
import org.http4k.lens.Query
import org.http4k.lens.string
import org.http4k.routing.bind
import org.http4k.routing.routes

val queryName = Query.string().required("name")
val queryApp: HttpHandler = routes(
      "/post" bind POST to { request: Request -> Response(OK).body(queryName(request)) }
    )

val catchApp = ServerFilters.CatchLensFailure.then(queryApp)(Request(GET, "/hello/2000-01-01?myCustomType=someValue"))
