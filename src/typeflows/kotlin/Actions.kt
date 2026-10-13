import io.typeflows.github.workflow.step.marketplace.SetupJava
import org.http4k.typeflows.GithubActionConstants.JAVA_VERSION
import org.http4k.typeflows.GithubActionConstants.JDK
import org.http4k.typeflows.GithubActionConstants.SETUP_JAVA

object Actions {
    const val ACTIONS_HUGO = "peaceiris/actions-hugo@2752ce1d29631191ea3f27c23495fa06139a5b78" // v3.2.1
    const val ACTIONS_GH_PAGES = "peaceiris/actions-gh-pages@84c30a85c19949d7eee79c4ff27748b70285e453" // v4.1.0

    val SetupJavaAction = SetupJava(JDK, JAVA_VERSION, SETUP_JAVA)
}
