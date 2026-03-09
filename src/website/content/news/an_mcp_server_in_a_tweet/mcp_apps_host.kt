package content.news.an_mcp_server_in_a_tweet

import org.http4k.ai.mcp.apps.McpAppsHost
import org.http4k.ai.mcp.testing.McpClientFactory
import org.http4k.core.Uri
import org.http4k.server.Jetty
import org.http4k.server.SunHttp
import org.http4k.server.asServer

fun main() {
    val server = MyMcpApp().asServer(Jetty(9000)).start()

    val client = McpClientFactory.Http(Uri.of("http://localhost:${server.port()}/mcp"))

    val host = McpAppsHost(client).asServer(SunHttp(10000)).start()

    println("MCP Apps Host running on http://localhost:${host.port()}")
}
