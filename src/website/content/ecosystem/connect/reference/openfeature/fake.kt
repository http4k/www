package content.ecosystem.connect.reference.openfeature

import org.http4k.chaos.start
import org.http4k.connect.openfeature.FakeOpenFeature

val fakeOpenFeature = FakeOpenFeature().start()
