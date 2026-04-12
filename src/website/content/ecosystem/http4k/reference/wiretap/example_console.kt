package content.ecosystem.http4k.reference.wiretap

import org.http4k.core.Method.GET
import org.http4k.core.Response
import org.http4k.core.Status.Companion.OK
import org.http4k.routing.bind
import org.http4k.routing.routes
import org.http4k.server.Jetty
import org.http4k.server.asServer
import org.http4k.server.uri
import org.http4k.wiretap.LocalTarget
import org.http4k.wiretap.RemoteTarget
import org.http4k.wiretap.Wiretap

// Local: app runs in-process, Wiretap instruments it directly
val local = Wiretap(LocalTarget {
    routes("/api" bind GET to { Response(OK) })
}).asServer(Jetty(9000)).start()

// Remote: app already running on a server, Wiretap proxies to it
val remoteApp = routes("/api" bind GET to { Response(OK) })
    .asServer(Jetty(0)).start()

val remote = Wiretap(RemoteTarget(remoteApp.uri()))
    .asServer(Jetty(9001)).start()
// Console at http://localhost:9001/_wiretap/
