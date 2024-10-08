package content.ecosystem.connect.concepts.pattern.post

import content.ecosystem.connect.concepts.pattern.Commit
import content.ecosystem.connect.concepts.pattern.UserDetails
import dev.forkhandles.result4k.Result4k
import org.http4k.client.OkHttp
import org.http4k.cloudnative.RemoteRequestFailed

val gitHub: GitHubApi = GitHubApi.Http(OkHttp())

val user: Result4k<UserDetails, RemoteRequestFailed> = gitHub(GetUser("octocat"))
val commit: Result4k<Commit, RemoteRequestFailed> = gitHub(GetRepoLatestCommit("http4k", "http4k-connect"))
