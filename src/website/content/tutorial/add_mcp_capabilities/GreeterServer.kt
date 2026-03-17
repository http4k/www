package content.tutorial.add_mcp_capabilities

import org.http4k.ai.mcp.protocol.ServerMetaData
import org.http4k.ai.mcp.server.capability.CapabilityPack
import org.http4k.ai.mcp.server.security.NoMcpSecurity
import org.http4k.routing.mcp
import org.http4k.server.Jetty
import org.http4k.server.asServer

fun GreeterServer() = mcp(
    ServerMetaData("Greeter", "1.0.0"), NoMcpSecurity,
    CapabilityPack(greetTool, guidelines), greetingPrompt
)

fun main() {
    GreeterServer().asServer(Jetty(3000)).start()
}
