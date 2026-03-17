package content.news.meet_http4k

import org.http4k.core.HttpHandler
import org.http4k.core.Response
import org.http4k.core.Status.Companion.OK

fun MyApp1(): HttpHandler = { Response(OK) }
fun MyApp2(app1: HttpHandler): HttpHandler = { app1(it) }

val app1: HttpHandler = MyApp1()
val app2: HttpHandler = MyApp2(app1)
