package content.tutorial.build_a_simple_mcp_app

import org.http4k.template.ViewModel

data class ReleaseSelection(val repo: String, val issues: List<Int>)

class ReleasePlannerUI : ViewModel
