package content.ecosystem.connect.reference.kafka.rest

import org.http4k.client.JavaHttpClient
import org.http4k.connect.kafka.rest.Http
import org.http4k.connect.kafka.rest.KafkaRest
import org.http4k.connect.kafka.rest.model.Credentials
import org.http4k.connect.kafka.rest.model.Record
import org.http4k.connect.kafka.rest.model.Records
import org.http4k.connect.kafka.rest.model.Topic
import org.http4k.connect.kafka.rest.produceMessages
import org.http4k.core.Uri

val kafkaRestV2 = KafkaRest.Http(
    Credentials("user", "password"), Uri.of("http://restproxy"), JavaHttpClient()
)

val produceResult = kafkaRestV2.produceMessages(Topic.of("topic"), Records.Json(listOf(Record("123", ""))), ::RoundRobinRecordPartitioner)
