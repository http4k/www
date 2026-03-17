package content.ecosystem.connect.concepts.clients

import dev.forkhandles.result4k.Result
import org.http4k.connect.RemoteFailure
import org.http4k.core.HttpHandler
import org.http4k.core.Response
import org.http4k.core.Status.Companion.OK

// constructing and using the clients
val httpHandler: HttpHandler = { Response(OK) }
//val example = Example.Http(httpHandler)

//val echoed: Result<Echoed, RemoteFailure> = example.echo("hello world")
// or...
//val alsoEchoed: Result<Echoed, RemoteFailure> = example(Echo("hello world"))
