package content.ecosystem.connect.reference.slack

import org.http4k.chaos.start
import org.http4k.connect.slack.FakeSlack

val fakeSlack = FakeSlack().start()
