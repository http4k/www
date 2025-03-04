//package content.ecosystem.http4k.reference.model_context_protocol
//
//import org.http4k.filter.debug
//import org.http4k.mcp.model.McpEntity
//import org.http4k.mcp.protocol.ProtocolCapability.PromptsChanged
//import org.http4k.mcp.protocol.ProtocolCapability.ToolsChanged
//import org.http4k.mcp.protocol.ServerMetaData
//import org.http4k.mcp.protocol.Version
//import org.http4k.routing.bind
//import org.http4k.routing.mcpSse
//import org.http4k.server.Helidon
//import org.http4k.server.asServer
//
//fun main() {
//    // creates the MCP server with all of the capabilities
//    val davidDiary = DiaryTool("David")
//    val ivanDiary = DiaryTool("Ivan")
//
//    val http4kMcp = mcpSse(
//        ServerMetaData(McpEntity.of("http4k MCP Server"), Version.of("1.0.0"), PromptsChanged),
//
//        // we add the capability bindinds to the server here..
//        davidDiary.tool bind davidDiary.handler,
//        ivanDiary.tool bind ivanDiary.handler,
//        GreetingPrompt.prompt bind GreetingPrompt.handler,
//        SampleFromOurLocalLlm.selector bind SampleFromOurLocalLlm.handler,
//        LookupAllLinksFromWebResource.resource bind LookupAllLinksFromWebResource.handler,
//        ProvideCompletionOptionsForPrompt.reference bind ProvideCompletionOptionsForPrompt.handler,
//    )
//
//    http4kMcp.debug().asServer(Helidon(3001)).start()
//
//    val avengersMcp = mcpSse(
//        ServerMetaData(McpEntity.of("http4k MCP Server"), Version.of("1.0.0"), ToolsChanged),
//        AvengersDiaryPack()
//    )
//
//    avengersMcp.asServer(Helidon(3002)).start()
//}
