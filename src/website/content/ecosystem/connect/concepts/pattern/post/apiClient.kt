package content.ecosystem.connect.concepts.pattern.post

import dev.forkhandles.result4k.Result4k
import org.http4k.cloudnative.RemoteRequestFailed

interface GitHubApi {
    operator fun <R : Any> invoke(action: GitHubApiAction<R>): Result4k<R, RemoteRequestFailed>

    companion object
}
