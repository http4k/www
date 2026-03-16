#!/usr/bin/env python3
"""
Add necessary imports to extracted .kt files based on symbols used.
"""

import re
import sys
from pathlib import Path

CONTENT_DIR = Path("src/website/content")

# Map of symbols to their imports
SYMBOL_IMPORTS = {
    # Core types
    'Request': 'org.http4k.core.Request',
    'Response': 'org.http4k.core.Response',
    'HttpHandler': 'org.http4k.core.HttpHandler',
    'Filter': 'org.http4k.core.Filter',
    'Status': 'org.http4k.core.Status',
    'Method': 'org.http4k.core.Method',
    'Uri': 'org.http4k.core.Uri',
    'Body': 'org.http4k.core.Body',
    'ContentType': 'org.http4k.core.ContentType',
    'BodyMode': 'org.http4k.core.BodyMode',
    'HttpTransaction': 'org.http4k.core.HttpTransaction',
    'MemoryBody': 'org.http4k.core.MemoryBody',
    'UriTemplate': 'org.http4k.core.UriTemplate',

    # Methods
    'GET': 'org.http4k.core.Method.GET',
    'POST': 'org.http4k.core.Method.POST',
    'PUT': 'org.http4k.core.Method.PUT',
    'DELETE': 'org.http4k.core.Method.DELETE',
    'PATCH': 'org.http4k.core.Method.PATCH',
    'HEAD': 'org.http4k.core.Method.HEAD',
    'OPTIONS': 'org.http4k.core.Method.OPTIONS',

    # Status codes
    'OK': 'org.http4k.core.Status.Companion.OK',
    'NOT_FOUND': 'org.http4k.core.Status.Companion.NOT_FOUND',
    'BAD_REQUEST': 'org.http4k.core.Status.Companion.BAD_REQUEST',
    'INTERNAL_SERVER_ERROR': 'org.http4k.core.Status.Companion.INTERNAL_SERVER_ERROR',
    'ACCEPTED': 'org.http4k.core.Status.Companion.ACCEPTED',
    'CREATED': 'org.http4k.core.Status.Companion.CREATED',
    'NO_CONTENT': 'org.http4k.core.Status.Companion.NO_CONTENT',
    'FORBIDDEN': 'org.http4k.core.Status.Companion.FORBIDDEN',
    'UNAUTHORIZED': 'org.http4k.core.Status.Companion.UNAUTHORIZED',
    'SEE_OTHER': 'org.http4k.core.Status.Companion.SEE_OTHER',
    'FOUND': 'org.http4k.core.Status.Companion.FOUND',

    # Core extensions
    'then': 'org.http4k.core.then',
    'with': 'org.http4k.core.with',
    'queries': 'org.http4k.core.queries',
    'toCurl': 'org.http4k.core.toCurl',
    'toBody': 'org.http4k.core.toBody',

    # Routing
    'routes': 'org.http4k.routing.routes',
    'bind': 'org.http4k.routing.bind',
    'static': 'org.http4k.routing.static',
    'singlePageApp': 'org.http4k.routing.singlePageApp',
    'RoutingHttpHandler': 'org.http4k.routing.RoutingHttpHandler',
    'websockets': 'org.http4k.routing.websockets',
    'sse': 'org.http4k.routing.sse',
    'poly': 'org.http4k.routing.poly',
    'ResourceLoader': 'org.http4k.routing.ResourceLoader',
    'Classpath': 'org.http4k.routing.ResourceLoader.Classpath',
    'Directory': 'org.http4k.routing.ResourceLoader.Directory',
    'webJars': 'org.http4k.routing.webJars',

    # Servers
    'asServer': 'org.http4k.server.asServer',
    'Jetty': 'org.http4k.server.Jetty',
    'Undertow': 'org.http4k.server.Undertow',
    'SunHttp': 'org.http4k.server.SunHttp',
    'Netty': 'org.http4k.server.Netty',
    'Http4kServer': 'org.http4k.server.Http4kServer',
    'PolyServerConfig': 'org.http4k.server.PolyServerConfig',

    # Clients
    'ApacheClient': 'org.http4k.client.ApacheClient',
    'ApacheAsyncClient': 'org.http4k.client.ApacheAsyncClient',
    'OkHttp': 'org.http4k.client.OkHttp',
    'JavaHttpClient': 'org.http4k.client.JavaHttpClient',

    # Lenses
    'Header': 'org.http4k.lens.Header',
    'Query': 'org.http4k.lens.Query',
    'Path': 'org.http4k.lens.Path',
    'FormField': 'org.http4k.lens.FormField',
    'Validator': 'org.http4k.lens.Validator',
    'BiDiBodyLens': 'org.http4k.lens.BiDiBodyLens',
    'LensFailure': 'org.http4k.lens.LensFailure',

    # Lens extensions
    'int': 'org.http4k.lens.int',
    'long': 'org.http4k.lens.long',
    'string': 'org.http4k.lens.string',
    'nonEmptyString': 'org.http4k.lens.nonEmptyString',
    'localDate': 'org.http4k.lens.localDate',
    'boolean': 'org.http4k.lens.boolean',

    # Filters
    'ServerFilters': 'org.http4k.filter.ServerFilters',
    'ClientFilters': 'org.http4k.filter.ClientFilters',
    'CachingFilters': 'org.http4k.filter.CachingFilters',
    'DebuggingFilters': 'org.http4k.filter.DebuggingFilters',
    'RequestFilters': 'org.http4k.filter.RequestFilters',
    'ResponseFilters': 'org.http4k.filter.ResponseFilters',
    'TrafficFilters': 'org.http4k.filter.TrafficFilters',
    'CatchLensFailure': 'org.http4k.filter.ServerFilters.CatchLensFailure',

    # Websockets
    'WsHandler': 'org.http4k.websocket.WsHandler',
    'WsResponse': 'org.http4k.websocket.WsResponse',
    'WsMessage': 'org.http4k.websocket.WsMessage',
    'Websocket': 'org.http4k.websocket.Websocket',
    'WsConsumer': 'org.http4k.websocket.WsConsumer',
    'WsRouter': 'org.http4k.websocket.WsRouter',
    'testWsClient': 'org.http4k.websocket.testWsClient',

    # SSE
    'SseHandler': 'org.http4k.sse.SseHandler',
    'SseResponse': 'org.http4k.sse.SseResponse',
    'SseMessage': 'org.http4k.sse.SseMessage',
    'Sse': 'org.http4k.sse.Sse',
    'SseConsumer': 'org.http4k.sse.SseConsumer',
    'SseFilter': 'org.http4k.sse.SseFilter',

    # Formats
    'Jackson': 'org.http4k.format.Jackson',
    'Moshi': 'org.http4k.format.Moshi',
    'Gson': 'org.http4k.format.Gson',
    'Klaxon': 'org.http4k.format.Klaxon',
    'KotlinxSerialization': 'org.http4k.format.KotlinxSerialization',
    'Argo': 'org.http4k.format.Argo',
    'auto': 'org.http4k.format.auto',

    # JSON
    'json': 'org.http4k.lens.json',

    # Contracts/OpenAPI
    'contract': 'org.http4k.contract.contract',
    'ContractRoute': 'org.http4k.contract.ContractRoute',
    'ContractRenderer': 'org.http4k.contract.openapi.OpenAPIJackson',
    'PreFlightExtraction': 'org.http4k.contract.PreFlightExtraction',
    'bindContract': 'org.http4k.contract.bindContract',
    'meta': 'org.http4k.contract.meta',

    # Serverless
    'AppLoader': 'org.http4k.serverless.AppLoader',
    'FnLoader': 'org.http4k.serverless.FnLoader',
    'AppLoaderWithContexts': 'org.http4k.serverless.AppLoaderWithContexts',
    'ApiGatewayV2LambdaFunction': 'org.http4k.serverless.ApiGatewayV2LambdaFunction',
    'ApiGatewayV1LambdaFunction': 'org.http4k.serverless.ApiGatewayV1LambdaFunction',
    'FnHandler': 'org.http4k.serverless.FnHandler',

    # Testing
    'RecordingEvents': 'org.http4k.events.RecordingEvents',
    'RequestContext': 'org.http4k.lens.RequestContext',
    'RequestContexts': 'org.http4k.lens.RequestContexts',

    # Connect types
    'CredentialsProvider': 'org.http4k.connect.amazon.CredentialsProvider',
    'Environment': 'org.http4k.cloudnative.env.Environment',

    # JUnit
    'Test': 'org.junit.jupiter.api.Test',

    # Misc
    'PolyHandler': 'org.http4k.routing.PolyHandler',
    'RouterMatch': 'org.http4k.routing.RouterMatch',
    'Router': 'org.http4k.routing.Router',
    'Events': 'org.http4k.events.Events',
    'Event': 'org.http4k.events.Event',
    'AutoMarshalledEvent': 'org.http4k.events.AutoMarshalledEvent',
    'EventFilters': 'org.http4k.events.EventFilters',
    'CloudEvent': 'org.http4k.cloudevents.CloudEvent',

    # AI types
    'Chat': 'org.http4k.ai.llm.chat.Chat',
    'StreamingChat': 'org.http4k.ai.llm.chat.StreamingChat',
    'ChatRequest': 'org.http4k.ai.llm.chat.ChatRequest',
    'ChatResponse': 'org.http4k.ai.llm.chat.ChatResponse',
    'ImageGeneration': 'org.http4k.ai.llm.images.ImageGeneration',
    'ImageRequest': 'org.http4k.ai.llm.images.ImageRequest',
    'ImageResponse': 'org.http4k.ai.llm.images.ImageResponse',
    'LLMResult': 'org.http4k.ai.llm.LLMResult',
    'LLMError': 'org.http4k.ai.llm.LLMError',
    'Result4k': 'dev.forkhandles.result4k.Result4k',
    'Msg': 'org.http4k.ai.llm.msg.Msg',
    'MsgContent': 'org.http4k.ai.llm.msg.MsgContent',
    'ModelName': 'org.http4k.ai.model.ModelName',
    'ApiKey': 'org.http4k.ai.model.ApiKey',
    'RequestId': 'org.http4k.ai.model.RequestId',
    'TokenCount': 'org.http4k.ai.model.TokenCount',
    'Role': 'org.http4k.ai.model.Role',
    'Tool': 'org.http4k.ai.llm.tools.Tool',
    'ToolChoice': 'org.http4k.ai.llm.tools.ToolChoice',
    'Resource': 'org.http4k.ai.llm.msg.Resource',
}

