package content.news.meet_http4k

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.http4k.core.Method.POST
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status.Companion.OK
import org.junit.jupiter.api.Test

class AppTest {
    @Test
    fun `echoes request body`() {
        assertThat(app(Request(POST, "/").body("hello")), equalTo(Response(OK).body("hello")))
    }
}
