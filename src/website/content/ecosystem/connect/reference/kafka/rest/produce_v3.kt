package content.ecosystem.connect.reference.kafka.rest

import org.http4k.client.JavaHttpClient
import org.http4k.connect.kafka.rest.Http
import org.http4k.connect.kafka.rest.KafkaRest
import org.http4k.connect.kafka.rest.extensions.RoundRobinRecordPartitioner
import org.http4k.connect.kafka.rest.model.Topic
import org.http4k.connect.kafka.rest.v3.extensions.produceRecordsWithPartitions
import org.http4k.connect.kafka.rest.v3.model.ClusterId
import org.http4k.connect.kafka.rest.v3.model.Record
import org.http4k.connect.kafka.rest.v3.model.RecordData.Binary
import org.http4k.connect.model.Base64Blob
import org.http4k.core.Credentials
import org.http4k.core.Uri

val kafkaRestV3 = KafkaRest.Http(
    Credentials("user", "password"), Uri.of("http://restproxy"), JavaHttpClient()
)

val topic = Topic.of("topic")
val clusterId = ClusterId.of("cluster")

val produceV3Result = kafkaRestV3.produceRecordsWithPartitions(
    topic,
    clusterId,
    listOf(Record(Binary(Base64Blob.encode("foo1")))),
    ::RoundRobinRecordPartitioner
)
