package content.ecosystem.connect.concepts.pattern.post

import content.ecosystem.connect.concepts.pattern.Commit
import content.ecosystem.connect.concepts.pattern.UserDetails
import content.ecosystem.connect.concepts.pattern.authorFrom
import content.ecosystem.connect.concepts.pattern.userNameFrom
import content.ecosystem.connect.concepts.pattern.userOrgsFrom
import dev.forkhandles.result4k.Failure
import dev.forkhandles.result4k.Result4k
import dev.forkhandles.result4k.Success
import org.http4k.cloudnative.RemoteRequestFailed
import org.http4k.core.Method.GET
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status.Companion.OK

data class GetUser(val username: String) : GitHubApiAction<UserDetails> {
    override fun toRequest() = Request(GET, "/users/$username")

    override fun toResult(response: Response) = when (response.status) {
        OK -> Success(UserDetails(userNameFrom(response), userOrgsFrom(response)))
        else -> Failure(RemoteRequestFailed(response.status, response.bodyString()))
    }
}

data class GetRepoLatestCommit(val owner: String, val repo: String) : GitHubApiAction<Commit> {
    override fun toRequest() = Request(GET, "/repos/$owner/$repo/commits").query("per_page", "1")

    override fun toResult(response: Response): Result4k<Commit, RemoteRequestFailed> = when (response.status) {
        OK -> Success(Commit(authorFrom(response)))
        else -> Failure(RemoteRequestFailed(response.status, response.bodyString()))
    }
}
