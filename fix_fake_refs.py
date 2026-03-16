#!/usr/bin/env python3
"""Re-apply fake.kt shortcode references in markdown files where fake.kt exists but markdown still has inline block."""

import re
from pathlib import Path

CONTENT_DIR = Path("src/website/content")

# Find all directories with fake*.kt files
for kt_path in sorted(CONTENT_DIR.rglob("fake*.kt")):
    md_path = kt_path.parent / "index.md"
    if not md_path.exists():
        continue

    content = md_path.read_text()
    kt_code = kt_path.read_text()

    # Extract the fake class call from the .kt file
    m = re.search(r'(Fake\w+)\(\)\.start\(\)', kt_code)
    if not m:
        continue
    fake_call = m.group(0)  # e.g., "FakeSQS().start()"

    # Check if the inline block still exists
    pattern = f'```kotlin\n{fake_call}\n```'
    if pattern in content:
        shortcode = '{{< kotlin file="' + kt_path.name + '" >}}'
        content = content.replace(pattern, shortcode, 1)
        md_path.write_text(content)
        print(f"Fixed: {md_path} -> {kt_path.name}")
    else:
        # Already converted or different pattern
        if f'file="{kt_path.name}"' in content:
            pass  # already has shortcode
        else:
            print(f"SKIP: {md_path} (pattern not found for {fake_call})")
