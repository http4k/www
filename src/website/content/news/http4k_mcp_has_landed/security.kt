package content.news.http4k_mcp_has_landed

import org.http4k.core.Credentials
import org.http4k.mcp.model.McpEntity
import org.http4k.mcp.protocol.ServerMetaData
import org.http4k.mcp.protocol.Version
import org.http4k.routing.mcpHttpStreaming
import org.http4k.security.BasicAuthSecurity
import org.http4k.security.then
import org.http4k.server.Helidon
import org.http4k.server.asServer

fun main() {
    val secureMcp = BasicAuthSecurity("realm", Credentials("foo", "bar")).then(
        mcpHttpStreaming(
            ServerMetaData(McpEntity.of("http4k mcp server"), Version.of("0.1.0"))
        )
    )

    secureMcp.asServer(Helidon(3001)).start()
}
