package content.tutorial.build_a_simple_mcp_app

import org.http4k.ai.mcp.PromptResponse
import org.http4k.ai.mcp.model.Prompt
import org.http4k.ai.mcp.server.capability.PromptCapability
import org.http4k.ai.model.Role
import org.http4k.lens.string
import org.http4k.routing.bind

fun AnalyseRepoHealth(): PromptCapability {
    val repo = Prompt.Arg.string().required("repo", "The GitHub repo in owner/name format")

    return Prompt(
        "analyse_repo_health",
        "Instructions for analysing a GitHub repo's health metrics. Call get_health_selection first to get the focus and metrics values.",
        repo
    ) bind { req ->
        val repoName = repo(req)
        PromptResponse(
            Role.User, """
You are a GitHub repository health analyst. Analyse **$repoName** using the focus mode and metrics from the most recent get_health_selection tool response to produce a health report.

## Focus Modes

Weight your analysis based on the focus:
- **contributor**: Prioritise activity recency (days since commit), issue volume, PR count, and community signals (stars, forks). A healthy project for contributors has recent commits, manageable open issues, and active PRs.
- **dependency**: Prioritise release recency (days since release), issue count (indicates bugs), and stability signals (stars as social proof). A healthy dependency has recent releases, few open issues, and broad adoption.
- **benchmarking**: Weight all metrics equally for a balanced overview. No single metric is prioritised.

## Scoring Instructions

For each metric, assign:
1. A traffic-light rating: GREEN, AMBER, RED, or SKULL (for truly alarming values)
2. A score out of 10
3. A one-line verdict explaining the rating

Use these thresholds as guidance:

| Metric              | GREEN        | AMBER         | RED           | SKULL           |
|---------------------|--------------|---------------|---------------|-----------------|
| stars               | > 1000       | 100 - 1000    | < 100         | < 10            |
| forks               | > 200        | 50 - 200      | < 50          | < 5             |
| open_issues         | < 50         | 50 - 200      | > 200         | > 1000          |
| open_prs            | < 10         | 10 - 30       | > 30          | > 100           |
| days_since_commit   | < 7          | 7 - 30        | > 30          | > 365           |
| days_since_release  | < 30         | 30 - 90       | > 90          | > 365           |
| repo_age_days       | > 365        | 90 - 365      | < 90          | < 14            |
| total_releases      | > 50         | 10 - 50       | < 10          | 0               |

**Note:** When scoring `total_releases`, consider it relative to `repo_age_days`. A young repo with few releases is fine; an old repo with few releases is a red flag.

## Output Format

Produce:
1. A table of metrics with traffic light, score/10, and one-line verdict
2. An overall score out of 10 (weighted by focus mode)
3. A summary paragraph with your overall assessment
""".trimIndent()
        )
    }
}
