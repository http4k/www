package content.ecosystem.connect.reference.amazon.evidently

import org.http4k.chaos.start
import org.http4k.connect.amazon.evidently.FakeEvidently

val evidently = FakeEvidently().start()
