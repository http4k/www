package content.ecosystem.ai.reference.mcp

import dev.forkhandles.result4k.Success
import org.http4k.ai.mcp.ToolResponse.Ok
import org.http4k.ai.mcp.model.Content
import org.http4k.ai.mcp.model.McpEntity
import org.http4k.ai.mcp.model.Tool
import org.http4k.ai.mcp.mpp.MppPaymentCheck
import org.http4k.ai.mcp.mpp.MppPayments
import org.http4k.ai.mcp.mpp.MppToolFilter
import org.http4k.ai.mcp.protocol.ServerMetaData
import org.http4k.ai.mcp.protocol.Version
import org.http4k.ai.mcp.protocol.withExtensions
import org.http4k.ai.mcp.server.capability.then
import org.http4k.ai.mcp.server.security.NoMcpSecurity
import org.http4k.connect.mpp.MppVerifier
import org.http4k.connect.mpp.model.Challenge
import org.http4k.connect.mpp.model.ChallengeId
import org.http4k.connect.mpp.model.ChargeRequest
import org.http4k.connect.mpp.model.Currency
import org.http4k.connect.mpp.model.PaymentAmount
import org.http4k.connect.mpp.model.PaymentIntent
import org.http4k.connect.mpp.model.PaymentMethod
import org.http4k.connect.mpp.model.Realm
import org.http4k.connect.mpp.model.Receipt
import org.http4k.connect.mpp.model.ReceiptStatus
import org.http4k.routing.bind
import org.http4k.routing.mcp
import org.http4k.server.JettyLoom
import org.http4k.server.asServer
import java.time.Instant

fun `mpp mcp tool example`() {
    val method = PaymentMethod.of("stripe")
    val intent = PaymentIntent.of("charge")

    val challenge = Challenge(
        id = ChallengeId.of("challenge-123"),
        realm = Realm.of("api.example.com"),
        method = method,
        intent = intent,
        request = ChargeRequest(
            amount = PaymentAmount.of("100"),
            currency = Currency.of("USD")
        )
    )

    val verifier = MppVerifier { credential ->
        Success(
            Receipt(
                status = ReceiptStatus.success,
                method = credential.challenge.method,
                timestamp = Instant.now(),
                challengeId = credential.challenge.id
            )
        )
    }

    // create a ToolFilter that requires payment for all tools it wraps
    val paymentFilter = MppToolFilter(verifier) { MppPaymentCheck.Required(listOf(challenge)) }

    // wrap individual tools with the payment filter
    val paidTool = paymentFilter.then(
        Tool("premium_data", "get premium data (requires payment)") bind {
            Ok(listOf(Content.Text("Here is your premium data!")))
        }
    )

    // free tools can be mixed alongside paid tools
    val freeTool = Tool("free_data", "get free data") bind {
        Ok(listOf(Content.Text("Here is your free data!")))
    }

    mcp(
        ServerMetaData(McpEntity.of("mpp mcp server"), Version.of("0.1.0"))
            .withExtensions(MppPayments(methods = listOf(method), intents = listOf(intent))),
        NoMcpSecurity,
        paidTool, freeTool
    ).asServer(JettyLoom(3001)).start()
}
