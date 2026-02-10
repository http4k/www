package content.ecosystem.ai.reference.mcp

import org.http4k.ai.mcp.server.capability.DirectoryResources
import org.http4k.ai.mcp.server.capability.RecursionMode.Flat
import org.http4k.ai.mcp.server.capability.RecursionMode.Recursive
import java.io.File

object DirectoryResourcesExample {
    @JvmStatic
    fun main(args: Array<String>) {
        // Expose a directory as browsable MCP resources
        // DirectoryResources implements the Resources interface
        val recursiveResources = DirectoryResources(
            dir = File("/path/to/documents"),
            recursive = Recursive  // walks subdirectories
        )

        val flatResources = DirectoryResources(
            dir = File("/path/to/documents"),
            recursive = Flat  // only top-level files
        )

        // DirectoryResources provides:
        // - listResources() - Lists all files in the directory
        // - listTemplates() - Returns URI templates for dynamic access
        // - read() - Reads file content by URI

        // Pass to McpProtocol constructor for use with custom server setup,
        // or use individual Resource.Static bindings for simpler cases
    }
}
