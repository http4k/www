package content.ecosystem.ai.reference.mcp

import org.http4k.ai.mcp.ResourceHandler
import org.http4k.ai.mcp.ResourceResponse
import org.http4k.ai.mcp.model.Resource
import org.http4k.routing.bind
import java.io.File

// Define a templated resource using RFC 6570 URI templates
val templatedFileResource = Resource.Templated(
    uriTemplate = "file://{+path}",      // {+path} allows slashes in the path
    name = "files",
    description = "Access files by path"
)

// Handler extracts the path from the URI and reads the file
val templatedFileResourceHandler: ResourceHandler = { request ->
    val path = request.uri.toString().substringAfter("file://")
    val file = File("/data", path)

    when {
        file.exists() && file.isFile -> ResourceResponse(
            Resource.Content.Text(file.readText(), request.uri)
        )

        else -> throw IllegalArgumentException("File not found: $path")
    }
}

// Bind the templated resource to its handler
val boundTemplatedFileResource = templatedFileResource bind templatedFileResourceHandler
