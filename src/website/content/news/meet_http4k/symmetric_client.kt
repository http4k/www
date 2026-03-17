package content.news.meet_http4k

import org.http4k.client.ApacheClient
import org.http4k.core.HttpHandler
import org.http4k.core.Method.GET
import org.http4k.core.Request
import org.http4k.core.Response

val client: HttpHandler = ApacheClient()
val clientResponse: Response = client(Request(GET, "http://server/path"))
