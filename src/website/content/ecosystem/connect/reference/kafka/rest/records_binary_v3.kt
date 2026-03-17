package content.ecosystem.connect.reference.kafka.rest

import org.http4k.connect.amazon.core.model.Base64Blob
import org.http4k.connect.kafka.rest.model.Record
import org.http4k.connect.kafka.rest.v3.model.Binary

val binaryV3Record = Record(Binary(Base64Blob.encode("foo1")))
