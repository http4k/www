#!/usr/bin/env python3
"""
Convert inline ```kotlin code blocks in markdown files to external .kt files
referenced via {{< kotlin file="..." >}} shortcodes.

Skips gradle dependency blocks and tiny snippets (< 3 lines).
Adds wildcard imports based on directory context.
"""

import re
import sys
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

# Common http4k imports to add based on code analysis
CORE_IMPORTS = [
    'org.http4k.core.*',
    'org.http4k.core.Method.*',
    'org.http4k.core.Status.Companion.*',
]

ROUTING_IMPORTS = [
    'org.http4k.routing.*',
]

SERVER_IMPORTS = [
    'org.http4k.server.*',
]

CLIENT_IMPORTS = [
    'org.http4k.client.*',
]

FILTER_IMPORTS = [
    'org.http4k.filter.*',
]

LENS_IMPORTS = [
    'org.http4k.lens.*',
]

WEBSOCKET_IMPORTS = [
    'org.http4k.websocket.*',
]

SSE_IMPORTS = [
    'org.http4k.sse.*',
]

SERVERLESS_IMPORTS = [
    'org.http4k.serverless.*',
]

FORMAT_IMPORTS = [
    'org.http4k.format.*',
]

CONNECT_COMMON_IMPORTS = [
    'org.http4k.connect.*',
    'org.http4k.connect.amazon.core.model.*',
]

AI_COMMON_IMPORTS = [
    'org.http4k.ai.llm.*',
    'org.http4k.ai.llm.chat.*',
    'org.http4k.ai.llm.model.*',
    'org.http4k.ai.llm.tools.*',
    'org.http4k.ai.llm.msg.*',
    'org.http4k.ai.llm.images.*',
    'org.http4k.ai.model.*',
]

RESULT4K_IMPORTS = [
    'dev.forkhandles.result4k.*',
]

TESTING_IMPORTS = [
    'org.junit.jupiter.api.Test',
]

# Map directory patterns to service-specific imports
CONNECT_SERVICE_MAP = {
    'amazon/apprunner': 'org.http4k.connect.amazon.apprunner',
    'amazon/cloudfront': 'org.http4k.connect.amazon.cloudfront',
    'amazon/cloudwatch': 'org.http4k.connect.amazon.cloudwatch',
    'amazon/cloudwatchlogs': 'org.http4k.connect.amazon.cloudwatchlogs',
    'amazon/cognito': 'org.http4k.connect.amazon.cognito',
    'amazon/containercredentials': 'org.http4k.connect.amazon.containercredentials',
    'amazon/dynamodb': 'org.http4k.connect.amazon.dynamodb',
    'amazon/eventbridge': 'org.http4k.connect.amazon.eventbridge',
    'amazon/evidently': 'org.http4k.connect.amazon.evidently',
    'amazon/firehose': 'org.http4k.connect.amazon.firehose',
    'amazon/iamidentitycenter': 'org.http4k.connect.amazon.iamidentitycenter',
    'amazon/instancemetadata': 'org.http4k.connect.amazon.instancemetadata',
    'amazon/kms': 'org.http4k.connect.amazon.kms',
    'amazon/lambda': 'org.http4k.connect.amazon.lambda',
    'amazon/s3': 'org.http4k.connect.amazon.s3',
    'amazon/scheduler': 'org.http4k.connect.amazon.scheduler',
    'amazon/secretsmanager': 'org.http4k.connect.amazon.secretsmanager',
    'amazon/ses': 'org.http4k.connect.amazon.ses',
    'amazon/sns': 'org.http4k.connect.amazon.sns',
    'amazon/sqs': 'org.http4k.connect.amazon.sqs',
    'amazon/sts': 'org.http4k.connect.amazon.sts',
    'amazon/systemsmanager': 'org.http4k.connect.amazon.systemsmanager',
    'kafka/rest': 'org.http4k.connect.kafka.rest',
    'kafka/schemaregistry': 'org.http4k.connect.kafka.schemaregistry',
    'mattermost': 'org.http4k.connect.mattermost',
    'slack': 'org.http4k.connect.slack',
    'github': 'org.http4k.connect.github',
    'gitlab': 'org.http4k.connect.gitlab',
    'storage/core': 'org.http4k.connect.storage',
    'storage/http': 'org.http4k.connect.storage',
    'storage/jdbc': 'org.http4k.connect.storage',
    'storage/redis': 'org.http4k.connect.storage',
    'storage/s3': 'org.http4k.connect.storage',
    'google/analytics-ga4': 'org.http4k.connect.google.analytics',
    'google/analytics-ua': 'org.http4k.connect.google.analytics',
    'x402': 'org.http4k.connect.x402',
}

