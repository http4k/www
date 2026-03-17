package content.ecosystem.connect.reference.storage.core

import org.http4k.connect.storage.InMemory
import org.http4k.connect.storage.Storage

data class AnEntity(val name: String)

val storage = Storage.InMemory<AnEntity>()
val store = run {
    storage["myKey"] = AnEntity("hello")
    println(storage["myKey"])
    storage.removeAll("myKey")
}
