import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.http4k.core.HttpHandler
import org.http4k.core.Method.GET
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status.Companion.OK
import org.http4k.routing.bind
import org.http4k.routing.routes
import org.http4k.wiretap.junit.Intercept
import org.http4k.wiretap.junit.RenderMode
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.RegisterExtension

class HttpTrafficTest {

    private val app = routes(
        "/api" bind GET to { Response(OK).body("hello") }
    )

    // Captures all HTTP traffic through the app
    @RegisterExtension
    @JvmField
    val intercept = Intercept(RenderMode.Always) { app }

    @Test
    fun `requests are captured`(http: HttpHandler) {
        val response = http(Request(GET, "/api"))
        assertThat(response.bodyString(), equalTo("hello"))
    }
}
