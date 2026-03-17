package content.ecosystem.connect.reference.kafka.rest

import org.http4k.connect.kafka.rest.model.PartitionId
import org.http4k.connect.kafka.rest.v2.model.Record
import org.http4k.connect.kafka.rest.v2.model.Records

val jsonRecords = Records.Json(listOf(Record("123", "value", PartitionId.of(123))))
