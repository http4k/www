package content.news.http4k_v4

import org.http4k.core.HttpHandler
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status.Companion.OK
import org.http4k.server.Netty
import org.http4k.server.asServer
val app: HttpHandler = { req: Request -> Response(OK).body("hello world!") }

val jvmApp = app.asServer(Netty(8080)).start()

//class MyServerlessFunction : GoogleCloudFunction(app)
