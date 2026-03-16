package content.ecosystem.connect.reference.kafka.schemaregistry

import org.http4k.chaos.start
import org.http4k.connect.kafka.schemaregistry.FakeSchemaRegistry

fun `start fake`() {
    FakeSchemaRegistry().start()
}
