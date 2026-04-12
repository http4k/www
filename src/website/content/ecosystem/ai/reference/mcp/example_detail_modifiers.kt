import io.opentelemetry.api.GlobalOpenTelemetry
import org.http4k.filter.CallToolDetailSpanModifiers
import org.http4k.filter.CompletionDetailSpanModifiers
import org.http4k.filter.GetPromptDetailSpanModifiers
import org.http4k.filter.McpFilters
import org.http4k.filter.OpenTelemetryTracing
import org.http4k.filter.ReadResourceDetailSpanModifiers
import org.http4k.filter.defaultMcpOtelSpanModifiers

val mcpFilter = McpFilters.OpenTelemetryTracing(
    openTelemetry = GlobalOpenTelemetry.get(),
    spanModifiers = defaultMcpOtelSpanModifiers
        + CallToolDetailSpanModifiers
        + GetPromptDetailSpanModifiers
        + CompletionDetailSpanModifiers
        + ReadResourceDetailSpanModifiers
)
