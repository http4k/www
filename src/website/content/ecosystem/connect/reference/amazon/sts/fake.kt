package content.ecosystem.connect.reference.amazon.sts

import org.http4k.chaos.start
import org.http4k.connect.amazon.sts.FakeSTS

fun `start fake`() {
    FakeSTS().start()
}
