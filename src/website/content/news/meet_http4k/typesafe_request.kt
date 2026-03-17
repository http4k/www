package content.news.meet_http4k

import org.http4k.core.Method.GET
import org.http4k.core.Request

val request = Request(GET, "http://server/search?page=123")
val unsafePage: Int = request.query("page")!!.toInt()
