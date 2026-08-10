package content.ecosystem.connect.reference.amazon.iotdataplane

import dev.forkhandles.result4k.Result
import org.http4k.aws.AwsCredentials
import org.http4k.client.JavaHttpClient
import org.http4k.connect.RemoteFailure
import org.http4k.connect.amazon.core.model.Region
import org.http4k.connect.amazon.iotdataplane.FakeIotDataPlane
import org.http4k.connect.amazon.iotdataplane.Http
import org.http4k.connect.amazon.iotdataplane.IotDataPlane
import org.http4k.connect.amazon.iotdataplane.getThingShadow
import org.http4k.connect.amazon.iotdataplane.model.PayloadFormatIndicator.UTF8_DATA
import org.http4k.connect.amazon.iotdataplane.model.ThingName
import org.http4k.connect.amazon.iotdataplane.model.TopicName
import org.http4k.connect.amazon.iotdataplane.publish
import org.http4k.connect.amazon.iotdataplane.updateThingShadow
import org.http4k.core.HttpHandler
import org.http4k.core.Uri
import org.http4k.filter.debug
import java.io.InputStream

const val USE_REAL_CLIENT = false

fun main() {
    val region = Region.of("us-east-1")
    val topic = TopicName.of("http4k/example/topic")
    val thing = ThingName.of("http4k-example-thing")

    // unlike other AWS services, the IoT data endpoint is account-specific
    val endpoint = Uri.of("https://000000000-ats.iot.us-east-1.amazonaws.com")

    // we can connect to the real service or the fake (drop in replacement)
    val http: HttpHandler = if (USE_REAL_CLIENT) JavaHttpClient() else FakeIotDataPlane()

    // create a client
    val client = IotDataPlane.Http(endpoint, region, { AwsCredentials("accessKeyId", "secretKey") }, http.debug())

    // all operations return a Result monad of the API type
    val published: Result<Unit, RemoteFailure> = client.publish(topic, """{"message":"hello"}""".toByteArray())

    // ... and the MQTT5 options are all available
    client.publish(
        topic = topic,
        payload = """{"message":"hello again"}""".toByteArray(),
        qos = 1,
        contentType = "application/json",
        payloadFormatIndicator = UTF8_DATA,
        userProperties = listOf("source" to "http4k")
    )

    // shadow documents are opaque JSON, so are sent as bytes and returned as a stream
    client.updateThingShadow(thing, """{"state":{"reported":{"on":true}}}""".toByteArray())

    val shadow: Result<InputStream, RemoteFailure> = client.getThingShadow(thing)
}
