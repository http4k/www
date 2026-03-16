#!/usr/bin/env python3
"""Create example.kt files for connect services with inline example usage blocks."""

import re
from pathlib import Path

CONTENT_DIR = Path("src/website/content")

KOTLIN_BLOCK_RE = re.compile(
    r'^```kotlin\s*\n(.*?)^```\s*$',
    re.MULTILINE | re.DOTALL
)

GRADLE_INDICATORS = [
    'implementation(', 'testImplementation(', 'dependencies {',
    'dependencies{', 'runtimeOnly(', 'http4k_bom',
]


def is_gradle_block(code):
    return any(ind in code for ind in GRADLE_INDICATORS)


def get_package(rel_path):
    parts = Path(rel_path).parent.parts
    clean = []
    for p in parts:
        c = re.sub(r'[^a-zA-Z0-9_]', '_', p)
        if c[0].isdigit():
            c = '_' + c
        clean.append(c)
    return 'content.' + '.'.join(clean)


def get_heading_before(content, pos):
    text_before = content[:pos]
    headings = list(re.finditer(r'^#{1,6}\s+(.+)$', text_before, re.MULTILINE))
    return headings[-1].group(1).strip() if headings else ''


# Services with example usage blocks to convert
# Format: (md_rel_path, service_package, extra_imports)
SERVICES = [
    ('ecosystem/connect/reference/amazon/apprunner/index.md',
     'org.http4k.connect.amazon.apprunner',
     ['org.http4k.connect.amazon.lambda.model.FunctionName']),

    ('ecosystem/connect/reference/amazon/cloudfront/index.md',
     'org.http4k.connect.amazon.cloudfront',
     ['java.util.UUID.random']),

    ('ecosystem/connect/reference/amazon/cloudwatch/index.md',
     'org.http4k.connect.amazon.cloudwatch',
     []),

    ('ecosystem/connect/reference/amazon/cloudwatchlogs/index.md',
     'org.http4k.connect.amazon.cloudwatchlogs',
     []),

    ('ecosystem/connect/reference/amazon/evidently/index.md',
     'org.http4k.connect.amazon.evidently',
     []),

    ('ecosystem/connect/reference/amazon/instancemetadata/index.md',
     'org.http4k.connect.amazon.instancemetadata',
     []),

    ('ecosystem/connect/reference/amazon/kms/index.md',
     'org.http4k.connect.amazon.kms',
     []),

    ('ecosystem/connect/reference/amazon/lambda/index.md',
     'org.http4k.connect.amazon.lambda',
     ['org.http4k.format.Moshi']),

    ('ecosystem/connect/reference/amazon/s3/index.md',
     'org.http4k.connect.amazon.s3',
     ['java.io.InputStream']),

    ('ecosystem/connect/reference/amazon/secretsmanager/index.md',
     'org.http4k.connect.amazon.secretsmanager',
     ['java.util.UUID']),

    ('ecosystem/connect/reference/amazon/sqs/index.md',
     'org.http4k.connect.amazon.sqs',
     []),

    ('ecosystem/connect/reference/amazon/sts/index.md',
     'org.http4k.connect.amazon.sts',
     []),

    ('ecosystem/connect/reference/amazon/systemsmanager/index.md',
     'org.http4k.connect.amazon.systemsmanager',
     []),

    ('ecosystem/connect/reference/mattermost/index.md',
     'org.http4k.connect.mattermost',
     []),

    ('ecosystem/connect/reference/amazon/eventbridge/index.md',
     'org.http4k.connect.amazon.eventbridge',
     []),

    ('ecosystem/connect/reference/amazon/firehose/index.md',
     'org.http4k.connect.amazon.firehose',
     []),
]

# AI services with example usage blocks
AI_SERVICES = [
    ('ecosystem/ai/reference/azure/index.md',
     'org.http4k.connect.azure',
     ['org.http4k.ai.llm.chat.*', 'org.http4k.ai.model.*']),

    ('ecosystem/ai/reference/lmstudio/index.md',
     'org.http4k.connect.lmstudio',
     ['org.http4k.ai.llm.chat.*', 'org.http4k.ai.model.*']),

    ('ecosystem/ai/reference/ollama/index.md',
     'org.http4k.connect.ollama',
     ['org.http4k.ai.llm.chat.*', 'org.http4k.ai.model.*']),

    ('ecosystem/ai/reference/openai/index.md',
     'org.http4k.connect.openai',
     ['org.http4k.ai.llm.chat.*', 'org.http4k.ai.model.*']),
]

COMMON_CONNECT_IMPORTS = [
    'org.http4k.client.JavaHttpClient',
    'org.http4k.connect.*',
    'org.http4k.connect.amazon.core.model.*',
    'org.http4k.core.HttpHandler',
    'org.http4k.filter.debug',
    'dev.forkhandles.result4k.*',
]


def process_service(md_rel, service_pkg, extra_imports, is_ai=False):
    md_path = CONTENT_DIR / md_rel
    if not md_path.exists():
        print(f"  SKIP: {md_path} not found")
        return

    content = md_path.read_text()
    blocks = list(KOTLIN_BLOCK_RE.finditer(content))
    package = get_package(md_rel)

    # Find "Example usage" blocks (non-gradle, with heading context)
    example_blocks = []
    for match in blocks:
        code = match.group(1)
        if is_gradle_block(code):
            continue
        heading = get_heading_before(content, match.start())
        if 'example' in heading.lower() or 'usage' in heading.lower():
            example_blocks.append((match, code, heading))

    if not example_blocks:
        print(f"  No example usage blocks found in {md_rel}")
        return

    # Check for existing example.kt
    kt_dir = md_path.parent
    existing = set(f.name for f in kt_dir.iterdir() if f.suffix == '.kt') if kt_dir.exists() else set()
    existing.update(re.findall(r'\{\{<\s*kotlin\s+file="([^"]+)"\s*>\}\}', content))

    for i, (match, code, heading) in enumerate(example_blocks):
        filename = 'example.kt' if i == 0 else f'example_{i}.kt'
        if filename in existing:
            print(f"  SKIP (exists): {filename}")
            continue

        # Build imports
        imports = []
        if is_ai:
            imports.extend([
                f'{service_pkg}.*',
            ])
        else:
            imports.extend([
                f'{service_pkg}.*',
                f'{service_pkg}.model.*',
            ])
        imports.extend(COMMON_CONNECT_IMPORTS if not is_ai else [
            'org.http4k.client.JavaHttpClient',
            'org.http4k.connect.*',
            'org.http4k.core.HttpHandler',
            'org.http4k.filter.debug',
            'dev.forkhandles.result4k.*',
        ])
        imports.extend(extra_imports)
        imports = sorted(set(imports))

        import_block = '\n'.join(f'import {imp}' for imp in imports)
        kt_content = f'package {package}\n\n{import_block}\n\n{code.rstrip()}\n'

        kt_path = kt_dir / filename
        kt_path.write_text(kt_content)
        print(f"  Created: {kt_path}")

        # Replace in markdown
        full_match = match.group(0)
        shortcode = f'{{{{< kotlin file="{filename}" >}}}}'
        content = content.replace(full_match, shortcode, 1)
        existing.add(filename)

    md_path.write_text(content)
    print(f"  Updated: {md_path}")


print("=== Connect Services ===")
for md_rel, pkg, extra in SERVICES:
    print(f"\n{md_rel}:")
    process_service(md_rel, pkg, extra)

print("\n=== AI Services ===")
for md_rel, pkg, extra in AI_SERVICES:
    print(f"\n{md_rel}:")
    process_service(md_rel, pkg, extra, is_ai=True)

print("\nDone!")
