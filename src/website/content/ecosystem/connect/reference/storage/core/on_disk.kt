package content.ecosystem.connect.reference.storage.core

import org.http4k.connect.storage.Disk
import org.http4k.connect.storage.Storage
import org.http4k.format.Moshi
import java.io.File

val diskStorage = Storage.Disk<AnEntity>(File("."), Moshi)
val diskStore = run {
    diskStorage["myKey"] = AnEntity("hello")
    println(diskStorage["myKey"])
    diskStorage.removeAll("myKey")
}
