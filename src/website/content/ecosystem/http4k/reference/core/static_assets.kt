package content.ecosystem.http4k.reference.core

import org.http4k.routing.ResourceLoader.Companion.Classpath
import org.http4k.routing.ResourceLoader.Companion.Directory
import org.http4k.routing.bind
import org.http4k.routing.routes
import org.http4k.routing.static

val staticRoutes = routes(
    "/static" bind static(Classpath("/org/http4k/some/package/name")),
    "/hotreload" bind static(Directory("path/to/static/dir/goes/here"))
)
