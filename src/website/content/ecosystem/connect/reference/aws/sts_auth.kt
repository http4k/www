package content.ecosystem.connect.reference.aws

import org.http4k.connect.amazon.CredentialsProvider
import org.http4k.connect.amazon.sqs.Http
import org.http4k.connect.amazon.sqs.SQS

val stsSqs = SQS.Http(credentialsProvider = CredentialsProvider.STS())
