package content.ecosystem.http4k.reference.wiretap

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import io.opentelemetry.api.OpenTelemetry
import org.http4k.core.HttpHandler
import org.http4k.core.Method.GET
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status.Companion.OK
import org.http4k.core.then
import org.http4k.filter.ClientFilters
import org.http4k.filter.OpenTelemetryTracing
import org.http4k.filter.ServerFilters
import org.http4k.routing.RoutingHttpHandler
import org.http4k.routing.bind
import org.http4k.routing.routes
import org.http4k.wiretap.junit.Intercept
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.RegisterExtension

class MyAppTest {

    @RegisterExtension
    @JvmField
    val intercept = Intercept {
        // http() wraps an outbound client with traffic recording and OTel tracing
        val next = http { Response(OK).body("from downstream") }

        // Build your app with the instrumented client
        val openTelemetry = otel("my-service")

        MyApp(next, openTelemetry)
    }

    private fun MyApp(httpClient: HttpHandler, otel: OpenTelemetry): RoutingHttpHandler {
        val downstreamClient = ClientFilters.OpenTelemetryTracing(otel).then(httpClient)

        return ServerFilters.OpenTelemetryTracing(otel)
            .then(routes("/api" bind GET to { downstreamClient(Request(GET, "http://downstream/data")) }))
    }

    // HttpHandler is injected — sends requests through Intercept's recording filters
    @Test
    fun `requests are captured with full trace context`(http: HttpHandler) {
        assertThat(http(Request(GET, "/api")).bodyString(), equalTo("from downstream"))
    }
}
