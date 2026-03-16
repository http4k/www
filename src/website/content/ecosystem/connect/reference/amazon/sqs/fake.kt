package content.ecosystem.connect.reference.amazon.sqs

import org.http4k.chaos.start
import org.http4k.connect.amazon.sqs.FakeSQS

val sqs = FakeSQS().start()
