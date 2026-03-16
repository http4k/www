package content.ecosystem.connect.reference.amazon.secretsmanager

import org.http4k.chaos.start
import org.http4k.connect.amazon.secretsmanager.FakeSecretsManager

fun `start fake`() {
    FakeSecretsManager().start()
}
