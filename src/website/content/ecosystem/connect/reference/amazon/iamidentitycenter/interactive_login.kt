package content.ecosystem.connect.reference.amazon.iamidentitycenter

import org.http4k.connect.amazon.CredentialsProvider
import org.http4k.connect.amazon.core.model.AwsAccount
import org.http4k.connect.amazon.core.model.Region
import org.http4k.connect.amazon.iamidentitycenter.SSO
import org.http4k.connect.amazon.iamidentitycenter.SSOProfile
import org.http4k.connect.amazon.sts.model.RoleName
import org.http4k.core.Uri

val provider = CredentialsProvider.SSO(
    SSOProfile(
        AwsAccount.of("01234567890"),
        RoleName.of("hello"),
        Region.US_EAST_1,
        Uri.of("http://foobar"),
    )
)
