package content.ecosystem.connect.reference.kafka.rest

import org.http4k.connect.amazon.core.model.Base64Blob
import org.http4k.connect.kafka.rest.model.PartitionId
import org.http4k.connect.kafka.rest.model.Record
import org.http4k.connect.kafka.rest.model.Records

val binaryRecords = Records.Binary(listOf(Record(Base64Blob.encode("123"), Base64Blob.encode("456"), PartitionId.of(123))))
