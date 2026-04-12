package content.ecosystem.http4k.reference.wiretap

import org.http4k.core.Method.GET
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status.Companion.OK
import org.http4k.core.then
import org.http4k.filter.OpenTelemetryTracing
import org.http4k.filter.ServerFilters
import org.http4k.routing.bind
import org.http4k.routing.routes
import org.http4k.wiretap.junit.Intercept
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(Intercept::class)
class ZeroConfigTest {

    fun App() = ServerFilters.OpenTelemetryTracing()
        .then(routes("/{path:.*}" bind GET to { Response(OK).body("hello!") }))

    @Test
    fun `can capture otel traces with zero config`() {
        App()(Request(GET, "/api"))
    }
}
