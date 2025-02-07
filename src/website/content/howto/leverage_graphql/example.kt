package content.howto.leverage_graphql

import com.expediagroup.graphql.generator.SchemaGeneratorConfig
import com.expediagroup.graphql.generator.TopLevelObject
import com.expediagroup.graphql.generator.toSchema
import graphql.ExecutionInput.Builder
import graphql.GraphQL.newGraphQL
import org.dataloader.DataLoaderFactory.newDataLoader
import org.dataloader.DataLoaderRegistry
import org.http4k.client.JavaHttpClient
import org.http4k.client.asGraphQLHandler
import org.http4k.core.Filter
import org.http4k.core.HttpHandler
import org.http4k.core.Uri
import org.http4k.core.then
import org.http4k.core.with
import org.http4k.graphql.GraphQLRequest
import org.http4k.graphql.GraphQLResponse
import org.http4k.graphql.GraphQLWithContextHandler
import org.http4k.lens.RequestKey
import org.http4k.lens.RequestLens
import org.http4k.routing.bind
import org.http4k.routing.graphQL
import org.http4k.routing.routes
import org.http4k.server.SunHttp
import org.http4k.server.asServer
import java.util.concurrent.CompletableFuture.supplyAsync

object UserDb {
    private val userDb = mutableListOf(
        User(id = 1, name = "Jim"),
        User(id = 2, name = "Bob"),
        User(id = 3, name = "Sue"),
        User(id = 4, name = "Rita"),
        User(id = 5, name = "Charlie")
    )

    fun search(ids: List<Int>) = userDb.filter { ids.contains(it.id) }
    fun delete(ids: List<Int>) = userDb.removeIf { ids.contains(it.id) }
}

data class User(val id: Int, val name: String)

class UserQueries {
    fun search(params: Params) = UserDb.search(params.ids)
}

class UserMutations {
    fun delete(params: Params) = UserDb.delete(params.ids)
}

data class Params(val ids: List<Int>)

class UserDbHandler : GraphQLWithContextHandler<String> {
    private val graphQL = newGraphQL(
        toSchema(
            SchemaGeneratorConfig(supportedPackages = listOf("content.`how-to`.leverage_graphql")),
            listOf(TopLevelObject(UserQueries())),
            listOf(TopLevelObject(UserMutations()))
        )
    ).build()

    private val dataLoaderRegistry = DataLoaderRegistry().apply {
        register("USER_LOADER", newDataLoader { ids: List<Int> ->
            supplyAsync {
                UserQueries().search(Params(ids))
            }
        })
    }

    override fun invoke(payload: GraphQLRequest, user: String) = GraphQLResponse.from(
        graphQL.execute(
            Builder()
                .query(payload.query)
                .variables(payload.variables.orEmpty())
                .dataLoaderRegistry(dataLoaderRegistry)
                .graphQLContext(mapOf("user" to user))
        )
    )
}

fun App(): HttpHandler {
    val user = RequestKey.required<String>("user")

    return AddUserToContext(user)
        .then(routes("/graphql" bind graphQL(UserDbHandler(), user)))
}

private fun AddUserToContext(user: RequestLens<String>) = Filter { next ->
    {
        next(it.with(user of it.method.toString()))
    }
}

fun main() {
    App().asServer(SunHttp(6000)).start()

    val graphQLClient =
        JavaHttpClient().asGraphQLHandler(Uri.of("http://localhost:6000/graphql"))

    fun runAndDisplay(query: String) {
        println(graphQLClient(GraphQLRequest(query)).data)
    }

    runAndDisplay(
        """{
        search(params: { ids: [1]}) {
            id
            name
        }
}"""
    )
    runAndDisplay(
        """
        mutation {
            delete(params: { ids: [1]})
        }
"""
    )

    runAndDisplay(
        """{
        search(params: { ids: [1]}) {
            id
            name
        }
}"""
    )
}
