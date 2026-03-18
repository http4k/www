package content.ecosystem.connect.reference.mpp

import dev.forkhandles.result4k.Success
import org.http4k.client.JavaHttpClient
import org.http4k.connect.mpp.MppSigner
import org.http4k.connect.mpp.model.Credential
import org.http4k.core.Method.GET
import org.http4k.core.Request
import org.http4k.core.Uri
import org.http4k.core.then
import org.http4k.filter.ClientFilters
import org.http4k.filter.MppPaymentRequired

fun `using the client filter`() {
    val signer = MppSigner { challenge ->
        Success(
            Credential(
                challenge = challenge,
                payload = mapOf("token" to "payment-token-123")
            )
        )
    }

    val client = ClientFilters.MppPaymentRequired(signer)
        .then(ClientFilters.SetBaseUriFrom(Uri.of("https://api.example.com")))
        .then(JavaHttpClient())

    val response = client(Request(GET, "/premium-data"))
}
