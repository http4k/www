package content.tutorial.build_an_a2a_agent

import org.http4k.server.Jetty
import org.http4k.server.asServer

fun main() {
    RecipeAgent().asServer(Jetty(9000)).start()
    println("Recipe Agent running on http://localhost:9000")
    println("Agent Card at http://localhost:9000/.well-known/agent-card.json")
}
