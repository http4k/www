package content.ecosystem.ai.reference.azure

import org.http4k.chaos.start
import org.http4k.connect.azure.FakeAzureAI

fun `start fake`() {
    FakeAzureAI().start()
}
