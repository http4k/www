package content.ecosystem.connect.reference.amazon.dynamodb

import org.http4k.connect.amazon.dynamodb.model.Item
import java.time.Instant

val item = Item(
    attrS of "hello",
    attrN of null,
    attrM of Item(attrI of Instant.now())
)
