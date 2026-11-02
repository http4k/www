package content.ecosystem.http4k.reference.compression

import org.http4k.core.Brotli
import org.http4k.core.ContentEncoding
import org.http4k.core.Gzip
import org.http4k.core.Method.GET
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status.Companion.OK
import org.http4k.core.Zstd
import org.http4k.core.then
import org.http4k.filter.ClientFilters
import org.http4k.filter.ServerFilters

fun main() {
    // encodings are offered in order of server preference
    val encodings = arrayOf(ContentEncoding.Zstd(), ContentEncoding.Brotli(), ContentEncoding.Gzip())

    val server = ServerFilters.Compression(*encodings)
        .then { Response(OK).body("hello world ".repeat(100)) }

    val raw = server(Request(GET, "/").header("accept-encoding", "br, gzip"))
    println(raw.header("content-encoding"))
    println(raw.body.length)

// Produces:
//    br
//    24

    val client = ClientFilters.Decompression(*encodings).then(server)
    val decoded = client(Request(GET, "/").header("accept-encoding", "zstd"))
    println(decoded.bodyString().length)

// Produces:
//    1200
}
