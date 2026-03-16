#!/usr/bin/env python3
"""Create fake.kt files for all services that have inline FakeXxx().start() blocks."""

import re
from pathlib import Path

CONTENT_DIR = Path("src/website/content")

# Map from markdown path to (fake_class_name, import_path)
FAKE_BLOCKS = {
    # AI references
    'ecosystem/ai/reference/azure/index.md': ('FakeAzureAI', 'org.http4k.connect.azure.FakeAzureAI'),
    'ecosystem/ai/reference/lmstudio/index.md': ('FakeLmStudio', 'org.http4k.connect.lmstudio.FakeLmStudio'),
    'ecosystem/ai/reference/ollama/index.md': ('FakeOllama', 'org.http4k.connect.ollama.FakeOllama'),
    'ecosystem/ai/reference/openai/index.md': ('FakeOpenAI', 'org.http4k.connect.openai.FakeOpenAI'),
    # Amazon services
    'ecosystem/connect/reference/amazon/apprunner/index.md': ('FakeAppRunner', 'org.http4k.connect.amazon.apprunner.FakeAppRunner'),
    'ecosystem/connect/reference/amazon/cloudfront/index.md': ('FakeCloudFront', 'org.http4k.connect.amazon.cloudfront.FakeCloudFront'),
    'ecosystem/connect/reference/amazon/cloudwatch/index.md': ('FakeCloudWatch', 'org.http4k.connect.amazon.cloudwatch.FakeCloudWatch'),
    'ecosystem/connect/reference/amazon/cloudwatchlogs/index.md': ('FakeCloudWatchLogs', 'org.http4k.connect.amazon.cloudwatchlogs.FakeCloudWatchLogs'),
    'ecosystem/connect/reference/amazon/cognito/index.md': ('FakeCloudFront', 'org.http4k.connect.amazon.cloudfront.FakeCloudFront'),  # Note: the markdown says FakeCloudFront but this is likely a bug in the docs
    'ecosystem/connect/reference/amazon/containercredentials/index.md': ('FakeContainerCredentials', 'org.http4k.connect.amazon.containercredentials.FakeContainerCredentials'),
    'ecosystem/connect/reference/amazon/eventbridge/index.md': ('FakeEventBridge', 'org.http4k.connect.amazon.eventbridge.FakeEventBridge'),
    'ecosystem/connect/reference/amazon/evidently/index.md': ('FakeEvidently', 'org.http4k.connect.amazon.evidently.FakeEvidently'),
    'ecosystem/connect/reference/amazon/firehose/index.md': ('FakeFirehose', 'org.http4k.connect.amazon.firehose.FakeFirehose'),
    'ecosystem/connect/reference/amazon/instancemetadata/index.md': ('FakeInstanceMetadataService', 'org.http4k.connect.amazon.instancemetadata.FakeInstanceMetadataService'),
    'ecosystem/connect/reference/amazon/kms/index.md': ('FakeKMS', 'org.http4k.connect.amazon.kms.FakeKMS'),
    'ecosystem/connect/reference/amazon/lambda/index.md': ('FakeLambda', 'org.http4k.connect.amazon.lambda.FakeLambda'),
    'ecosystem/connect/reference/amazon/s3/index.md': ('FakeS3', 'org.http4k.connect.amazon.s3.FakeS3'),
    'ecosystem/connect/reference/amazon/scheduler/index.md': ('FakeScheduler', 'org.http4k.connect.amazon.scheduler.FakeScheduler'),
    'ecosystem/connect/reference/amazon/secretsmanager/index.md': ('FakeSecretsManager', 'org.http4k.connect.amazon.secretsmanager.FakeSecretsManager'),
    'ecosystem/connect/reference/amazon/ses/index.md': ('FakeSES', 'org.http4k.connect.amazon.ses.FakeSES'),
    'ecosystem/connect/reference/amazon/sns/index.md': ('FakeSNS', 'org.http4k.connect.amazon.sns.FakeSNS'),
    'ecosystem/connect/reference/amazon/sqs/index.md': ('FakeSQS', 'org.http4k.connect.amazon.sqs.FakeSQS'),
    'ecosystem/connect/reference/amazon/sts/index.md': ('FakeSTS', 'org.http4k.connect.amazon.sts.FakeSTS'),
    'ecosystem/connect/reference/amazon/systemsmanager/index.md': ('FakeSecretsManager', 'org.http4k.connect.amazon.secretsmanager.FakeSecretsManager'),  # docs say FakeSecretsManager
    # Google analytics
    'ecosystem/connect/reference/google/analytics-ga4/index.md': ('FakeGoogleAnalytics', 'org.http4k.connect.google.analytics.ga4.FakeGoogleAnalytics'),
    'ecosystem/connect/reference/google/analytics-ua/index.md': ('FakeGoogleAnalytics', 'org.http4k.connect.google.ua.FakeGoogleAnalytics'),
    # Kafka
    'ecosystem/connect/reference/kafka/rest/index.md': ('FakeKafkaRest', 'org.http4k.connect.kafka.rest.FakeKafkaRest'),
    'ecosystem/connect/reference/kafka/schemaregistry/index.md': ('FakeSchemaRegistry', 'org.http4k.connect.kafka.schemaregistry.FakeSchemaRegistry'),
    # Other
    'ecosystem/connect/reference/mattermost/index.md': ('FakeMattermost', 'org.http4k.connect.mattermost.FakeMattermost'),
}

