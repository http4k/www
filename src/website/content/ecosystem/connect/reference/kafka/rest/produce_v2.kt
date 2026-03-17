package content.ecosystem.connect.reference.kafka.rest

import org.http4k.client.JavaHttpClient
import org.http4k.connect.kafka.rest.Http
import org.http4k.connect.kafka.rest.KafkaRest
import org.http4k.connect.kafka.rest.extensions.RoundRobinRecordPartitioner
import org.http4k.connect.kafka.rest.model.Topic
import org.http4k.connect.kafka.rest.v3.extensions.produceRecordsWithPartitions
import org.http4k.connect.kafka.rest.v3.model.ClusterId
import org.http4k.core.Credentials
import org.http4k.core.Uri

val kafkaRestV2 = KafkaRest.Http(
    Credentials("user", "password"), Uri.of("http://restproxy"), JavaHttpClient()
)

val produceResult = kafkaRestV2.produceRecordsWithPartitions(
    Topic.of("topic"),
    ClusterId.of("clusterId"),
    listOf(),
    ::RoundRobinRecordPartitioner
)
