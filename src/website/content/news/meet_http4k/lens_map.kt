package content.news.meet_http4k

import org.http4k.core.Method.GET
import org.http4k.core.Request
import org.http4k.lens.Query
import org.http4k.lens.localDate
import java.time.LocalDate

data class MyDate(val value: LocalDate)
val dateQuery = Query.localDate().map(::MyDate, MyDate::value).required("date")
val myDate: MyDate = dateQuery(Request(GET, "http://server/search?date=2000-01-01"))
