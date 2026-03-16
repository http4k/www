package content.ecosystem.connect.reference.amazon.s3

import org.http4k.chaos.start
import org.http4k.connect.amazon.s3.FakeS3

fun `start fake`() {
    FakeS3().start()
}
