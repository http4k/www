package content.ecosystem.http4k.reference.gcp

import com.google.api.client.http.GenericUrl
import com.google.api.client.http.HttpResponse
import org.http4k.client.OkHttp
import org.http4k.core.then
import org.http4k.filter.DebuggingFilters
import org.http4k.gcp.GcpSdkHttpTransport

fun main() {
    val http4kClient = DebuggingFilters.PrintRequestAndResponse().then(OkHttp())

    // you can plug this HttpTRansport into any GCP SDK client
    val client = GcpSdkHttpTransport(http4kClient)

    val response: HttpResponse = client.createRequestFactory().buildGetRequest(GenericUrl("https://example.com"))
        .execute()

    println(response)
}
