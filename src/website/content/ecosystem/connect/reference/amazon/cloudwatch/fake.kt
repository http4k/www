package content.ecosystem.connect.reference.amazon.cloudwatch

import org.http4k.chaos.start
import org.http4k.connect.amazon.cloudwatch.FakeCloudWatch

fun `start fake`() {
    FakeCloudWatch().start()
}
