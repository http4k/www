package content.tutorial.add_mcp_capabilities

import org.http4k.ai.mcp.ResourceResponse
import org.http4k.ai.mcp.model.Resource
import org.http4k.ai.mcp.model.Resource.Content.Text
import org.http4k.core.Uri
import org.http4k.routing.bind

val guidelines = Resource.Static("greeting://guidelines", "Greeting style guide") bind {
    ResourceResponse(
        Text(
            "Always greet warmly. Use the person's name. Keep it under 20 words.",
            Uri.of("greeting://guidelines")
        )
    )
}
