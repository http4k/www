package content.ecosystem.connect.reference.x402

import org.http4k.chaos.start
import org.http4k.connect.x402.FakeX402Facilitator

val facilitator = FakeX402Facilitator().start()
