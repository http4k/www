package content.ecosystem.connect.reference.amazon.cloudfront

import org.http4k.chaos.start
import org.http4k.connect.amazon.cloudfront.FakeCloudFront

fun `start fake`() {
    FakeCloudFront().start()
}
