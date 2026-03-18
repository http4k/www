package content.ecosystem.connect.reference.mpp

import dev.forkhandles.result4k.Success
import org.http4k.connect.mpp.MppVerifier
import org.http4k.connect.mpp.model.Challenge
import org.http4k.connect.mpp.model.ChallengeId
import org.http4k.connect.mpp.model.ChargeRequest
import org.http4k.connect.mpp.model.Currency
import org.http4k.connect.mpp.model.PaymentAmount
import org.http4k.connect.mpp.model.PaymentIntent
import org.http4k.connect.mpp.model.PaymentMethod
import org.http4k.connect.mpp.model.PaymentReference
import org.http4k.connect.mpp.model.Realm
import org.http4k.connect.mpp.model.Receipt
import org.http4k.connect.mpp.model.ReceiptStatus
import org.http4k.core.Response
import org.http4k.core.Status.Companion.OK
import org.http4k.core.then
import org.http4k.routing.bind
import org.http4k.routing.routes
import org.http4k.security.MppSecurity
import java.time.Instant

fun `using MppSecurity`() {
    val verifier = MppVerifier { credential ->
        Success(
            Receipt(
                status = ReceiptStatus.success,
                method = credential.challenge.method,
                timestamp = Instant.now(),
                challengeId = credential.challenge.id,
                reference = PaymentReference.of("tx-456")
            )
        )
    }

    val challenge = Challenge(
        id = ChallengeId.of("challenge-123"),
        realm = Realm.of("api.example.com"),
        method = PaymentMethod.of("stripe"),
        intent = PaymentIntent.of("charge"),
        request = ChargeRequest(
            amount = PaymentAmount.of("100"),
            currency = Currency.of("USD")
        )
    )

    val security = MppSecurity({ challenge }, verifier)

    val app = routes(
        "/premium" bind security.filter.then { Response(OK).body("paid content") }
    )
}
