import io.typeflows.github.DotGitHub
import io.typeflows.github.TypeflowsGitHubRepo
import io.typeflows.github.visualisation.WorkflowVisualisations
import io.typeflows.util.Builder
import org.http4k.typeflows.Http4kProjectStandards

class Typeflows : Builder<TypeflowsGitHubRepo> {
    override fun build() = TypeflowsGitHubRepo {
        dotGithub = DotGitHub {
            workflows += BuildWorkflow()
            workflows += BuildLtsWorkflow()
            workflows += UpgradeHttp4kWorkflow()
            workflows += UpgradeHttp4kLtsWorkflow()

            dependabot = ProjectDependabot().build()

            files += WorkflowVisualisations(workflows)
        }

        files += Http4kProjectStandards()
    }
}
