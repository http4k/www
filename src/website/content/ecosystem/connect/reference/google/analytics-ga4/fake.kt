package content.ecosystem.connect.reference.google.analytics_ga4

import org.http4k.chaos.start
import org.http4k.connect.google.analytics.ga4.FakeGoogleAnalytics

fun `start fake`() {
    FakeGoogleAnalytics().start()
}
