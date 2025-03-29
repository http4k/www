package content.news.http4k_mcp_has_landed

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.http4k.lens.with
import org.http4k.mcp.ToolRequest
import org.http4k.mcp.ToolResponse
import org.http4k.mcp.model.Content
import org.http4k.mcp.model.Tool
import org.http4k.routing.bind

val weatherTool = Tool("weather", "Gets weather for a city", cityArg) bind { req ->
    val city = cityArg(req)
    ToolResponse.Ok(Content.Text("Weather in $city: Sunny and 25°C"))
}

//@Test
fun `can call a tool as a function`() {
    // Test it directly - no server needed!
    val request = ToolRequest().with(cityArg of "London")
    val response = weatherTool(request) as ToolResponse.Ok
    assertThat(response, equalTo(ToolResponse.Ok(Content.Text("Weather in London: Sunny and 25°C"))))
}
