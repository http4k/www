package content.ecosystem.connect.reference.kafka.rest

import org.http4k.connect.kafka.rest.model.PartitionId
import org.http4k.connect.kafka.rest.model.Record
import org.http4k.connect.kafka.rest.model.Records

val jsonRecords = Records.Json(listOf(Record("123", "value", PartitionId.of(123))))
