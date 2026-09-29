package content.ecosystem.ai.reference.typesafe

import org.http4k.chaos.start
import org.http4k.connect.typesafe.FakeTypeSafe

val typeSafe = FakeTypeSafe().start()
