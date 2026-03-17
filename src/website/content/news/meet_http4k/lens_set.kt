package content.news.meet_http4k

import org.http4k.core.Method.GET
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status.Companion.OK
import org.http4k.core.with
import org.http4k.lens.Header
import org.http4k.lens.int

val pageSizeLens = Header.int().required("page")
val pageResponse: Response = pageSizeLens(123, Response(OK))
// or apply multiple lenses using with()
val updated: Request = Request(GET, "/foo").with(pageLens of 123, pageSizeLens of 10)
