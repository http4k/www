package content.howto.implement_your_own_clients

import org.http4k.connect.Action
import org.http4k.core.Method.GET
import org.http4k.core.Request
import org.http4k.core.Response

interface MySystemAction<R> : Action<R>

data class Echo(val value: String) : MySystemAction<Echoed> {
    override fun toRequest() = Request(GET, "echo").body(value)
    override fun toResult(response: Response) = Echoed(response.bodyString())
}

data class Echoed(val value: String)
