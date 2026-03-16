#!/usr/bin/env python3
"""List all inline kotlin code blocks that would be converted."""

import re
from pathlib import Path

CONTENT_DIR = Path("src/website/content")

KOTLIN_BLOCK_RE = re.compile(
    r'^```kotlin\s*\n(.*?)^```\s*$',
    re.MULTILINE | re.DOTALL
)

GRADLE_INDICATORS = [
    'implementation(',
    'testImplementation(',
    'dependencies {',
    'dependencies{',
    'runtimeOnly(',
    'compileOnly(',
    'api(',
    'kapt(',
    'ksp(',
    'http4k_bom',
]

def is_gradle_block(code):
    for indicator in GRADLE_INDICATORS:
        if indicator in code:
            return True
    return False

def count_code_lines(code):
    count = 0
    for line in code.strip().split('\n'):
        stripped = line.strip()
        if stripped and not stripped.startswith('//'):
            count += 1
    return count

def get_heading_context(md_content, block_start):
    text_before = md_content[:block_start]
    headings = list(re.finditer(r'^#{1,6}\s+(.+)$', text_before, re.MULTILINE))
    if headings:
        return headings[-1].group(1).strip()
    return '(no heading)'

results = []
for md_path in sorted(CONTENT_DIR.rglob('*.md')):
    content = md_path.read_text()
    blocks = list(KOTLIN_BLOCK_RE.finditer(content))
    for match in blocks:
        code = match.group(1)
        if is_gradle_block(code):
            continue
        lines = count_code_lines(code)
        heading = get_heading_context(content, match.start())
        rel_path = str(md_path.relative_to(CONTENT_DIR))
        first_line = code.strip().split('\n')[0][:60]
        results.append((lines, rel_path, heading, first_line))

# Sort by line count
results.sort(key=lambda x: x[0])

print(f"Total non-gradle kotlin blocks: {len(results)}\n")

# Group by size
small = [r for r in results if r[0] <= 2]
medium = [r for r in results if 3 <= r[0] <= 8]
large = [r for r in results if r[0] > 8]

print(f"=== SMALL (1-2 lines): {len(small)} blocks ===")
for lines, path, heading, first in small:
    print(f"  [{lines}L] {path} | {heading} | {first}")

print(f"\n=== MEDIUM (3-8 lines): {len(medium)} blocks ===")
for lines, path, heading, first in medium:
    print(f"  [{lines}L] {path} | {heading} | {first}")

print(f"\n=== LARGE (9+ lines): {len(large)} blocks ===")
for lines, path, heading, first in large:
    print(f"  [{lines}L] {path} | {heading} | {first}")
