package content.news.an_mcp_server_in_a_tweet

import org.http4k.ai.mcp.ToolResponse.Ok
import org.http4k.ai.mcp.model.Content.Text
import org.http4k.ai.mcp.model.Tool
import org.http4k.ai.mcp.server.asServer
import org.http4k.routing.bind
import org.http4k.server.Jetty
import java.time.Instant.now

fun main() {
    val mcp = Tool("clock", "get the time") bind { Ok(Text(now())) }
    mcp.asServer(Jetty(3000)).start()
}
