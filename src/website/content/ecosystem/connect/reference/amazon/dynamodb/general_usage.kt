package content.ecosystem.connect.reference.amazon.dynamodb

import dev.forkhandles.result4k.Result
import dev.forkhandles.result4k.valueOrNull
import org.http4k.aws.AwsCredentials
import org.http4k.client.JavaHttpClient
import org.http4k.connect.RemoteFailure
import org.http4k.connect.amazon.core.model.Base64Blob
import org.http4k.connect.amazon.core.model.Region
import org.http4k.connect.amazon.dynamodb.DynamoDb
import org.http4k.connect.amazon.dynamodb.Http
import org.http4k.connect.amazon.dynamodb.deleteTable
import org.http4k.connect.amazon.dynamodb.getItem
import org.http4k.connect.amazon.dynamodb.model.Attribute
import org.http4k.connect.amazon.dynamodb.model.AttributeValue
import org.http4k.connect.amazon.dynamodb.model.AttributeValue.Companion.List
import org.http4k.connect.amazon.dynamodb.model.AttributeValue.Companion.Null
import org.http4k.connect.amazon.dynamodb.model.AttributeValue.Companion.Num
import org.http4k.connect.amazon.dynamodb.model.TableName
import org.http4k.connect.amazon.dynamodb.putItem
import org.http4k.core.HttpHandler
import org.http4k.filter.debug

// we can connect to the real service
val http: HttpHandler = JavaHttpClient()

// create a client
val dynamoClient = DynamoDb.Http(Region.of("us-east-1"), { AwsCredentials("accessKeyId", "secretKey") }, http.debug())

val tableName = TableName.of("myTable")

val attrB = Attribute.base64Blob().required("theBlob")
val attrBS = Attribute.base64Blobs().required("theBlobs")
val attrNS = Attribute.numbers().required("theNumbers")
val attrL = Attribute.list().required("theList")
val attrSS = Attribute.strings().required("theStrings")
val attrNL = Attribute.string().optional("theNullable")

// we can bind values to the attributes
val putResult = dynamoClient.putItem(
    tableName,
    Item = mapOf(
        attrS to "foobar",
        attrBool to true,
        attrB to Base64Blob.encode("foo"),
        attrBS to setOf(Base64Blob.encode("bar")),
        attrN to 123,
        attrNS to setOf(123, 12.34),
        attrL to listOf(
            List(listOf(AttributeValue.Str("foo"))),
            Num(123),
            Null()
        ),
        attrM to mapOf(attrS to "foo", attrBool to false),
        attrSS to setOf("345", "567"),
        attrNL to null
    )
)
