package content.ecosystem.connect.concepts.pattern.pre

import content.ecosystem.connect.concepts.pattern.Commit
import content.ecosystem.connect.concepts.pattern.UserDetails
import content.ecosystem.connect.concepts.pattern.authorFrom
import content.ecosystem.connect.concepts.pattern.userNameFrom
import content.ecosystem.connect.concepts.pattern.userOrgsFrom
import org.http4k.client.OkHttp
import org.http4k.core.HttpHandler
import org.http4k.core.Method.GET
import org.http4k.core.Request
import org.http4k.core.Uri
import org.http4k.core.then
import org.http4k.filter.ClientFilters.SetBaseUriFrom
import org.http4k.filter.RequestFilters.SetHeader

class GitHubApi(client: HttpHandler) {
    private val http = SetBaseUriFrom(Uri.of("https://api.github.com"))
        .then(SetHeader("Accept", "application/vnd.github.v3+json"))
        .then(client)

    fun getUser(username: String): UserDetails {
        val response = http(Request(GET, "/users/$username"))
        return UserDetails(userNameFrom(response), userOrgsFrom(response))
    }

    fun getRepoLatestCommit(owner: String, repo: String) = Commit(
        authorFrom(http(Request(GET, "/repos/$owner/$repo/commits").query("per_page", "1")))
    )
}

val gitHub: GitHubApi = GitHubApi(OkHttp())

val user: UserDetails = gitHub.getUser("octocat")
