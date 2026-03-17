#!/usr/bin/env python3
"""Replace inline example usage blocks with shortcode references for files that have a compiled example.kt."""

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

# Directories with successfully compiled example.kt files
COMPILED = [
    'ecosystem/connect/reference/amazon/cloudfront',
    'ecosystem/connect/reference/amazon/cloudwatchlogs',
    'ecosystem/connect/reference/amazon/evidently',
    'ecosystem/connect/reference/amazon/instancemetadata',
    'ecosystem/connect/reference/amazon/s3',
    'ecosystem/connect/reference/amazon/secretsmanager',
    'ecosystem/connect/reference/amazon/systemsmanager',
]

for d in COMPILED:
    md_path = CONTENT_DIR / d / 'index.md'
    if not md_path.exists():
        continue

    content = md_path.read_text()

    # Find the "Example usage" heading and the first non-gradle kotlin block after it
    example_heading = re.search(r'^###?\s+Example\s+usage\s*$', content, re.MULTILINE | re.IGNORECASE)
    if not example_heading:
        print(f"SKIP {d}: no Example usage heading")
        continue

    # Find the first kotlin block after the heading
    search_from = example_heading.end()
    remaining = content[search_from:]
    block_match = KOTLIN_BLOCK_RE.search(remaining)
    if not block_match:
        print(f"SKIP {d}: no kotlin block after heading")
        continue

    code = block_match.group(1)
    if any(ind in code for ind in GRADLE_INDICATORS):
        print(f"SKIP {d}: first block is gradle")
        continue

    # Replace
    full_match = block_match.group(0)
    shortcode = '{{< kotlin file="example.kt" >}}'
    new_remaining = remaining.replace(full_match, shortcode, 1)
    new_content = content[:search_from] + new_remaining

    md_path.write_text(new_content)
    print(f"Updated: {md_path}")
