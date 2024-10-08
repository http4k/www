import content.ecosystem.connect.concepts.pattern.UserDetails
import content.ecosystem.connect.concepts.pattern.post.GitHubApi
import content.ecosystem.connect.concepts.pattern.post.gitHub
import dev.forkhandles.result4k.Result
import dev.forkhandles.result4k.flatMap
import org.http4k.cloudnative.RemoteRequestFailed

fun GitHubApi.getLatestUser(owner: String, repo: String): Result<UserDetails, RemoteRequestFailed> =
    getRepoLatestCommit(owner, repo).flatMap { getUser(it.author) }

val latestUser: Result<UserDetails, RemoteRequestFailed> = gitHub.getLatestUser("http4k", "http4k-connect")
