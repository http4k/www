package content.ecosystem.http4k.reference.mcp

import org.http4k.client.JavaHttpClient
import org.http4k.core.Method.GET
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Uri
import org.http4k.ai.mcp.ResourceHandler
import org.http4k.ai.mcp.ResourceRequest
import org.http4k.ai.mcp.ResourceResponse
import org.http4k.ai.mcp.model.Resource
import org.http4k.ai.mcp.model.ResourceName
import org.jsoup.Jsoup


val websiteResource = Resource.Static(Uri.of("https://http4k.org"), ResourceName.of("HTTP4K"), "description")

// this function provides a static resource that contains all the links from the http4k website
val getLinksResourceHandler: ResourceHandler = {
    val htmlPage = JavaHttpClient()(Request(GET, it.uri))

    val links = getAllLinksFrom(htmlPage)
        .map { Resource.Content.Text(it.text(), Uri.of(it.attr("href"))) }

    ResourceResponse(links)
}

private fun getAllLinksFrom(htmlPage: Response) = Jsoup.parse(htmlPage.bodyString())
    .allElements.toList()
    .filter { it.tagName() == "a" }
    .filter { it.hasAttr("href") }

object LookupAllLinksFromWebResource {
    @JvmStatic
    fun main() = println(
        // invoke/test the prompt offline - just invoke it like a function
        getLinksResourceHandler(ResourceRequest(Uri.of("https://http4k.org")))
    )
}
