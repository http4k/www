package content.ecosystem.http4k.reference.model_context_protocol

import org.http4k.client.JavaHttpClient
import org.http4k.core.BodyMode
import org.http4k.core.Method.GET
import org.http4k.core.Request
import org.http4k.mcp.ToolRequest
import org.http4k.mcp.client.SseMcpClient
import org.http4k.mcp.model.McpEntity
import org.http4k.mcp.model.ToolName
import org.http4k.mcp.protocol.ClientCapabilities
import org.http4k.mcp.protocol.Version

fun main() {
    val client = SseMcpClient(
        McpEntity.of("http4k MCP Client"), Version.of("1.0.0"),
        ClientCapabilities(),
        Request(GET, "http://localhost:3001/sse"),
        JavaHttpClient(responseBodyMode = BodyMode.Stream)
    )

    println(client.start())

    println(client.tools().list().getOrThrow())

    println(
        client.tools().call(
            ToolName.of("diary_for_David"),
            ToolRequest(mapOf("date" to "2025-03-21"))
        ).getOrThrow()
    )
}
