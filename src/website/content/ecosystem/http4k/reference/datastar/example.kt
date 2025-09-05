package content.ecosystem.http4k.reference.datastar

import org.http4k.core.Body
import org.http4k.core.ContentType.Companion.TEXT_HTML
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status.Companion.OK
import org.http4k.core.with
import org.http4k.datastar.Element
import org.http4k.filter.debug
import org.http4k.lens.datastarElements
import org.http4k.routing.poly
import org.http4k.routing.sse.bind
import org.http4k.server.Helidon
import org.http4k.server.asServer
import org.http4k.sse.SseResponse
import org.http4k.sse.sendPatchElements
import org.http4k.template.DatastarElementRenderer
import org.http4k.template.HandlebarsTemplates
import org.http4k.template.ViewModel
import org.http4k.template.viewModel
import java.time.Instant
import java.time.temporal.ChronoUnit.SECONDS
import org.http4k.routing.bind as bindHttp

// a standard view model using the Handlebars template engine for both elements and pages
data class ElementModel(val content: String) : ViewModel
data object Index : ViewModel

// wrap the renderer in the DatastarElementRenderer to convert each rendered template into an Element
val templateRenderer = HandlebarsTemplates().CachingClasspath()
val elementRenderer = DatastarElementRenderer(templateRenderer)
val pageLens = Body.viewModel(templateRenderer, TEXT_HTML).toLens()

fun main() {
    poly(
        // SSE ROUTES

        // send a single element as an SSE datastar event
        "sse/rawFragment" bind { req: Request ->
            SseResponse {
                it.sendPatchElements(Element.of("""<div id="toBeReplaced">Raw SSE Element</div>""")).close()
            }
        },
        // update each single element as an SSE datastar event
        "sse/usingTemplate" bind { req: Request ->
            SseResponse {
                // we can simulate a stream of data here
                while (true) {
                    val newTime = Instant.now().truncatedTo(SECONDS).toString()
                    it.sendPatchElements(elementRenderer(ElementModel(newTime)))
                    Thread.sleep(2000)
                }
            }
        },

        // HTTP ROUTES

        // send a single fragment in the response normally
        "http/rawFragment" bindHttp { req: Request ->
            Response(OK).datastarElements(Element.of("""<div id="toBeReplaced">Raw HTTP Element</div>"""))
        },
        // send a single element in the response using the renderer
        "http/usingTemplate" bindHttp { req: Request ->
            Response(OK).datastarElements(elementRenderer(ElementModel("HTTP template")))
        },
        // render our page template and send it in the response
        "/" bindHttp { req: Request ->
            Response(OK).with(pageLens of Index)
        }
    ).debug().asServer(Helidon(8000)).start()
}
