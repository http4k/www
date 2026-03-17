package content.ecosystem.http4k.reference.multipart

import org.http4k.contract.PreFlightExtraction
import org.http4k.contract.bindContract
import org.http4k.contract.meta
import org.http4k.core.Method.POST
import org.http4k.core.Response
import org.http4k.core.Status.Companion.OK

val binaryUploadRoute = "/api/document-upload" meta {
    preFlightExtraction = PreFlightExtraction.IgnoreBody
} bindContract POST to { req -> Response(OK) }
