package content.ecosystem.connect.reference.storage.s3

import org.http4k.aws.AwsCredentials
import org.http4k.client.JavaHttpClient
import org.http4k.connect.amazon.core.model.Region
import org.http4k.connect.amazon.s3.Http
import org.http4k.connect.amazon.s3.S3Bucket
import org.http4k.connect.amazon.s3.model.BucketName
import org.http4k.connect.storage.S3
import org.http4k.connect.storage.Storage
import org.http4k.format.Moshi
import java.time.Clock

data class AnEntity(val name: String)

val awsCredentials = AwsCredentials("accessKey", "secret")
val bucketClient = S3Bucket.Http(BucketName.of("foobar"), Region.AP_EAST_1, { awsCredentials }, JavaHttpClient(), Clock.systemUTC())

val storage = Storage.S3<AnEntity>(bucketClient, Moshi)

val store = run {
    storage["myKey"] = AnEntity("hello")
    println(storage["myKey"])
    storage.removeAll("myKey")
}
