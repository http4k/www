package content.ecosystem.http4k.reference.mcp

import org.http4k.mcp.ToolResponse
import org.http4k.mcp.model.Tool
import org.http4k.mcp.util.McpJson.auto
import org.http4k.routing.bind

// a complex request object
data class MavenJar(val org: String, val name: String, val version: String) {
    override fun toString() = "$org:$name:$version"
}

// the auto() method is imported from McpJson (requires Kotlin Reflect)
val libDescription = Tool.Arg
    .auto(MavenJar("org.http4k", "http4k-mcp-sdk", "6.0.0.0"))
    .required("the maven dependency")

val describeMavenLib = Tool(
    "describeLibrary",
    "extracts the maven coordinates from a library name",
    libDescription
)

object MavenTool {
    @JvmStatic
    fun main() = println(
        describeMavenLib bind {
            // we can extract the class automatically using the lens
            val lib: MavenJar = libDescription(it)
            ToolResponse.Ok("${lib.org}:${lib.name}:${lib.version}")
        }
    )
}
