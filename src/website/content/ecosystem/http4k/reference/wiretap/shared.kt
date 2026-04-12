package content.ecosystem.http4k.reference.wiretap

import io.opentelemetry.api.GlobalOpenTelemetry
import io.opentelemetry.api.OpenTelemetry
import org.http4k.ai.mcp.ToolResponse
import org.http4k.ai.mcp.model.Tool
import org.http4k.ai.mcp.protocol.ServerMetaData
import org.http4k.ai.mcp.server.security.NoMcpSecurity
import org.http4k.core.HttpHandler
import org.http4k.core.PolyHandler
import org.http4k.routing.bind
import org.http4k.routing.mcp

fun MyApp(http: HttpHandler, otel: OpenTelemetry = GlobalOpenTelemetry.get()): HttpHandler = http

fun MyMcpServer(http: HttpHandler, otel: OpenTelemetry = GlobalOpenTelemetry.get()): PolyHandler =
    mcp(
        ServerMetaData("mcp app", "0.0.0"),
        NoMcpSecurity,
        Tool("foobar", "foobar") bind { ToolResponse.Ok("") }
    )