AI_SERVICE_MAP = {
    'anthropic': 'org.http4k.connect.anthropic',
    'openai': 'org.http4k.connect.openai',
    'azure': 'org.http4k.connect.azure',
    'ollama': 'org.http4k.connect.ollama',
    'lmstudio': 'org.http4k.connect.lmstudio',
    'gemini': 'org.http4k.connect.google.gemini',
    'github': 'org.http4k.connect.github',
    'langchain': 'org.http4k.connect.langchain',
}

# Code keywords that suggest specific import groups are needed
KEYWORD_IMPORT_MAP = {
    'routes': ROUTING_IMPORTS,
    'bind': ROUTING_IMPORTS,
    'static': ROUTING_IMPORTS,
    'singlePageApp': ROUTING_IMPORTS,
    'webJars': ROUTING_IMPORTS,
    'poly': ROUTING_IMPORTS,
    'websockets': ROUTING_IMPORTS,
    'sse ': ROUTING_IMPORTS,
    'asServer': SERVER_IMPORTS,
    'Jetty': SERVER_IMPORTS,
    'Undertow': SERVER_IMPORTS,
    'SunHttp': SERVER_IMPORTS,
    'Netty': SERVER_IMPORTS,
    'ApacheClient': CLIENT_IMPORTS,
    'JavaHttpClient': CLIENT_IMPORTS,
    'OkHttp': CLIENT_IMPORTS,
    'ServerFilters': FILTER_IMPORTS,
    'ClientFilters': FILTER_IMPORTS,
    'CatchLensFailure': FILTER_IMPORTS,
    'Header.': LENS_IMPORTS,
    'Query.': LENS_IMPORTS,
    'Path.': LENS_IMPORTS,
    'Body.': LENS_IMPORTS,
    'FormField': LENS_IMPORTS,
    'WsHandler': WEBSOCKET_IMPORTS,
    'WsResponse': WEBSOCKET_IMPORTS,
    'WsMessage': WEBSOCKET_IMPORTS,
    'Websocket': WEBSOCKET_IMPORTS,
    'testWsClient': WEBSOCKET_IMPORTS,
    'SseHandler': SSE_IMPORTS,
    'SseResponse': SSE_IMPORTS,
    'SseMessage': SSE_IMPORTS,
    'AppLoader': SERVERLESS_IMPORTS,
    'FnLoader': SERVERLESS_IMPORTS,
    'ApiGateway': SERVERLESS_IMPORTS,
    'LambdaFunction': SERVERLESS_IMPORTS,
    'Jackson': FORMAT_IMPORTS,
    'Moshi': FORMAT_IMPORTS,
    'Gson': FORMAT_IMPORTS,
    'Argo': FORMAT_IMPORTS,
    'Result<': RESULT4K_IMPORTS,
    'Result4k': RESULT4K_IMPORTS,
    'valueOrNull': RESULT4K_IMPORTS,
    'LLMResult': AI_COMMON_IMPORTS,
    'Chat ': AI_COMMON_IMPORTS,
    'Chat.': AI_COMMON_IMPORTS,
    'ChatRequest': AI_COMMON_IMPORTS,
    'ChatResponse': AI_COMMON_IMPORTS,
    'StreamingChat': AI_COMMON_IMPORTS,
    'ImageGeneration': AI_COMMON_IMPORTS,
    'ImageRequest': AI_COMMON_IMPORTS,
    'Msg.': AI_COMMON_IMPORTS,
    'MsgContent': AI_COMMON_IMPORTS,
    'ModelName': AI_COMMON_IMPORTS,
    'ApiKey': AI_COMMON_IMPORTS,
    'Resource.': AI_COMMON_IMPORTS,
    '@Test': TESTING_IMPORTS,
    'PreFlightExtraction': ['org.http4k.contract.*'],
    'bindContract': ['org.http4k.contract.*'],
    'contract(': ['org.http4k.contract.*'],
    'RequestContext': ['org.http4k.lens.RequestContexts', 'org.http4k.lens.RequestContext'],
    'CloudEvent': ['org.http4k.cloudevents.*'],
    'Events': ['org.http4k.events.*'],
    'RecordingEvents': ['org.http4k.events.*'],
    'toCurl': ['org.http4k.core.toCurl'],
    'toBody': ['org.http4k.core.toBody'],
    'Attribute': ['org.http4k.connect.amazon.dynamodb.model.*'],
    'Base64Blob': ['org.http4k.connect.amazon.core.model.Base64Blob'],
    'CredentialsProvider': ['org.http4k.connect.amazon.CredentialsProvider'],
    'Environment': ['org.http4k.cloudnative.env.Environment'],
    'AwsCredentialScope': ['org.http4k.aws.AwsCredentialScope'],
    'AwsCredentials': ['org.http4k.connect.amazon.core.model.AwsCredentials'],
    'debug()': ['org.http4k.filter.debug'],
}


