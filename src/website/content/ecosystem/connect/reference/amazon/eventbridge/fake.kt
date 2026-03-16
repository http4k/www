package content.ecosystem.connect.reference.amazon.eventbridge

import org.http4k.chaos.start
import org.http4k.connect.amazon.eventbridge.FakeEventBridge

fun `start fake`() {
    FakeEventBridge().start()
}
