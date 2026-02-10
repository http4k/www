package content.ecosystem.ai.reference.mcp

import org.http4k.ai.mcp.ToolResponse
import org.http4k.ai.mcp.model.Content.Text
import org.http4k.ai.mcp.model.Tool
import org.http4k.ai.mcp.protocol.ServerMetaData
import org.http4k.ai.mcp.server.security.NoMcpSecurity
import org.http4k.routing.bind
import org.http4k.routing.mcpWebsocket
import org.http4k.server.Helidon
import org.http4k.server.asServer

object WebsocketServerExample {
    @JvmStatic
    fun main(args: Array<String>) {
        // Create a WebSocket-based MCP server
        // Provides full-duplex communication over a single connection
        val server = mcpWebsocket(
            ServerMetaData("WebSocket MCP Server", "1.0.0"),
            NoMcpSecurity,
            Tool("ping", "Ping the server") bind {
                ToolResponse.Ok(Text("pong"))
            }
        )

        // WebSocket endpoint is available at /ws
        server.asServer(Helidon(3001)).start()
    }
}
