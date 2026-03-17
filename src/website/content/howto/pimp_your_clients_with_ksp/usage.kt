package content.howto.pimp_your_clients_with_ksp

import dev.forkhandles.result4k.Result
import org.http4k.client.JavaHttpClient
import org.http4k.connect.RemoteFailure

val api = API(JavaHttpClient())

val result: Result<String, RemoteFailure> = api.reverse("hello")
