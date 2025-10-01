package content.ecosystem.http4k.reference.powerassert

import org.http4k.core.Method.POST
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status.Companion.OK
import org.http4k.powerassert.hasBody
import org.http4k.powerassert.hasHeader
import org.http4k.powerassert.hasQuery
import org.http4k.powerassert.hasStatus
import org.junit.jupiter.api.Test

class ExampleTest {

    @Test
    fun `example matchers`() {

        val request = Request(POST, "/?a=b").body("http4k is cool").header("my header", "a value")

        // status
        assert(Response(OK).hasStatus(OK))

        // query
        assert(request.hasQuery("a", "b"))

        // header
        assert(request.hasHeader("my header", "a value"))

        // body
        assert(request.hasBody("http4k is cool"))

        // composite
        assert(request.hasBody("http4k is cool"))
    }
}
