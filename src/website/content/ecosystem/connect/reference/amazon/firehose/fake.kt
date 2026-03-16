package content.ecosystem.connect.reference.amazon.firehose

import org.http4k.chaos.start
import org.http4k.connect.amazon.firehose.FakeFirehose

val firehose = FakeFirehose().start()
