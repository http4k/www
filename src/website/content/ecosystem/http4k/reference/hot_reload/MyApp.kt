package content.ecosystem.http4k.reference.hot_reload

import org.http4k.core.ContentType
import org.http4k.core.Method
import org.http4k.core.Response
import org.http4k.core.Status
import org.http4k.lens.contentType
import org.http4k.routing.bind
import org.http4k.routing.routes

fun MyApp() = routes("/" bind Method.GET to {
    // standard HTML response which will be reloaded when sources file are changed
    Response(Status.OK)
        .contentType(ContentType.TEXT_HTML)
        .body(EDIT_THIS_AND_SEE_THE_RELOAD_HAPPEN_IN_THE_BROWSER)
})

private val EDIT_THIS_AND_SEE_THE_RELOAD_HAPPEN_IN_THE_BROWSER = """
<body>
<h1>Hello, Hot-Reload World!</h1>
<h2>Edit me</h2>
</body>
"""
