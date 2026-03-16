package content.ecosystem.connect.reference.x402

import org.http4k.connect.x402.X402Facilitator
import org.http4k.connect.x402.Http
import org.http4k.core.Uri

val facilitatorClient = X402Facilitator.Http(Uri.of("https://x402.org/facilitator"))
