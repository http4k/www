package content.ecosystem.ai.reference.mcp

import org.http4k.ai.mcp.ToolResponse
import org.http4k.ai.mcp.model.Content.Text
import org.http4k.ai.mcp.model.Tool
import org.http4k.ai.mcp.protocol.ServerMetaData
import org.http4k.ai.mcp.server.security.NoMcpSecurity
import org.http4k.routing.bind
import org.http4k.routing.mcpJsonRpc
import org.http4k.server.SunHttp
import org.http4k.server.asServer

object JsonrpcServerExample {
    @JvmStatic
    fun main(args: Array<String>) {
        // Create a pure JSON-RPC based MCP server
        // Accepts standard JSON-RPC 2.0 messages
        val server = mcpJsonRpc(
            ServerMetaData("JSON-RPC MCP Server", "1.0.0"),
            NoMcpSecurity,
            Tool("echo", "Echo the input") bind {
                ToolResponse.Ok(Text("Echo received"))
            }
        )

        // JSON-RPC endpoint is available at /jsonrpc
        server.asServer(SunHttp(3001)).start()
    }
}
