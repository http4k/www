package content.tutorial.add_mcp_capabilities

import org.http4k.ai.mcp.ToolResponse.Ok
import org.http4k.ai.mcp.model.Content.Text
import org.http4k.ai.mcp.model.Tool
import org.http4k.ai.mcp.model.string
import org.http4k.routing.bind

val name = Tool.Arg.string().required("name", "Who to greet")

val greetTool = Tool("greet", "Say hello", name) bind { Ok(Text("Hello, ${name(it)}!")) }
