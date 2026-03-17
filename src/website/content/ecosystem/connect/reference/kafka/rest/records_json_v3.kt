package content.ecosystem.connect.reference.kafka.rest

import org.http4k.connect.kafka.rest.v3.model.Record
import org.http4k.connect.kafka.rest.v3.model.RecordData

val jsonV3Record = Record(RecordData.Json(mapOf("key" to "value")))
