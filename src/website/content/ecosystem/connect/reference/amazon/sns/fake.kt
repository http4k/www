package content.ecosystem.connect.reference.amazon.sns

import org.http4k.chaos.start
import org.http4k.connect.amazon.sns.FakeSNS

fun `start fake`() {
    FakeSNS().start()
}
