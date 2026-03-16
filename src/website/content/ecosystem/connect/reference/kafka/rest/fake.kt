package content.ecosystem.connect.reference.kafka.rest

import org.http4k.chaos.start
import org.http4k.connect.kafka.rest.FakeKafkaRest

val kafkaRest = FakeKafkaRest().start()
