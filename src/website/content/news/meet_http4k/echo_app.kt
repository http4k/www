package content.news.meet_http4k

import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status.Companion.OK
import org.http4k.server.SunHttp
import org.http4k.server.asServer

val app = { request: Request -> Response(OK).body(request.body) }
val server = app.asServer(SunHttp(8000)).start()
