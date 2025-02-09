package content.ecosystem.http4k.reference.datastar

import org.http4k.core.Body
import org.http4k.core.ContentType.Companion.TEXT_HTML
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status.Companion.OK
import org.http4k.core.with
import org.http4k.datastar.Fragment
import org.http4k.filter.debug
import org.http4k.lens.datastarFragments
import org.http4k.routing.poly
import org.http4k.routing.sse.bind
import org.http4k.server.Helidon
import org.http4k.server.asServer
import org.http4k.sse.SseResponse
import org.http4k.sse.sendMergeFragments
import org.http4k.template.DatastarFragmentRenderer
import org.http4k.template.HandlebarsTemplates
import org.http4k.template.ViewModel
import org.http4k.template.viewModel
import java.time.Instant
import java.time.temporal.ChronoUnit.SECONDS
import org.http4k.routing.bind as bindHttp

// a standard view model using the Handlebars template engine for both fragments and pages
data class FragmentModel(val content: String) : ViewModel
data object Index : ViewModel

// wrap the renderer in the DatastarFragmentRenderer to convert each rendered template into a Fragment
val templateRenderer = HandlebarsTemplates().CachingClasspath()
val fragmentRenderer = DatastarFragmentRenderer(templateRenderer)
val pageLens = Body.viewModel(templateRenderer, TEXT_HTML).toLens()

fun main() {
    poly(
        // SSE ROUTES

        // send a single fragment as an SSE datastar event
        "sse/rawFragment" bind { req: Request ->
            SseResponse {
                it.sendMergeFragments(Fragment.of("""<div id="toBeReplaced">Raw SSE Fragment</div>""")).close()
            }
        },
        // update each single fragment as an SSE datastar event
        "sse/usingTemplate" bind { req: Request ->
            SseResponse {
                // we can simulate a stream of data here
                while (true) {
                    val newTime = Instant.now().truncatedTo(SECONDS).toString()
                    it.sendMergeFragments(fragmentRenderer(FragmentModel(newTime)))
                    Thread.sleep(2000)
                }
            }
        },

        // HTTP ROUTES

        // send a single fragment in the response normally
        "http/rawFragment" bindHttp { req: Request ->
            Response(OK).datastarFragments(Fragment.of("""<div id="toBeReplaced">Raw HTTP Fragment</div>"""))
        },
        // send a single fragment in the response using the renderer
        "http/usingTemplate" bindHttp { req: Request ->
            Response(OK).datastarFragments(fragmentRenderer(FragmentModel("HTTP template")))
        },
        // render our page template and send it in the response
        "/" bindHttp { req: Request ->
            Response(OK).with(pageLens of Index)
        }
    ).debug().asServer(Helidon(8000)).start()
}
