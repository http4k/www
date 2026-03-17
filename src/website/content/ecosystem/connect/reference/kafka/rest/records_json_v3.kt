package content.ecosystem.connect.reference.kafka.rest

import org.http4k.connect.kafka.rest.model.Record
import org.http4k.connect.kafka.rest.v3.model.Json

val jsonV3Record = Record(Json(mapOf("key" to "value")))
