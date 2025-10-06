import io.typeflows.github.workflow.Job
import io.typeflows.github.workflow.RunsOn
import io.typeflows.github.workflow.Workflow
import io.typeflows.github.workflow.step.RunCommand
import io.typeflows.github.workflow.step.UseAction
import io.typeflows.github.workflow.step.marketplace.Checkout
import io.typeflows.github.workflow.trigger.RepositoryDispatch
import io.typeflows.util.Builder

class UpgradeHttp4kWorkflow : Builder<Workflow> {
    override fun build() = Workflow("upgrade_http4k") {
        displayName = "Upgrade http4k"
        on += RepositoryDispatch("http4k-release")

        jobs += Job("upgrade", RunsOn.of("ubuntu-latest")) {
            steps += Checkout()
            steps += RunCommand(
                $$"""
                git config --local user.name "github-actions"
                git config --local user.email "github-actions@github.com"
                ./upgrade_http4k.sh ${{ github.event.client_payload.version }}
                git add .
                git commit -am "Upgrade http4k"
            """.trimIndent(), "Configure git and upgrade"
            )
            steps += RunCommand("git push", "Push changes") {
                env["GITHUB_TOKEN"] = $$"${{ secrets.GITHUB_TOKEN }}"
            }
            steps += UseAction("peter-evans/repository-dispatch@v3.0.0", "Trigger release") {
                with += mapOf("event-type" to "release")
            }
        }
    }
}
