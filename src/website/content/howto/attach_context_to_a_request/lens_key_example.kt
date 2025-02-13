package content.howto.attach_context_to_a_request

import org.http4k.core.Filter
import org.http4k.core.HttpHandler
import org.http4k.core.Method.GET
import org.http4k.core.NoOp
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status.Companion.OK
import org.http4k.core.then
import org.http4k.core.with
import org.http4k.lens.RequestKey
import org.http4k.lens.RequestLens

fun main() {
    data class SharedState(val message: String)

    fun AddState(key: RequestLens<SharedState>) = Filter { next ->
        {
            // "modify" the request like any other Lens
            next(it.with(key of SharedState("hello there")))
        }
    }

    fun PrintState(key: RequestLens<SharedState>): HttpHandler = { request ->
        // we can just extract the Lens state from the request like any other standard Lens
        println(key(request))
        Response(OK)
    }

    // this Lens is the key we use to set and get the type-safe state. By using this, we gain
    // typesafety and the guarantee that there will be no clash of keys.
    // RequestLens can be required or optional, as per the standard Lens mechanism.
    val key = RequestKey.required<SharedState>("sharedState")

    // The Filter modifies the bag of state.
    // The handler just prints out the state.
    val app = AddState(key)
        .then(PrintState(key))

    app(Request(GET, "/hello"))
}
