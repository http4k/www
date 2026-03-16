package content.news.an_mcp_server_in_a_tweet

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

fun `x402 tools`() {
    val facilitator = X402Facilitator.Http(Uri.of("https://x402-facilitator.example.com"))

    val payPerCall = X402ToolFilter(facilitator) {
        PaymentCheck.Required(
            listOf(
                PaymentRequirements(
                    scheme = PaymentScheme.of("exact"),
                    network = PaymentNetwork.of("base-sepolia"),
                    asset = AssetAddress.of("0x036CbD53842c5426634e7929541eC2318f3dCF7e"),
                    amount = PaymentAmount.of("100"),
                    payTo = WalletAddress.of("0xYOUR_WALLET_ADDRESS"),
                    maxTimeoutSeconds = 60
                )
            )
        )
    }

    // paid tool - wrapped with the payment filter
    val paidTool = payPerCall.then(
        Tool("premium_data", "premium data (pay-per-call)") bind {
            Ok(listOf(Content.Text("Here is your premium data!")))
        }
    )

    // free tool - no filter, no charge
    val freeTool = Tool("free_data", "free data") bind {
        Ok(listOf(Content.Text("Here is your free data!")))
    }

    mcp(
        ServerMetaData(McpEntity.of("x402 server"), Version.of("0.1.0")),
        NoMcpSecurity,
        paidTool, freeTool
    ).asServer(JettyLoom(3001)).start()
}
