package content.tutorial.build_a_simple_mcp_app

import org.http4k.server.Jetty
import org.http4k.server.asServer

fun main() {
    val server = GithubReleasePlanner().asServer(Jetty(9000)).start()

    println("Server started on " + server.port())
}
