package content.ecosystem.connect.reference.amazon.iotjobsdataplane

import org.http4k.chaos.start
import org.http4k.connect.amazon.iotjobsdataplane.FakeIotJobsDataPlane

val iotJobsDataPlane = FakeIotJobsDataPlane().start()
