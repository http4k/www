package content.ecosystem.connect.reference.kafka.rest

import org.http4k.connect.kafka.rest.model.PartitionId
import org.http4k.connect.kafka.rest.v2.model.Record
import org.http4k.connect.kafka.rest.v2.model.Records
import org.http4k.connect.model.Base64Blob

val binaryRecords =
    Records.Binary(listOf(Record(Base64Blob.encode("123"), Base64Blob.encode("456"), PartitionId.of(123))))
