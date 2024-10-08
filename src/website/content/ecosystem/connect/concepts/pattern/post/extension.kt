import content.ecosystem.connect.concepts.pattern.Commit
import content.ecosystem.connect.concepts.pattern.UserDetails
import content.ecosystem.connect.concepts.pattern.post.GetRepoLatestCommit
import content.ecosystem.connect.concepts.pattern.post.GetUser
import content.ecosystem.connect.concepts.pattern.post.GitHubApi
import content.ecosystem.connect.concepts.pattern.post.gitHub
import dev.forkhandles.result4k.Result4k
import org.http4k.cloudnative.RemoteRequestFailed

fun GitHubApi.getUser(username: String) = invoke(GetUser(username))
fun GitHubApi.getRepoLatestCommit(owner: String, repo: String) = invoke(GetRepoLatestCommit(owner, repo))

val user2: Result4k<UserDetails, RemoteRequestFailed> = gitHub.getUser("octocat")
val commit2: Result4k<Commit, RemoteRequestFailed> = gitHub.getRepoLatestCommit("http4k", "http4k-connect")
