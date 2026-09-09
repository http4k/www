package content.ecosystem.connect.reference.amazon.cognitoidentity

import org.http4k.chaos.start
import org.http4k.connect.amazon.cognitoidentity.FakeCognitoIdentity

val cognitoIdentity = FakeCognitoIdentity().start()
