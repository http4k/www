package content.ecosystem.connect.reference.amazon.cloudwatchlogs

import org.http4k.chaos.start
import org.http4k.connect.amazon.cloudwatchlogs.FakeCloudWatchLogs

fun `start fake`() {
    FakeCloudWatchLogs().start()
}
