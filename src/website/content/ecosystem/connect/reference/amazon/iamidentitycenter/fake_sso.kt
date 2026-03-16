package content.ecosystem.connect.reference.amazon.iamidentitycenter

import org.http4k.chaos.start
import org.http4k.connect.amazon.iamidentitycenter.FakeSSO

fun `start sso fake`() {
    FakeSSO().start()
}
