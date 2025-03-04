//package content.ecosystem.http4k.reference.model_context_protocol
//
//import org.http4k.mcp.server.capability.CapabilityPack
//import org.http4k.routing.bind
//
//// We can compose multiple different capabilities into a single pack
//fun AvengersDiaryPack(): CapabilityPack {
//    val blackPanther = DiaryTool("Black Panther")
//    val blackWidow = DiaryTool("Black Widow")
//    val captain = DiaryTool("Captain America")
//    val hawkeye = DiaryTool("Hawkeye")
//    val hulk = DiaryTool("Hulk")
//
//    return CapabilityPack(
//        blackPanther.tool bind blackPanther.handler,
//        blackWidow.tool bind blackWidow.handler,
//        captain.tool bind captain.handler,
//        hawkeye.tool bind hawkeye.handler,
//        hulk.tool bind hulk.handler,
//    )
//}
