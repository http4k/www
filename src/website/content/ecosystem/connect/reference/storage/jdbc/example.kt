package content.ecosystem.connect.reference.storage.jdbc

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.http4k.connect.storage.Jdbc
import org.http4k.connect.storage.Storage
import org.http4k.format.Moshi

data class AnEntity(val name: String)

val ds = HikariDataSource(
    HikariConfig().apply {
        driverClassName = "org.h2.Driver"
        jdbcUrl = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1"
    })

val storage = Storage.Jdbc(ds, "mytable", Moshi)

val store = run {
    storage["myKey"] = AnEntity("hello")
    println(storage["myKey"])
    storage.removeAll("myKey")
}
