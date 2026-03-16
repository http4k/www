package content.ecosystem.connect.reference.x402

import dev.forkhandles.result4k.Success
import org.http4k.client.JavaHttpClient
import org.http4k.connect.x402.X402Facilitator
import org.http4k.connect.x402.X402Signer
import org.http4k.connect.x402.model.PaymentPayload
import org.http4k.connect.x402.model.PaymentScheme
import org.http4k.core.Method.GET
import org.http4k.core.Request
import org.http4k.core.Uri
import org.http4k.core.then
import org.http4k.filter.ClientFilters
import org.http4k.filter.X402PaymentRequired

fun `using the client filter`() {
    val signer = X402Signer { reqs ->
        val req = reqs.first()
        Success(
            PaymentPayload(
                x402Version = 2,
                scheme = req.scheme,
                network = req.network,
                payload = mapOf("signature" to "0xsigned"),
                resource = "https://api.example.com/data",
                description = "Paid resource"
            )
        )
    }

    val client = ClientFilters.X402PaymentRequired(signer)
        .then(ClientFilters.SetBaseUriFrom(Uri.of("https://api.example.com")))
        .then(JavaHttpClient())

    val response = client(Request(GET, "/premium-data"))
}
