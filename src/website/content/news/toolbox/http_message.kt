package content.news.toolbox

import org.http4k.core.Method
import org.http4k.core.Request

fun request(): Request = Request(Method.POST, "/example/index.html")
	.query("query1", "abc")
	.query("query2", "def")
	.header("Host", "toolbox.http4k.org")
	.header("Accept", "image/gif, image/jpeg, */*")
	.header("Content-Type", "text/plain")
	.body("""hello from http4k""")
