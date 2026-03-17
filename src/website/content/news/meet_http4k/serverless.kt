package content.news.meet_http4k

import org.http4k.core.HttpHandler
import org.http4k.core.Response
import org.http4k.core.Status.Companion.OK
import org.http4k.serverless.ApiGatewayV2LambdaFunction
import org.http4k.serverless.AppLoader

object TweetEcho : AppLoader {
    override fun invoke(env: Map<String, String>): HttpHandler = {
        Response(OK).body(it.bodyString().take(140))
    }
}

class MyLambdaFunction : ApiGatewayV2LambdaFunction(TweetEcho)
