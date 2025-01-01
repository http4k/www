****---
category: Reference
type: ecosystem
ecosystem: http4k Connect
title: Slack
description: Feature overview of the http4k Connect Slack modules
---

```kotlin
dependencies {
    {{< http4k_bom >}}
    implementation("org.http4k:http4k-connect-slack")
}
```

The Slack connector provides the following Actions:

- Send Channel Message

### Example usage

```kotlin
val message = SlackMessage("message", channel = ChannelId.of("channel"))
val slack = Slack.Http({ SlackToken.of("my slack token") })
slack.chatPostMessage(message)

val webhooks = SlackWebhook.Http(Uri.of("https://hooks.slack.com/services/some/webhook/path"))
slackWebhooks.webhookPostMessage(message)

```

### Default Fake port: 23660

To start:

```kotlin
FakeSlack().start()
```****
