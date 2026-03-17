package content.ecosystem.connect.reference.storage.redis

import org.http4k.connect.storage.Redis
import org.http4k.connect.storage.Storage
import org.http4k.core.Uri
import org.http4k.format.Moshi

data class AnEntity(val name: String)

val storage = Storage.Redis<AnEntity>(Uri.of("redis://host:8000"), Moshi)

val store = run {
    storage["myKey"] = AnEntity("hello")
    println(storage["myKey"])
    storage.removeAll("myKey")
}
