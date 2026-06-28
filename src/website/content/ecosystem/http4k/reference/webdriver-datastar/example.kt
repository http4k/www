package content.ecosystem.http4k.reference.webdriverdatastar

import org.http4k.core.Method.GET
import org.http4k.core.Response
import org.http4k.core.Status.Companion.OK
import org.http4k.datastar.DatastarEvent.PatchElements
import org.http4k.datastar.Selector
import org.http4k.routing.bind
import org.http4k.routing.routes
import org.http4k.webdriver.datastar.DatastarWebDriver
import org.openqa.selenium.By

fun main() {
    val home = """
        <html><body>
            <button id="btn" data-on-click="@get('/clicked')">go</button>
            <div id="out">before</div>
        </body></html>
    """.trimIndent()

    val app = routes(
        "/" bind GET to { Response(OK).body(home) },
        "/clicked" bind GET to {
            // reply with an SSE patch that morphs the #out element
            val patch = PatchElements("<div id=\"out\">after</div>", selector = Selector.of("#out"))
            Response(OK).body(patch.toSseEvent().toMessage())
        }
    )

    val driver = DatastarWebDriver(app)

    driver.get("/")

    driver.findElement(By.id("btn")).click()

    println(driver.findElement(By.id("out")).text)

// prints:
//
// after
}
