package content.ecosystem.connect.reference.amazon.kms

import org.http4k.chaos.start
import org.http4k.connect.amazon.kms.FakeKMS

val kms = FakeKMS().start()
