package content.ecosystem.ai.reference.mcp

import org.http4k.ai.mcp.ResourceRequest
import org.http4k.ai.mcp.ResourceResponse
import org.http4k.ai.mcp.ToolRequest
import org.http4k.ai.mcp.ToolResponse
import org.http4k.ai.mcp.model.Content.Text
import org.http4k.ai.mcp.model.Resource
import org.http4k.ai.mcp.model.Tool
import org.http4k.ai.mcp.model.string
import org.http4k.ai.mcp.protocol.ServerMetaData
import org.http4k.ai.mcp.server.security.NoMcpSecurity
import org.http4k.ai.mcp.testing.testMcpClient
import org.http4k.ai.model.ToolName
import org.http4k.core.Uri
import org.http4k.lens.with
import org.http4k.routing.bind
import org.http4k.routing.mcpHttpStreaming

object TestingExample {
    @JvmStatic
    fun main(args: Array<String>) {
        val nameLens = Tool.Arg.string().required("name")

        // Create your MCP server
        val mcpServer = mcpHttpStreaming(
            ServerMetaData("Test Server", "1.0.0"),
            NoMcpSecurity,
            Tool("greet", "Greet someone", nameLens) bind { request ->
                ToolResponse.Ok(Text("Hello, ${nameLens(request)}!"))
            },
            Resource.Static("file://readme", "readme", "The readme") bind { req ->
                ResourceResponse(Resource.Content.Text("# Welcome", req.uri))
            }
        )

        // Create in-memory test client - no network needed!
        val client = mcpServer.testMcpClient()
        client.start()

        // Test tools - returns McpResult<List<McpTool>>
        val toolsResult = client.tools().list()
        println("Available tools: $toolsResult")

        // Call a tool
        val callResult = client.tools().call(
            ToolName.of("greet"),
            ToolRequest().with(nameLens of "World")
        )
        println("Tool result: $callResult")

        // Test resources - returns McpResult<List<McpResource>>
        val resourcesResult = client.resources().list()
        println("Available resources: $resourcesResult")

        // Read a resource
        val content = client.resources().read(ResourceRequest(Uri.of("file://readme")))
        println("Resource content: $content")

        // Test prompts - returns McpResult<List<McpPrompt>>
        val promptsResult = client.prompts().list()
        println("Available prompts: $promptsResult")

        client.close()
    }
}
