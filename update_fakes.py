#!/usr/bin/env python3
"""Update fake.kt files to use val pattern instead of wrapping function."""

import re
from pathlib import Path

CONTENT_DIR = Path("src/website/content")

for kt_path in sorted(CONTENT_DIR.rglob("fake*.kt")):
    content = kt_path.read_text()

    # Extract fake class name
    m = re.search(r'(Fake\w+)\(\)\.start\(\)', content)
    if not m:
        continue
    fake_class = m.group(1)

    # Derive variable name: FakeSQS -> sqs, FakeAnthropicAI -> anthropicAI
    var_name = fake_class.replace('Fake', '')
    var_name = var_name[0].lower() + var_name[1:]

    # Get package line
    pkg_match = re.search(r'^package .+$', content, re.MULTILINE)
    # Get import lines
    import_lines = re.findall(r'^import .+$', content, re.MULTILINE)

    new_content = pkg_match.group(0) + '\n\n'
    new_content += '\n'.join(import_lines) + '\n\n'
    new_content += f'val {var_name} = {fake_class}().start()\n'

    kt_path.write_text(new_content)
    print(f"Updated: {kt_path} -> val {var_name} = {fake_class}().start()")
