package content.ecosystem.connect.reference.openfeature

import org.http4k.client.JavaHttpClient
import org.http4k.connect.openfeature.Cached
import org.http4k.connect.openfeature.Http
import org.http4k.connect.openfeature.OpenFeature
import org.http4k.connect.openfeature.evaluateAllFlags
import org.http4k.connect.openfeature.evaluateFlag
import org.http4k.connect.openfeature.model.EvaluationContext
import org.http4k.connect.openfeature.model.FlagKey
import org.http4k.connect.openfeature.model.TargetingKey
import org.http4k.core.Uri

val client = OpenFeature.Http(Uri.of("http://localhost:43778"), JavaHttpClient())
val cached = OpenFeature.Cached(client)

val context = EvaluationContext(TargetingKey.of("alice"), "plan" to "premium")
val flagResult = cached.evaluateFlag(FlagKey.of("dark-mode"), context)
val bulkResult = cached.evaluateAllFlags(context)
