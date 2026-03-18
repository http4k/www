package content.ecosystem.ai.reference.mcp

import dev.forkhandles.result4k.Success
import org.http4k.ai.mcp.ToolResponse.Ok
import org.http4k.ai.mcp.model.Content
import org.http4k.ai.mcp.model.McpEntity
import org.http4k.ai.mcp.model.Tool
import org.http4k.ai.mcp.mpp.MppPaymentCheck
import org.http4k.ai.mcp.mpp.MppPayments
import org.http4k.ai.mcp.protocol.ServerMetaData
import org.http4k.ai.mcp.protocol.Version
import org.http4k.ai.mcp.protocol.withExtensions
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
import org.http4k.ai.mcp.mpp.MppPaymentRequired
import org.http4k.filter.McpFilters
import org.http4k.routing.bind
import org.http4k.routing.mcp
import org.http4k.server.JettyLoom
import org.http4k.server.asServer
import java.time.Instant

fun `mpp mcp filter example`() {
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

    // McpFilter gates ALL MCP requests — tools, prompts, resources, etc.
    val mcpFilter = McpFilters.MppPaymentRequired(verifier) { MppPaymentCheck.Required(listOf(challenge)) }

    val tool = Tool("premium_data", "get premium data") bind {
        Ok(listOf(Content.Text("Here is your premium data!")))
    }

    mcp(
        ServerMetaData(McpEntity.of("mpp mcp server"), Version.of("0.1.0"))
            .withExtensions(MppPayments(methods = listOf(method), intents = listOf(intent))),
        NoMcpSecurity,
        tool,
        mcpFilter = mcpFilter
    ).asServer(JettyLoom(3001)).start()
}
