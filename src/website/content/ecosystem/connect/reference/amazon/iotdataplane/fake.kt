package content.ecosystem.connect.reference.amazon.iotdataplane

import org.http4k.chaos.start
import org.http4k.connect.amazon.iotdataplane.FakeIotDataPlane

val iotDataPlane = FakeIotDataPlane().start()