def is_gradle_block(code: str) -> bool:
    for indicator in GRADLE_INDICATORS:
        if indicator in code:
            return True
    return False


def count_code_lines(code: str) -> int:
    """Count non-blank, non-comment lines."""
    count = 0
    for line in code.strip().split('\n'):
        stripped = line.strip()
        if stripped and not stripped.startswith('//'):
            count += 1
    return count


def get_package_from_path(md_path: Path) -> str:
    rel = md_path.relative_to(CONTENT_DIR)
    if rel.name == 'index.md':
        parts = rel.parent.parts
    else:
        parts = list(rel.parent.parts) + [rel.stem]
    clean_parts = []
    for part in parts:
        clean = re.sub(r'[^a-zA-Z0-9_]', '_', part)
        if clean and clean[0].isdigit():
            clean = '_' + clean
        clean_parts.append(clean)
    return 'package content.' + '.'.join(clean_parts)


def get_kt_dir(md_path: Path) -> Path:
    if md_path.name == 'index.md':
        return md_path.parent
    else:
        return md_path.parent / md_path.stem


def get_rel_content_path(md_path: Path) -> str:
    """Get the relative path from the content directory."""
    rel = md_path.relative_to(CONTENT_DIR)
    if rel.name == 'index.md':
        return str(rel.parent)
    return str(rel.parent / rel.stem)


def determine_imports(code: str, content_path: str) -> list[str]:
    """Determine needed imports based on code content and directory context."""
    imports = set()

    # Always add core imports if code references any http4k types
    http4k_indicators = ['Request', 'Response', 'HttpHandler', 'Filter', 'Status', 'Method',
                         'Body', 'Uri', 'ContentType', 'then(', '.then(', 'with(']
    if any(ind in code for ind in http4k_indicators):
        for imp in CORE_IMPORTS:
            imports.add(imp)

    # Check keyword-based imports
    for keyword, import_list in KEYWORD_IMPORT_MAP.items():
        if keyword in code:
            for imp in import_list:
                imports.add(imp)

    # Check for connect service-specific imports
    for service_path, service_pkg in CONNECT_SERVICE_MAP.items():
        if f'connect/reference/{service_path}' in content_path:
            imports.add(f'{service_pkg}.*')
            imports.add(f'{service_pkg}.model.*')
            for imp in CONNECT_COMMON_IMPORTS:
                imports.add(imp)
            # Also add core imports since connect examples use http4k core
            for imp in CORE_IMPORTS:
                imports.add(imp)
            for imp in CLIENT_IMPORTS:
                imports.add(imp)
            for imp in RESULT4K_IMPORTS:
                imports.add(imp)
            break

    # Check for AI service-specific imports
    for service_name, service_pkg in AI_SERVICE_MAP.items():
        if f'ai/reference/{service_name}' in content_path:
            imports.add(f'{service_pkg}.*')
            for imp in AI_COMMON_IMPORTS:
                imports.add(imp)
            for imp in CORE_IMPORTS:
                imports.add(imp)
            for imp in CLIENT_IMPORTS:
                imports.add(imp)
            break

    # Check for AI concepts pages
    if 'ai/concepts/' in content_path:
        for imp in AI_COMMON_IMPORTS:
            imports.add(imp)
        for imp in RESULT4K_IMPORTS:
            imports.add(imp)

    # Connect concepts pages
    if 'connect/concepts/' in content_path:
        for imp in CONNECT_COMMON_IMPORTS:
            imports.add(imp)
        for imp in CORE_IMPORTS:
            imports.add(imp)

    # http4k concepts pages
    if 'http4k/concepts/' in content_path or 'http4k/reference/' in content_path:
        for imp in CORE_IMPORTS:
            imports.add(imp)

    # AWS auth page
    if 'connect/reference/aws' in content_path:
        imports.add('org.http4k.connect.amazon.sqs.*')
        imports.add('org.http4k.connect.amazon.CredentialsProvider')
        for imp in CONNECT_COMMON_IMPORTS:
            imports.add(imp)

    # Storage pages
    if 'storage/' in content_path:
        imports.add('org.http4k.connect.storage.*')

    # Add java imports based on code
    if 'Duration' in code:
        imports.add('java.time.Duration')
    if 'LocalDate' in code:
        imports.add('java.time.LocalDate')
    if 'Instant' in code:
        imports.add('java.time.Instant')
    if 'UUID' in code:
        imports.add('java.util.UUID')
    if 'InputStream' in code:
        imports.add('java.io.InputStream')
    if 'ByteBuffer' in code:
        imports.add('java.nio.ByteBuffer')

    return sorted(imports)


