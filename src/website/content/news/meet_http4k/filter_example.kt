package content.news.meet_http4k

import org.http4k.core.Filter
import org.http4k.core.HttpHandler
import org.http4k.core.then

val setContentType = Filter { next ->
        { request -> next(request).header("Content-Type", "text/plain") }
    }
val repeatBody = Filter { next ->
        { request -> next(request.body(request.bodyString() + request.bodyString())) }
    }
val composedFilter: Filter = repeatBody.then(setContentType)
val decoratedApp: HttpHandler = composedFilter.then(app)
