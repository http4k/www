package content.tutorial.build_a_simple_mcp_app

import org.http4k.ai.mcp.apps.McpAppsHost
import org.http4k.ai.mcp.testing.McpClientFactory
import org.http4k.core.Uri
import org.http4k.server.Jetty
import org.http4k.server.SunHttp
import org.http4k.server.asServer

fun main() {
    val server = GithubReleasePlanner().asServer(Jetty(0)).start()

    val host = McpAppsHost(
        McpClientFactory.Http(Uri.of("http://localhost:${server.port()}/mcp"))
    ).asServer(SunHttp(10000)).start()

    println("MCP Apps Host running on http://localhost:${host.port()}")
}
