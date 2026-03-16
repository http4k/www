package content.ecosystem.ai.reference.mcp

import org.http4k.ai.mcp.ToolResponse.Ok
import org.http4k.ai.mcp.model.Content
import org.http4k.ai.mcp.model.McpEntity
import org.http4k.ai.mcp.model.Tool
import org.http4k.ai.mcp.protocol.ServerMetaData
import org.http4k.ai.mcp.protocol.Version
import org.http4k.ai.mcp.server.capability.then
import org.http4k.ai.mcp.server.security.NoMcpSecurity
import org.http4k.ai.mcp.x402.PaymentCheck
import org.http4k.ai.mcp.x402.X402ToolFilter
import org.http4k.connect.x402.X402Facilitator
import org.http4k.connect.x402.Http
import org.http4k.connect.x402.model.AssetAddress
import org.http4k.connect.x402.model.PaymentAmount
import org.http4k.connect.x402.model.PaymentNetwork
import org.http4k.connect.x402.model.PaymentRequirements
import org.http4k.connect.x402.model.PaymentScheme
import org.http4k.connect.x402.model.WalletAddress
import org.http4k.core.Uri
import org.http4k.routing.bind
import org.http4k.routing.mcp
import org.http4k.server.JettyLoom
import org.http4k.server.asServer

fun `x402 mcp tool example`() {
    val requirements = PaymentRequirements(
        scheme = PaymentScheme.of("exact"),
        network = PaymentNetwork.of("base-sepolia"),
        asset = AssetAddress.of("0x036CbD53842c5426634e7929541eC2318f3dCF7e"),
        amount = PaymentAmount.of("100"),
        payTo = WalletAddress.of("0x1234567890abcdef1234567890abcdef12345678"),
        maxTimeoutSeconds = 60
    )

    val facilitator = X402Facilitator.Http(Uri.of("https://x402-facilitator.example.com"))

    // create a ToolFilter that requires payment for all tools it wraps
    val paymentFilter = X402ToolFilter(facilitator) { PaymentCheck.Required(listOf(requirements)) }

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
        ServerMetaData(McpEntity.of("x402 mcp server"), Version.of("0.1.0")),
        NoMcpSecurity,
        paidTool, freeTool
    ).asServer(JettyLoom(3001)).start()
}