def get_heading_context(md_content: str, block_start: int) -> str:
    text_before = md_content[:block_start]
    headings = list(re.finditer(r'^#{1,6}\s+(.+)$', text_before, re.MULTILINE))
    if headings:
        heading = headings[-1].group(1).strip()
        heading = re.sub(r'[^a-zA-Z0-9\s]', '', heading)
        heading = heading.strip().lower()
        heading = re.sub(r'\s+', '_', heading)
        heading = re.sub(r'^(default_fake_port_\d+)$', 'fake', heading)
        words = heading.split('_')
        if len(words) > 4:
            heading = '_'.join(words[:4])
        return heading
    return 'example'


def infer_name_from_code(code: str) -> str:
    code = code.strip()
    m = re.search(r'^(?:data\s+)?(?:class|object|interface|fun\s+interface)\s+(\w+)', code, re.MULTILINE)
    if m:
        name = m.group(1)
        name = re.sub(r'(?<=[a-z])(?=[A-Z])', '_', name).lower()
        return name
    m = re.search(r'^typealias\s+(\w+)', code, re.MULTILINE)
    if m:
        name = m.group(1)
        name = re.sub(r'(?<=[a-z])(?=[A-Z])', '_', name).lower()
        return name
    if re.search(r'^fun\s+main\b', code, re.MULTILINE):
        return 'main'
    m = re.search(r'^fun\s+(\w+)', code, re.MULTILINE)
    if m:
        return m.group(1).lower()
    return None


def generate_kt_filename(heading: str, code: str, existing_files: set, index: int) -> str:
    code_name = infer_name_from_code(code)
    if code_name and len(code_name) <= 30:
        base = code_name
    else:
        base = heading if heading else 'example'
    if len(base) > 30:
        base = base[:30].rstrip('_')
    name = f"{base}.kt"
    if name not in existing_files:
        existing_files.add(name)
        return name
    for i in range(1, 100):
        name = f"{base}_{i}.kt"
        if name not in existing_files:
            existing_files.add(name)
            return name
    return f"code_{index}.kt"


def needs_wrapping(code: str) -> bool:
    """Check if code has loose statements that need wrapping in a function."""
    code = code.strip()
    lines = [l.strip() for l in code.split('\n') if l.strip() and not l.strip().startswith('//')]
    if not lines:
        return False
    # If code starts with val/var assignments or function calls at top level (not in a class/fun),
    # it might need wrapping. But actually top-level vals are fine in Kotlin.
    # Code that needs wrapping: standalone expressions like `client(request)` or `println(...)`
    # But top-level val declarations and function definitions are fine.
    return False


