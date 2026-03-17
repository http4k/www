#!/usr/bin/env python3
"""Fix variable names in fake.kt files where acronyms got weird casing."""

import re
from pathlib import Path

CONTENT_DIR = Path("src/website/content")

# Map of bad var names to good ones
FIXES = {
    'val kMS': 'val kms',
    'val sES': 'val ses',
    'val sNS': 'val sns',
    'val sQS': 'val sqs',
    'val sTS': 'val sts',
    'val oIDC': 'val oidc',
    'val sSO': 'val sso',
}

for kt_path in sorted(CONTENT_DIR.rglob("fake*.kt")):
    content = kt_path.read_text()
    changed = False
    for old, new in FIXES.items():
        if old in content:
            content = content.replace(old, new)
            changed = True
            print(f"Fixed: {kt_path} ({old} -> {new})")
    if changed:
        kt_path.write_text(content)
