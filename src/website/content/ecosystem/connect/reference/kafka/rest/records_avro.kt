package content.ecosystem.connect.reference.kafka.rest

import org.http4k.connect.kafka.rest.v2.model.Records

val a = Records.Avro(
    listOf(
//        Record(
//            RandomEvent(UUID.nameUUIDFromBytes(it.toByteArray())),
//            RandomEvent(UUID(0, 0), PartitionId.of(123))
//        )
    )
)