# IAM Identity Center has TWO fake blocks
IAM_FAKES = {
    'ecosystem/connect/reference/amazon/iamidentitycenter/index.md': [
        ('FakeOIDC', 'org.http4k.connect.amazon.iamidentitycenter.FakeOIDC'),
        ('FakeSSO', 'org.http4k.connect.amazon.iamidentitycenter.FakeSSO'),
    ]
}

def get_package(rel_path):
    parts = Path(rel_path).parent.parts
    clean = []
    for p in parts:
        c = re.sub(r'[^a-zA-Z0-9_]', '_', p)
        if c[0].isdigit():
            c = '_' + c
        clean.append(c)
    return 'content.' + '.'.join(clean)


def create_fake_kt(md_rel_path, fake_class, fake_import):
    md_path = CONTENT_DIR / md_rel_path
    kt_dir = md_path.parent
    package = get_package(md_rel_path)

    # Check if fake.kt already exists
    kt_path = kt_dir / 'fake.kt'
    if kt_path.exists():
        print(f"  SKIP (exists): {kt_path}")
        return False

    kt_content = f"""package {package}

import org.http4k.chaos.start
import {fake_import}

fun `start fake`() {{
    {fake_class}().start()
}}
"""
    kt_path.write_text(kt_content)
    print(f"  Created: {kt_path}")

    # Update markdown
    content = md_path.read_text()
    pattern = f'```kotlin\n{fake_class}().start()\n```'
    if pattern in content:
        content = content.replace(pattern, '{{< kotlin file="fake.kt" >}}')
        md_path.write_text(content)
        print(f"  Updated: {md_path}")
        return True
    else:
        # Try with extra whitespace
        pattern_re = re.compile(
            rf'^```kotlin\s*\n\s*{re.escape(fake_class)}\(\)\.start\(\)\s*\n```\s*$',
            re.MULTILINE
        )
        if pattern_re.search(content):
            content = pattern_re.sub('{{< kotlin file="fake.kt" >}}', content)
            md_path.write_text(content)
            print(f"  Updated: {md_path}")
            return True
        else:
            print(f"  WARNING: Could not find pattern in {md_path}")
            return False


# Process single-fake files
for md_rel, (fake_cls, fake_imp) in FAKE_BLOCKS.items():
    print(f"\n{md_rel}:")
    create_fake_kt(md_rel, fake_cls, fake_imp)

# Process IAM Identity Center (two fakes)
for md_rel, fakes in IAM_FAKES.items():
    print(f"\n{md_rel}:")
    md_path = CONTENT_DIR / md_rel
    content = md_path.read_text()
    package = get_package(md_rel)

    for fake_cls, fake_imp in fakes:
        filename = fake_cls.lower().replace('fake', 'fake_') + '.kt'
        # e.g., fake_oidc.kt, fake_sso.kt
        filename = f"fake_{fake_cls.replace('Fake', '').lower()}.kt"
        kt_path = md_path.parent / filename
        if kt_path.exists():
            print(f"  SKIP (exists): {kt_path}")
            continue

        kt_content = f"""package {package}

import org.http4k.chaos.start
import {fake_imp}

fun `start {fake_cls.replace('Fake', '').lower()} fake`() {{
    {fake_cls}().start()
}}
"""
        kt_path.write_text(kt_content)
        print(f"  Created: {kt_path}")

        pattern = f'```kotlin\n{fake_cls}().start()\n```'
        if pattern in content:
            content = content.replace(pattern, f'{{{{< kotlin file="{filename}" >}}}}', 1)

    md_path.write_text(content)
    print(f"  Updated: {md_path}")

print("\nDone!")
