package content.ecosystem.connect.reference.amazon.iot

import org.http4k.chaos.start
import org.http4k.connect.amazon.iot.FakeIot

val iot = FakeIot().start()
