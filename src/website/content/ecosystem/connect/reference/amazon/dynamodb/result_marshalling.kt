package content.ecosystem.connect.reference.amazon.dynamodb

import dev.forkhandles.result4k.Result
import org.http4k.lens.LensFailure

val booleanResult: Result<Boolean, LensFailure> = attrBool.asResult()(item)
