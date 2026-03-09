package content.news.an_mcp_server_in_a_tweet

import org.http4k.ai.mcp.ToolResponse.Ok
import org.http4k.ai.mcp.model.Content.Text
import org.http4k.ai.mcp.model.Tool
import org.http4k.ai.mcp.model.string
import org.http4k.ai.mcp.protocol.ServerMetaData
import org.http4k.ai.mcp.server.security.NoMcpSecurity
import org.http4k.routing.bind
import org.http4k.routing.mcp
import org.http4k.server.Helidon
import org.http4k.server.asServer

fun main() {
    val name = Tool.Arg.string().required("name")

    mcp(
        ServerMetaData("Greeter", "1.0.0"), NoMcpSecurity,
        Tool("greet", "Say hello", name) bind { Ok(Text("Hello, ${name(it)}!")) }
    ).asServer(Helidon(3000)).start()
}
