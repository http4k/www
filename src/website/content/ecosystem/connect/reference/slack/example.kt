package content.ecosystem.connect.reference.slack

import org.http4k.connect.slack.Http
import org.http4k.connect.slack.Slack
import org.http4k.connect.slack.SlackWebhook
import org.http4k.connect.slack.model.ChannelId
import org.http4k.connect.slack.model.SlackMessage
import org.http4k.connect.slack.model.SlackToken
import org.http4k.core.Uri

val message = SlackMessage("message", channel = ChannelId.of("channel"))
val slack = Slack.Http({ SlackToken.of("my slack token") })
val postResult = slack.chatPostMessage(message)

val webhooks = SlackWebhook.Http(Uri.of("https://hooks.slack.com/services/some/webhook/path"))
val webhookResult = webhooks.webhookPostMessage(message)
