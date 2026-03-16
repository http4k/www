package content.ecosystem.ai.reference.openai

import org.http4k.chaos.start
import org.http4k.connect.openai.FakeOpenAI

fun `start fake`() {
    FakeOpenAI().start()
}
