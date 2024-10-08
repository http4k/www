package content.ecosystem.connect.concepts.pattern.post

import dev.forkhandles.result4k.Result4k
import org.http4k.cloudnative.RemoteRequestFailed
import org.http4k.connect.Action

interface GitHubApiAction<R> : Action<Result4k<R, RemoteRequestFailed>>
