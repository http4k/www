package content.tutorial.create_an_mcp_server

import org.http4k.ai.mcp.ToolResponse.Ok
import org.http4k.ai.mcp.model.Content.Text
import org.http4k.ai.mcp.model.Tool
import org.http4k.ai.mcp.protocol.ServerMetaData
import org.http4k.ai.mcp.server.security.NoMcpSecurity
import org.http4k.routing.bind
import org.http4k.routing.mcp
import org.http4k.server.Jetty
import org.http4k.server.asServer
import java.time.Instant.now

fun main() {
    mcp(
        ServerMetaData("Clock", "1.0.0"), NoMcpSecurity,
        Tool("clock", "get the time") bind { Ok(Text(now())) }
    ).asServer(Jetty(3000)).start()
}
