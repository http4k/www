package content.ecosystem.connect.reference.amazon.evidently

import org.http4k.chaos.start
import org.http4k.connect.amazon.evidently.FakeEvidently

fun `start fake`() {
    FakeEvidently().start()
}