# Symbols to ignore (Kotlin stdlib, common types that don't need imports)
IGNORE_SYMBOLS = {
    'String', 'Int', 'Long', 'Boolean', 'Double', 'Float', 'Unit', 'Any',
    'List', 'Map', 'Set', 'Pair', 'Triple', 'Sequence',
    'println', 'print', 'listOf', 'mapOf', 'setOf', 'arrayOf',
    'null', 'true', 'false', 'this', 'it', 'super',
    'fun', 'val', 'var', 'class', 'object', 'interface', 'enum',
    'if', 'else', 'when', 'for', 'while', 'do', 'return', 'break', 'continue',
    'package', 'import', 'typealias', 'data', 'sealed', 'abstract', 'open',
    'private', 'protected', 'internal', 'public', 'override', 'operator',
    'Thread', 'System', 'Comparable',
}


def find_needed_imports(code: str) -> list[str]:
    """Analyze code and determine needed imports."""
    # Find all capitalized identifiers that might need imports
    # Also find specific lowercase function names we know about
    words = set(re.findall(r'\b([A-Z]\w+)\b', code))
    # Also check for known lowercase symbols like 'routes', 'bind', etc.
    all_words = set(re.findall(r'\b(\w+)\b', code))

    imports = set()
    for word in words | all_words:
        if word in IGNORE_SYMBOLS:
            continue
        if word in SYMBOL_IMPORTS:
            imports.add(SYMBOL_IMPORTS[word])

    return sorted(imports)


