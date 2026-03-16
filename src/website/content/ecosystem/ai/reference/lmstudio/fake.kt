package content.ecosystem.ai.reference.lmstudio

import org.http4k.chaos.start
import org.http4k.connect.lmstudio.FakeLmStudio

fun `start fake`() {
    FakeLmStudio().start()
}
