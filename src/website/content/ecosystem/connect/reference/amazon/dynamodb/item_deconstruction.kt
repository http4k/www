package content.ecosystem.connect.reference.amazon.dynamodb

import java.time.Instant

val string: String? = attrS(item)
val boolean: Boolean = attrBool(item)
val instant: Instant = attrI(attrM(item))
