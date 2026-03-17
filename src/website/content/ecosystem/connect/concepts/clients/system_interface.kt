package content.ecosystem.connect.concepts.clients

import dev.forkhandles.result4k.Result
import org.http4k.connect.Action
import org.http4k.connect.RemoteFailure

// Generic system interface
interface Example {
   operator fun <R : Any> invoke(request: ExampleAction<R>): Result<R, RemoteFailure>

   companion object
}

// System-specific action
interface ExampleAction<R> : Action<Result<R, RemoteFailure>>

// Action and response classes
data class Echo(val value: String) : ExampleAction<Echoed> {
    override fun toRequest() = org.http4k.core.Request(org.http4k.core.Method.GET, "/echo").body(value)
    override fun toResult(response: org.http4k.core.Response) = dev.forkhandles.result4k.Success(Echoed(response.bodyString()))
}
data class Echoed(val value: String)

// Traditional function helpers
fun Example.echo(value: String): Result<Echoed, RemoteFailure> = this(Echo(value))
