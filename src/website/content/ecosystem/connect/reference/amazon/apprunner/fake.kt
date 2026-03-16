package content.ecosystem.connect.reference.amazon.apprunner

import org.http4k.chaos.start
import org.http4k.connect.amazon.apprunner.FakeAppRunner

val appRunner = FakeAppRunner().start()
