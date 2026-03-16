package content.ecosystem.connect.reference.amazon.ses

import org.http4k.chaos.start
import org.http4k.connect.amazon.ses.FakeSES

val ses = FakeSES().start()
