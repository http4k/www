package content.news.http4k_mcp_has_landed

import org.http4k.mcp.model.McpEntity
import org.http4k.mcp.protocol.ServerMetaData
import org.http4k.mcp.protocol.Version
import org.http4k.mcp.server.security.BearerAuthMcpSecurity
import org.http4k.routing.mcpHttpNonStreaming
import org.http4k.serverless.ApiGatewayV2LambdaFunction
import org.http4k.serverless.AppLoader

class McpLambdaFunction : ApiGatewayV2LambdaFunction(AppLoader {
    mcpHttpNonStreaming(
        ServerMetaData(McpEntity.of("Serverless MCP"), Version.of("1.0.0")),
        BearerAuthMcpSecurity { it == "my-oauth-token" },
        weatherTool
    )
})
