package content.ecosystem.ai.reference.azure

import org.http4k.ai.model.ApiKey
import org.http4k.connect.azure.AzureAI
import org.http4k.connect.azure.AzureHost
import org.http4k.connect.azure.Http
import org.http4k.connect.azure.Region

// create a client
val client = AzureAI.Http(ApiKey.of("foobar"), AzureHost.of("myHost"), Region.of("us-east-1"))
