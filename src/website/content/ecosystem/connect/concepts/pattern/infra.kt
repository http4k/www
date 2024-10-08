package content.ecosystem.connect.concepts.pattern

import org.http4k.core.Response

fun userOrgsFrom(response: Response): List<String> = listOf()
fun userNameFrom(response: Response) = "octocat"
fun authorFrom(response: Response) = "octocat"

data class UserDetails(val name: String, val orgs: List<String>)

data class Commit(val author: String)
