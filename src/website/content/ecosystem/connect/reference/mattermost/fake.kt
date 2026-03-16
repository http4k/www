package content.ecosystem.connect.reference.mattermost

import org.http4k.chaos.start
import org.http4k.connect.mattermost.FakeMattermost

val mattermost = FakeMattermost().start()