def process_file(md_path: Path, dry_run: bool = True, min_lines: int = 3) -> list:
    content = md_path.read_text()
    blocks = list(KOTLIN_BLOCK_RE.finditer(content))
    if not blocks:
        return []

    kt_dir = get_kt_dir(md_path)
    package_decl = get_package_from_path(md_path)
    content_path = get_rel_content_path(md_path)

    existing_kt_files = set()
    if kt_dir.exists():
        for f in kt_dir.iterdir():
            if f.suffix == '.kt':
                existing_kt_files.add(f.name)
    existing_refs = set(re.findall(r'\{\{<\s*kotlin\s+file="([^"]+)"\s*>\}\}', content))
    existing_kt_files.update(existing_refs)

    non_gradle_blocks = []
    for match in blocks:
        code = match.group(1)
        if not is_gradle_block(code) and count_code_lines(code) >= min_lines:
            non_gradle_blocks.append(match)

    if not non_gradle_blocks:
        return []

    replacements = []
    for i, match in enumerate(non_gradle_blocks):
        code = match.group(1)
        heading = get_heading_context(content, match.start())
        kt_filename = generate_kt_filename(heading, code, existing_kt_files, i)
        kt_path = kt_dir / kt_filename

        # Build imports
        needed_imports = determine_imports(code, content_path)
        import_block = '\n'.join(f'import {imp}' for imp in needed_imports)

        # Build .kt file content
        parts = [package_decl, '']
        if import_block:
            parts.append(import_block)
            parts.append('')
        parts.append(code.rstrip())
        parts.append('')
        kt_content = '\n'.join(parts)

        shortcode = f'{{{{< kotlin file="{kt_filename}" >}}}}'

        replacements.append({
            'match': match,
            'kt_path': kt_path,
            'kt_content': kt_content,
            'shortcode': shortcode,
            'kt_filename': kt_filename,
        })

    if dry_run:
        for r in replacements:
            print(f"  Would create: {r['kt_path']}")
            print(f"  Would replace block with: {r['shortcode']}")
        return replacements

    new_content = content
    for r in reversed(replacements):
        match = r['match']
        new_content = new_content[:match.start()] + r['shortcode'] + new_content[match.end():]

    kt_dir.mkdir(parents=True, exist_ok=True)

    for r in replacements:
        r['kt_path'].write_text(r['kt_content'])
        print(f"  Created: {r['kt_path']}")

    md_path.write_text(new_content)
    print(f"  Updated: {md_path}")

    return replacements


def find_markdown_files_with_kotlin(min_lines: int = 3):
    results = []
    for md_path in sorted(CONTENT_DIR.rglob('*.md')):
        content = md_path.read_text()
        blocks = list(KOTLIN_BLOCK_RE.finditer(content))
        non_gradle = [b for b in blocks
                     if not is_gradle_block(b.group(1))
                     and count_code_lines(b.group(1)) >= min_lines]
        if non_gradle:
            results.append((md_path, len(non_gradle)))
    return results


def main():
    dry_run = '--apply' not in sys.argv
    min_lines = 3

    for arg in sys.argv[1:]:
        if arg.startswith('--min-lines='):
            min_lines = int(arg.split('=')[1])

    single_file = None
    for arg in sys.argv[1:]:
        if arg != '--apply' and not arg.startswith('--'):
            single_file = arg

    if single_file:
        md_path = Path(single_file)
        if not md_path.exists():
            md_path = CONTENT_DIR / single_file
        print(f"Processing: {md_path}")
        process_file(md_path, dry_run=dry_run, min_lines=min_lines)
        return

    files = find_markdown_files_with_kotlin(min_lines)
    total_blocks = sum(count for _, count in files)
    print(f"Found {len(files)} files with {total_blocks} non-gradle kotlin blocks (>= {min_lines} lines)")
    print()

    if dry_run:
        print("DRY RUN - use --apply to make changes")
        print()

    for md_path, count in files:
        print(f"\n{'='*60}")
        print(f"File: {md_path} ({count} blocks)")
        print(f"{'='*60}")
        process_file(md_path, dry_run=dry_run, min_lines=min_lines)


if __name__ == '__main__':
    main()
