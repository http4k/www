package content.ecosystem.ai.reference.azure

import dev.forkhandles.result4k.Result
import org.http4k.ai.model.ModelName
import org.http4k.connect.RemoteFailure
import org.http4k.connect.azure.AzureAI
import org.http4k.connect.azure.Http
import org.http4k.connect.azure.model.AzureAIApiKey
import org.http4k.connect.azure.model.AzureHost
import org.http4k.connect.amazon.core.model.Region
import org.http4k.filter.debug

    // create a client
val client = AzureAI.Http(
    AzureAIApiKey.of("foobar"),
    AzureHost.of("myHost"), Region.of("us-east-1"),
)
