package content.ecosystem.connect.reference.amazon.xray

import org.http4k.chaos.start
import org.http4k.connect.amazon.xray.FakeXRay

val xray = FakeXRay().start()
