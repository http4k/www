package content.ecosystem.connect.reference.amazon.instancemetadata

import org.http4k.chaos.start
import org.http4k.connect.amazon.instancemetadata.FakeInstanceMetadataService

fun `start fake`() {
    FakeInstanceMetadataService().start()
}
