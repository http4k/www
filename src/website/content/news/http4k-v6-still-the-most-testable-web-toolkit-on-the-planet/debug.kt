package content.news.`http4k-v6-still-the-most-testable-web-toolkit-on-the-planet`

import org.http4k.filter.debug
import org.http4k.routing.poly
import org.http4k.server.Helidon
import org.http4k.server.asServer

val server = poly( /* handlers */).debug().asServer(Helidon(8000)).start()
