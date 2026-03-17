package content.ecosystem.connect.reference.storage.http

import org.http4k.connect.storage.InMemory
import org.http4k.connect.storage.Storage
import org.http4k.connect.storage.asHttpHandler
import org.http4k.server.SunHttp
import org.http4k.server.asServer

data class AnEntity(val name: String)

val baseStorage = Storage.InMemory<AnEntity>()
val storageServer = baseStorage.asHttpHandler().asServer(SunHttp(8000)).start()
