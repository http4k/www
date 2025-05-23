package content.news.documenting_apis_with_openapi

import org.http4k.contract.contract
import org.http4k.contract.openapi.ApiInfo
import org.http4k.contract.openapi.v3.OpenApi3
import org.http4k.core.Credentials
import org.http4k.core.HttpHandler
import org.http4k.core.Method.GET
import org.http4k.core.Request
import org.http4k.format.Jackson

val finalHttp: HttpHandler = contract {
    renderer = OpenApi3(ApiInfo("my friendly api", "v1.0", "API description"), Jackson)
    descriptionPath = "/reference/api/swagger.json"
    security = org.http4k.security.BasicAuthSecurity("realm", Credentials("user", "password"))

    routes += Greetings()
    routes += Family()
}

fun main() {
    println(finalHttp(Request(GET, "/reference/api/swagger.json")))
}
