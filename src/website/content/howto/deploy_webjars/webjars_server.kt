package content.howto.deploy_webjars

import org.http4k.routing.webJars
import org.http4k.server.SunHttp
import org.http4k.server.asServer

val server = webJars().asServer(SunHttp(8080)).start()
