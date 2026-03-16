package content.ecosystem.ai.reference.anthropic

import org.http4k.chaos.start
import org.http4k.connect.anthropic.FakeAnthropicAI

fun `start fake`() {
    FakeAnthropicAI().start()
}
