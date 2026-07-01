package content.ecosystem.pro.reference.mcp

import org.http4k.ai.mcp.ToolResponse
import org.http4k.ai.mcp.model.Tool
import org.http4k.ai.mcp.util.McpJson.auto
import org.http4k.lens.with
import org.http4k.routing.bind

// a complex response object
data class MavenJar(val org: String, val name: String, val version: Int)

// the auto() method is imported from McpJson (requires Kotlin Reflect)
val libDescription = Tool.Arg
    .auto(_root_ide_package_.content.ecosystem.pro.reference.mcp.MavenJar("org.http4k", "http4k-ai-mcp-sdk", 6))
    .required("the maven dependency")

// the auto() method is imported from McpJson (requires Kotlin Reflect)
val nextVersion = Tool.Output.auto(
    _root_ide_package_.content.ecosystem.pro.reference.mcp.MavenJar(
        "org.http4k",
        "http4k-ai-mcp-sdk",
        6
    )
).toLens()

val getNextVersion = Tool(
    "nextVersion",
    "get the next maven version for a library",
    _root_ide_package_.content.ecosystem.pro.reference.mcp.libDescription,
    output = _root_ide_package_.content.ecosystem.pro.reference.mcp.nextVersion
)

object MavenTool {
    @JvmStatic
    fun main() = println(
        _root_ide_package_.content.ecosystem.pro.reference.mcp.getNextVersion bind {
            // we can extract the class automatically using the lens
            val lib: content.ecosystem.pro.reference.mcp.MavenJar =
                _root_ide_package_.content.ecosystem.pro.reference.mcp.libDescription(it)

            // and then inject the typesafe response object!
            ToolResponse.Ok().with(_root_ide_package_.content.ecosystem.pro.reference.mcp.nextVersion of lib.copy(version = lib.version + 1))
        }
    )
}
