package content.ecosystem.connect.reference.google.analytics_ua

import org.http4k.chaos.start
import org.http4k.connect.google.ua.FakeGoogleAnalytics

fun `start fake`() {
    FakeGoogleAnalytics().start()
}
