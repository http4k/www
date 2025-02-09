package content.ecosystem.http4k.reference.model_context_protocol

import org.http4k.client.JavaHttpClient
import org.http4k.core.Method.GET
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Uri
import org.http4k.mcp.ResourceResponse
import org.http4k.mcp.model.Resource
import org.http4k.mcp.model.ResourceName
import org.http4k.mcp.server.capability.ResourceCapability
import org.http4k.routing.bind
import org.jsoup.Jsoup

fun LookupAllLinksFromWebResource(): ResourceCapability {
    val http = JavaHttpClient()
    return Resource.Static(Uri.of("https://http4k.org"), ResourceName.of("HTTP4K"), "description") bind {
        val htmlPage = http(Request(GET, it.uri))

        val links = getAllLinksFrom(htmlPage)
            .map { Resource.Content.Text(it.text(), Uri.of(it.attr("href"))) }

        ResourceResponse(links)
    }
}

private fun getAllLinksFrom(htmlPage: Response) = Jsoup.parse(htmlPage.bodyString())
    .allElements.toList()
    .filter { it.tagName() == "a" }
    .filter { it.hasAttr("href") }

