import io.typeflows.github.workflow.Cron
import io.typeflows.github.workflow.Job
import io.typeflows.github.workflow.RunsOn
import io.typeflows.github.workflow.Workflow
import io.typeflows.github.workflow.step.RunCommand
import io.typeflows.github.workflow.step.UseAction
import io.typeflows.github.workflow.step.marketplace.Checkout
import io.typeflows.github.workflow.step.marketplace.JavaDistribution.Temurin
import io.typeflows.github.workflow.step.marketplace.JavaVersion.V21
import io.typeflows.github.workflow.step.marketplace.SetupGradle
import io.typeflows.github.workflow.step.marketplace.SetupJava
import io.typeflows.github.workflow.trigger.Branches
import io.typeflows.github.workflow.trigger.Push
import io.typeflows.github.workflow.trigger.RepositoryDispatch
import io.typeflows.github.workflow.trigger.Schedule
import io.typeflows.util.Builder

class BuildWorkflow : Builder<Workflow> {
    override fun build() = Workflow("build") {
        displayName = "Build and Deploy"
        on += Push {
            branches = Branches.Only("master")
        }
        on += RepositoryDispatch("release")
        on += Schedule {
            cron += Cron.of("0 0,6,12,18 * * *")
        }

        jobs += Job("release-site", RunsOn.of("ubuntu-latest")) {
            steps += Checkout {
                fetchDepth = 0
            }
            steps += SetupJava(Temurin, V21)
            steps += SetupGradle {
                gradleVersion = "9.0.0"
            }
            steps += RunCommand("./gradlew check") {
                name = "Build"
                timeoutMinutes = 25
            }
            steps += UseAction("peaceiris/actions-hugo@v3.0.0") {
                name = "Setup Hugo"
                with += mapOf(
                    "hugo-version" to "0.148.1",
                    "extended" to "true"
                )
            }
            steps += RunCommand("hugo --minify -s src/website") {
                name = "Build Site"
            }
            steps += UseAction("peaceiris/actions-gh-pages@v4.0.0") {
                name = "Deploy"
                with += mapOf(
                    "personal_token" to $$"${{ secrets.TOOLBOX_REPO_TOKEN }}",
                    "external_repository" to "http4k/http4k.github.io",
                    "publish_branch" to "master",
                    "publish_dir" to "build/www",
                    "cname" to "www.http4k.org"
                )
            }
        }
    }
}
