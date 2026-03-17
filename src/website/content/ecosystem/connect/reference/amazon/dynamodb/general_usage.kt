package content.ecosystem.connect.reference.amazon.dynamodb

import org.http4k.aws.AwsCredentials
import org.http4k.client.JavaHttpClient
import org.http4k.connect.amazon.core.model.Region
import org.http4k.connect.amazon.dynamodb.DynamoDb
import org.http4k.connect.amazon.dynamodb.Http
import org.http4k.connect.amazon.dynamodb.model.Attribute
import org.http4k.connect.amazon.dynamodb.model.AttributeValue.Companion.Null
import org.http4k.connect.amazon.dynamodb.model.AttributeValue.Companion.Num
import org.http4k.connect.amazon.dynamodb.model.Item
import org.http4k.connect.amazon.dynamodb.model.TableName
import org.http4k.connect.amazon.dynamodb.putItem
import org.http4k.connect.model.Base64Blob
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
    Item = Item(
        attrS of "foobar",
        attrBool of true,
        attrB of Base64Blob.encode("foo"),
        attrBS of setOf(Base64Blob.encode("bar")),
        attrN of 123,
        attrNS of setOf(123.toBigDecimal(), 12.34.toBigDecimal()),
        attrL of listOf(
            Num(123),
            Null()
        ),
        attrM of Item(attrS of "foo", attrBool of false),
        attrSS of setOf("345", "567"),
        attrNL of null
    )
)
