package content.ecosystem.connect.reference.kafka.rest

import org.http4k.connect.kafka.rest.v3.model.RecordData
import org.http4k.connect.kafka.rest.v3.model.Record
import org.http4k.connect.model.Base64Blob

val binaryV3Record = Record(RecordData.Binary(Base64Blob.encode("foo1")))
