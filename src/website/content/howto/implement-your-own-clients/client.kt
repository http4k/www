package content.howto.implement_your_own_clients

import org.http4k.core.HttpHandler

interface MySystem {
    operator fun <R : Any> invoke(action: MySystemAction<R>): R

    companion object
}

fun MySystem.Companion.Http(http: HttpHandler) = object : MySystem {
    override fun <R : Any> invoke(action: MySystemAction<R>) = action.toResult(http(action.toRequest()))
}
