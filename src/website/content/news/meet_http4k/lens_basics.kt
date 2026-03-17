package content.news.meet_http4k

import org.http4k.core.Method.GET
import org.http4k.core.Request
import org.http4k.lens.Query
import org.http4k.lens.int

val pageLens = Query.int().required("page")
val page: Int = pageLens(Request(GET, "http://server/search?page=123"))
