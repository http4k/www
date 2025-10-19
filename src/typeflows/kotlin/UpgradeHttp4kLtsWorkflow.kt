import io.typeflows.github.workflow.Job
import io.typeflows.github.workflow.RunsOn
import io.typeflows.github.workflow.Secrets
import io.typeflows.github.workflow.Workflow
import io.typeflows.github.workflow.step.RunCommand
import io.typeflows.github.workflow.step.SendRepositoryDispatch
import io.typeflows.github.workflow.step.marketplace.Checkout
import io.typeflows.github.workflow.trigger.RepositoryDispatch
import io.typeflows.util.Builder

class UpgradeHttp4kLtsWorkflow : Builder<Workflow> {
    override fun build() = Workflow("upgrade_http4k-lts") {
        displayName = "Upgrade http4k LTS"
        on += RepositoryDispatch("http4k-lts-release")

        jobs += Job("upgrade", RunsOn.of("ubuntu-latest")) {
            steps += Checkout {
                ref = "lts"
            }
            steps += RunCommand(
                $$"""
                git config --local user.name "github-actions"
                git config --local user.email "github-actions@github.com"
                ./upgrade_http4k.sh ${{ github.event.client_payload.version }}
                git add .
                git commit -am "Upgrade http4k"
            """.trimIndent()
            ) {
                name = "Configure git and upgrade"
            }
            steps += RunCommand("git push") {
                name = "Push changes"
                env["GITHUB_TOKEN"] =Secrets.GITHUB_TOKEN
            }
            steps += SendRepositoryDispatch(
                "lts-release",
                Secrets.GITHUB_TOKEN,
            ) {
                name = "Trigger LTS release"
            }
        }
    }
}
