package content.ecosystem.http4k.reference.core

import org.http4k.core.ContentEncoding
import org.http4k.core.ContentType.Companion.TEXT_PLAIN
import org.http4k.core.Deflate
import org.http4k.core.Gzip
import org.http4k.core.Method.GET
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status.Companion.OK
import org.http4k.core.then
import org.http4k.core.with
import org.http4k.filter.ClientFilters
import org.http4k.filter.ServerFilters
import org.http4k.filter.compressibleContentTypes
import org.http4k.lens.Header

fun main() {
    // encodings are offered in order of server preference
    val server = ServerFilters.Compression(
        ContentEncoding.Gzip(),
        ContentEncoding.Deflate(),
        shouldCompress = compressibleContentTypes(TEXT_PLAIN)
    ).then { Response(OK).with(Header.CONTENT_TYPE of TEXT_PLAIN).body("hello world ".repeat(100)) }

    val raw = server(Request(GET, "/").header("accept-encoding", "deflate"))
    println(raw.header("content-encoding"))

// Produces:
//    deflate

    // asks for gzip and decompresses the response
    val client = ClientFilters.Decompression(ContentEncoding.Gzip()).then(server)
    println(client(Request(GET, "/")).bodyString().length)

// Produces:
//    1200
}
