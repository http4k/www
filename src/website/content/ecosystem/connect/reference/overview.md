---
category: Reference
type: ecosystem
ecosystem: http4k Connect
title: Module Overview
description: Overview of the http4k Connect modules
weight: -1
---

A quick reference as to what is what with the http4k Connect modules.

## Cloud Services

| Vendor     | System              | In-Memory Fake | Notes                                                           |
|------------|---------------------|----------------|-----------------------------------------------------------------|
| AWS        | AppRunner           | ✅              |                                                                 |
| AWS        | CloudFront          | ✅              |                                                                 |
| AWS        | Cloudwatch Logs     | ✅              |                                                                 |
| AWS        | DynamoDb            | ✅              |                                                                 |
| AWS        | EventBridge         | ✅              |                                                                 |
| AWS        | Evidently           | ✅              |                                                                 |
| AWS        | Firehose            | ✅              |                                                                 |
| AWS        | IAM Identity Center | ✅              |                                                                 |
| AWS        | Instance Metadata   | ✅              |                                                                 |
| AWS        | KMS                 | ✅              |                                                                 |
| AWS        | Lambda              | ✅              |                                                                 |
| AWS        | S3                  | ✅              |                                                                 |
| AWS        | Secrets Manager     | ✅              |                                                                 |
| AWS        | SES                 | ✅              |                                                                 |
| AWS        | SNS                 | ✅              |                                                                 |
| AWS        | SQS                 | ✅              |                                                                 |
| AWS        | STS                 | ✅              |                                                                 |
| AWS        | Systems Manager     | ✅              |                                                                 |
| GitHub     | V3 API              | ❌              | Client Shell and WebHook Signing only                           |
| GitLab     | API                 | ❌              | Client Shell and WebHook Signing only                           |
| Google     | Analytics GA4       | ✅              |                                                                 |
| Google     | Analytics UA        | ✅              |                                                                 |
| Kafka      | Rest Proxy          | ✅              |                                                                 |
| Kafka      | Schema Registry     | ✅              |                                                                 |
| Mattermost | WebHook             | ❌              |                                                                 |
| Slack      | Slack               | ✅              | Minimal support for sending messages to channel and via webhook |

<br/>
<br/>

### AI Services

| Vendor      | System   | In-Memory Fake | Notes                                                      |
|-------------|----------|----------------|------------------------------------------------------------|
| AnthropicAI | API      | ✅              | Includes content generators                                |
| AzureAI     | API      | ✅              | Includes content generators and GitHubModels compatibility |
| LangChain4J | Adapters | ❌              | Adapters to be plugged into LangChains                     |
| LM Studio   | API      | ✅              |                                                            |
| Ollama      | API      | ✅              | Includes content generators and image generation           |
| Open AI     | API      | ✅              | Includes content generators and image generation           |

<br/>
<br/>

### Storage Implementations

| Implementation | Notes                   |
|----------------|-------------------------|
| In-Memory      | Included with all Fakes |
| File-Based     | Included with all Fakes |
| JDBC           |                         |
| Redis          |                         |
| S3             |                         |
