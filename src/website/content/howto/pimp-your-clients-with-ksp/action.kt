package content.howto.`pimp-your-clients-with-ksp`

import dev.forkhandles.result4k.Result
import dev.forkhandles.result4k.Success
import org.http4k.connect.Action
import org.http4k.connect.Http4kConnectAction
import org.http4k.connect.RemoteFailure
import org.http4k.core.Method.POST
import org.http4k.core.Request
import org.http4k.core.Response

interface APIAction<R> : Action<Result<R, RemoteFailure>>

@Http4kConnectAction
data class Reverse(val value: String) : APIAction<String> {
    override fun toRequest() = Request(POST, "/reverse").body(value)

    override fun toResult(response: Response): Result<String, RemoteFailure> =
        Success(response.bodyString())
}
