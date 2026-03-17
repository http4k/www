package content.ecosystem.connect.reference.amazon.dynamodb

import dev.forkhandles.result4k.Result
import org.http4k.connect.amazon.dynamodb.model.Attribute
import org.http4k.connect.amazon.dynamodb.model.Item
import org.http4k.lens.LensFailure
import java.time.Instant

val attrS = Attribute.string().optional("theNull")
val attrBool = Attribute.boolean().required("theBool")
val attrN = Attribute.int().optional("theNum")
val attrI = Attribute.instant().required("theInstant")
val attrM = Attribute.map().required("theMap")
