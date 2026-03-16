package content.ecosystem.ai.reference.ollama

import org.http4k.chaos.start
import org.http4k.connect.ollama.FakeOllama

fun `start fake`() {
    FakeOllama().start()
}
