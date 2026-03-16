package content.ecosystem.connect.reference.x402

import org.http4k.connect.x402.FakeX402Facilitator
import org.http4k.connect.x402.X402Facilitator
import org.http4k.connect.x402.model.AssetAddress
import org.http4k.connect.x402.model.PaymentAmount
import org.http4k.connect.x402.model.PaymentNetwork
import org.http4k.connect.x402.model.PaymentRequirements
import org.http4k.connect.x402.model.PaymentScheme
import org.http4k.connect.x402.model.WalletAddress
import org.http4k.core.Response
import org.http4k.core.Status.Companion.OK
import org.http4k.core.Uri
import org.http4k.core.then
import org.http4k.filter.ServerFilters
import org.http4k.filter.X402PaymentRequired

fun `using the server filter`() {
    val facilitator = FakeX402Facilitator().client()

    val requirements = PaymentRequirements(
        scheme = PaymentScheme.of("exact"),
        network = PaymentNetwork.of("base-sepolia"),
        asset = AssetAddress.of("0xUSDC"),
        amount = PaymentAmount.of("100"),
        payTo = WalletAddress.of("0xmerchant"),
        maxTimeoutSeconds = 30
    )

    val app = ServerFilters.X402PaymentRequired(facilitator) { listOf(requirements) }
        .then { Response(OK).body("premium content") }
}
