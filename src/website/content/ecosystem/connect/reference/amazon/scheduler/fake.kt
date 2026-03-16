package content.ecosystem.connect.reference.amazon.scheduler

import org.http4k.chaos.start
import org.http4k.connect.amazon.scheduler.FakeScheduler

fun `start fake`() {
    FakeScheduler().start()
}
