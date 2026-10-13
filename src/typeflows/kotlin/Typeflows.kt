import io.typeflows.github.DotGitHub
import io.typeflows.github.TypeflowsGitHubRepo
import io.typeflows.github.visualisation.WorkflowVisualisations
import io.typeflows.github.workflow.Cron
import io.typeflows.github.workflow.Workflow
import io.typeflows.github.workflow.step.RunCommand
import io.typeflows.util.Builder
import org.http4k.typeflows.GithubActionConstants.JAVA_VERSION
import org.http4k.typeflows.GithubActionConstants.http4kMavenCredentials
import org.http4k.typeflows.Http4kProjectStandards
import org.http4k.typeflows.UpdateGradleProjectDependencies

class Typeflows : Builder<TypeflowsGitHubRepo> {
    override fun build() = TypeflowsGitHubRepo {
        dotGithub = DotGitHub {
            workflows += BuildWorkflow()
            workflows += BuildLtsWorkflow()
            workflows += UpgradeHttp4kWorkflow()
            workflows += UpgradeHttp4kLtsWorkflow()

            workflows += UpdateGradleProjectDependencies(
                "update-dependencies",
                Cron.of("0 08 * * 1"),
                RunCommand("ls"),
                "master",
                "master",
                JAVA_VERSION,
            ).build().let { base ->
                Workflow(base.name.removeSuffix(".yml")) {
                    displayName = base.displayName
                    on = base.on.toMutableList()
                    permissions = base.permissions
                    jobs += base.jobs
                    env.http4kMavenCredentials()
                }
            }

            files += WorkflowVisualisations(workflows)
        }

        files += Http4kProjectStandards()
    }
}
