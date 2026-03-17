package content.howto.`pimp-your-clients-with-ksp`

import dev.forkhandles.result4k.Result
import org.http4k.connect.Http4kConnectApiClient
import org.http4k.connect.RemoteFailure
import org.http4k.core.HttpHandler
import org.http4k.core.Uri
import org.http4k.core.then
import org.http4k.filter.ClientFilters.SetBaseUriFrom

@Http4kConnectApiClient
class API(rawHttp: HttpHandler) {
    private val transport = SetBaseUriFrom(Uri.of("https://api.com"))
        .then(rawHttp)

    operator fun <R> invoke(action: APIAction<R>): Result<R, RemoteFailure> =
        action.toResult(transport(action.toRequest()))
}