def process_kt_file(kt_path: Path, dry_run: bool = True) -> bool:
    """Add imports to a .kt file if needed."""
    content = kt_path.read_text()

    # Skip if already has imports (beyond package)
    lines = content.split('\n')
    has_imports = any(line.startswith('import ') for line in lines)
    if has_imports:
        return False

    # Find the package line
    package_match = re.match(r'(package\s+\S+)\n', content)
    if not package_match:
        return False

    package_line = package_match.group(0)
    code_after_package = content[len(package_line):]

    # Determine needed imports
    needed_imports = find_needed_imports(code_after_package)
    if not needed_imports:
        return False

    import_block = '\n'.join(f'import {imp}' for imp in needed_imports)

    new_content = package_line + '\n' + import_block + '\n' + code_after_package

    if dry_run:
        print(f"  {kt_path.name}: +{len(needed_imports)} imports")
        for imp in needed_imports:
            print(f"    import {imp}")
        return True

    kt_path.write_text(new_content)
    print(f"  Fixed: {kt_path} (+{len(needed_imports)} imports)")
    return True


def main():
    dry_run = '--apply' not in sys.argv

    # Find all newly created .kt files (those without imports)
    kt_files = sorted(CONTENT_DIR.rglob('*.kt'))
    fixed = 0

    for kt_path in kt_files:
        content = kt_path.read_text()
        # Only process files that have a package declaration but no imports
        if not content.startswith('package content.'):
            continue
        if any(line.startswith('import ') for line in content.split('\n')):
            continue

        if dry_run:
            print(f"\n{kt_path}:")

        if process_kt_file(kt_path, dry_run):
            fixed += 1

    print(f"\n{'Would fix' if dry_run else 'Fixed'} {fixed} files")
    if dry_run:
        print("Use --apply to make changes")


if __name__ == '__main__':
    main()
