package content.tutorial.build_a_simple_mcp_app

import org.http4k.template.ViewModel

enum class AnalysisFocus {
    contributor, dependency, benchmarking
}

data class RepoHealthSelection(val repo: String, val metrics: Map<String, String>, val focus: AnalysisFocus)

class HealthChecker : ViewModel
