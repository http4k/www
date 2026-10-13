import io.typeflows.github.workflow.Cron
import io.typeflows.github.workflow.Job
import io.typeflows.github.workflow.RunsOn
import io.typeflows.github.workflow.Workflow
import io.typeflows.github.workflow.step.RunCommand
import io.typeflows.github.workflow.step.UseAction
import io.typeflows.github.workflow.step.marketplace.Checkout
import io.typeflows.github.workflow.step.marketplace.SetupGradle
import io.typeflows.github.workflow.trigger.Branches
import io.typeflows.github.workflow.trigger.Push
import io.typeflows.github.workflow.trigger.RepositoryDispatch
import io.typeflows.github.workflow.trigger.Schedule
import io.typeflows.github.workflow.trigger.WorkflowDispatch
import io.typeflows.util.Builder
import Actions.ACTIONS_GH_PAGES
import Actions.ACTIONS_HUGO
import Actions.SetupJavaAction
import org.http4k.typeflows.GithubActionConstants.CHECKOUT
import org.http4k.typeflows.GithubActionConstants.SETUP_GRADLE
import org.http4k.typeflows.GithubActionConstants.http4kMavenCredentials

class BuildWorkflow : Builder<Workflow> {
    override fun build() = Workflow("build") {
        displayName = "Build and Deploy"
        on += Push {
            branches = Branches.Only("master")
        }
        on += RepositoryDispatch("release")
        on += WorkflowDispatch()
        on += Schedule {
            cron += Cron.of("0 0,6,12,18 * * *")
        }

        jobs += Job("release-site", RunsOn.of("ubuntu-latest")) {
            steps += Checkout(CHECKOUT) {
                fetchDepth = 0
            }
            steps += SetupJavaAction
            steps += SetupGradle(SETUP_GRADLE) {
                gradleVersion = "9.0.0"
            }
            steps += RunCommand("./gradlew check") {
                name = "Build"
                timeoutMinutes = 25
                env.http4kMavenCredentials()
            }
            steps += UseAction(ACTIONS_HUGO) {
                name = "Setup Hugo"
                with += mapOf(
                    "hugo-version" to "0.163.0",
                    "extended" to "true"
                )
            }
            steps += RunCommand("hugo --minify -s src/website") {
                name = "Build Site"
            }
            steps += UseAction(ACTIONS_GH_PAGES) {
                name = "Deploy"
                with += mapOf(
                    "personal_token" to $$"${{ secrets.TOOLBOX_REPO_TOKEN }}",
                    "external_repository" to "http4k/http4k.github.io",
                    "publish_branch" to "master",
                    "publish_dir" to "build/www",
                    "cname" to "www.http4k.org",
                    "force_orphan" to "true"
                )
            }
        }
    }
}
