package content.ecosystem.http4k.reference.datastar

import org.http4k.core.Body
import org.http4k.core.ContentType.Companion.TEXT_HTML
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status.Companion.OK
import org.http4k.core.with
import org.http4k.datastar.Fragment
import org.http4k.lens.datastarFragments
import org.http4k.routing.routes
import org.http4k.routing.sse
import org.http4k.routing.sse.bind
import org.http4k.server.Helidon
import org.http4k.server.PolyHandler
import org.http4k.server.asServer
import org.http4k.sse.SseResponse
import org.http4k.sse.sendMergeFragments
import org.http4k.template.DatastarFragmentRenderer
import org.http4k.template.HandlebarsTemplates
import org.http4k.template.ViewModel
import org.http4k.template.viewModel
import org.http4k.routing.bind as bindHttp

// a standard view model using the Handlebars template engine for both fragments and pages
data class MyFragmentModel(val message: String) : ViewModel
data object MyPageModel : ViewModel

// wrap the renderer in the DatastarFragmentRenderer to convert each rendered template into a Fragment
val templateRenderer = HandlebarsTemplates().CachingClasspath()
val fragmentRenderer = DatastarFragmentRenderer(templateRenderer)
val pageLens = Body.viewModel(templateRenderer, TEXT_HTML).toLens()

private fun sseApp() = sse(
    // send a single fragment as an SSE datastar event
    "sse/rawFragment" bind { req: Request ->
        SseResponse {
            it.sendMergeFragments(Fragment.of("<h1>hello</h1>")).close()
        }
    },
    // update each single fragment as an SSE datastar event
    "sse/usingTemplates" bind { req: Request ->
        SseResponse {
            // we can simulate a stream of data here
            while (true) {
                it.sendMergeFragments(fragmentRenderer(MyFragmentModel("hello")))
            }
        }
    }
)

// this handles HTTP protocol routes
fun httpApp() = routes(
    // send a single fragment in the response normally
    "http/rawFragment" bindHttp { req: Request ->
        Response(OK).datastarFragments(Fragment.of("<h1>hello</h1>"))
    },
    // send a single fragment in the response using the renderer
    "http/usingTemplates" bindHttp { req: Request ->
        Response(OK).datastarFragments(fragmentRenderer(MyFragmentModel("hello")))
    },
    // render our page template and send it in the response
    "/" bindHttp { req: Request ->
        Response(OK).with(pageLens of MyPageModel)
    }
)

fun main() {
    PolyHandler(http = httpApp(), sse = sseApp()).asServer(Helidon(8000)).start()
}
