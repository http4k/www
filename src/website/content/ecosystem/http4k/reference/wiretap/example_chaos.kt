package content.ecosystem.http4k.reference.wiretap

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.http4k.chaos.ChaosBehaviours.ReturnStatus
import org.http4k.chaos.ChaosEngine
import org.http4k.core.HttpHandler
import org.http4k.core.Method.GET
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status.Companion.INTERNAL_SERVER_ERROR
import org.http4k.core.Status.Companion.OK
import org.http4k.wiretap.junit.Intercept
import org.http4k.wiretap.junit.RenderMode
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.RegisterExtension

class ChaosTest {

    @RegisterExtension
    @JvmField
    val intercept = Intercept(RenderMode.Always) {
        MyApp(http { Response(OK).body("downstream") }, otel("my-service"))
    }

    // ChaosEngine is injected — controls failure injection on outbound calls
    @Test
    fun `outbound calls fail when chaos is enabled`(http: HttpHandler, chaos: ChaosEngine) {
        chaos.enable(ReturnStatus(INTERNAL_SERVER_ERROR))

        val response = http(Request(GET, "/api"))
        assertThat(response.status, equalTo(INTERNAL_SERVER_ERROR))
    }
}
