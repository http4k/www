package content.ecosystem.connect.reference.amazon.containercredentials

import org.http4k.chaos.start
import org.http4k.connect.amazon.containercredentials.FakeContainerCredentials

fun `start fake`() {
    FakeContainerCredentials().start()
}
