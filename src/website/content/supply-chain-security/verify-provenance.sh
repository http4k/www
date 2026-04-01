#!/bin/bash
set -euo pipefail

GROUP_PATH="${1:-org/http4k/http4k-core}"
VERSION="${2:-6.39.1.0}"
PUBLIC_KEY="${3:-cosign.pub}"
MAVEN_USER="${MAVEN_USER:-}"
MAVEN_PASSWORD="${MAVEN_PASSWORD:-}"
BASE_URL="https://maven.http4k.org"

if [ -z "$MAVEN_USER" ] || [ -z "$MAVEN_PASSWORD" ]; then
    echo -n "Maven username: " && read MAVEN_USER
    echo -n "Maven password: " && read -s MAVEN_PASSWORD && echo
fi
WORKDIR=$(mktemp -d)

if [ ! -f "$PUBLIC_KEY" ]; then
    echo "ERROR: Public key not found at $PUBLIC_KEY"
    echo "Usage: $0 <group/artifact/path> <version> <cosign.pub>"
    exit 1
fi

PUBLIC_KEY=$(realpath "$PUBLIC_KEY")
ARTIFACT=$(basename "$GROUP_PATH")
URL="$BASE_URL/$GROUP_PATH/$VERSION"

echo "=== Downloading from $URL ==="

FILES=(
    "${ARTIFACT}-${VERSION}.jar"
    "${ARTIFACT}-${VERSION}.pom"
    "${ARTIFACT}-${VERSION}-sources.jar"
    "${ARTIFACT}-${VERSION}-javadoc.jar"
    "${ARTIFACT}-${VERSION}.jar.sigstore.json"
    "${ARTIFACT}-${VERSION}-sbom.json"
    "${ARTIFACT}-${VERSION}-sbom.json.sigstore.json"
    "${ARTIFACT}-${VERSION}.provenance.json"
    "${ARTIFACT}-${VERSION}.provenance.json.sigstore.json"
)

for f in "${FILES[@]}"; do
    if curl -sf -u "$MAVEN_USER:$MAVEN_PASSWORD" -o "$WORKDIR/$f" "$URL/$f"; then
        echo "  Downloaded: $f"
    else
        echo "  Not found:  $f"
    fi
done
echo ""

echo "=== Files downloaded ==="
ls -1 "$WORKDIR"
echo ""

PASS=0
FAIL=0

verify() {
    local file="$1"
    local bundle="${file}.sigstore.json"
    local name=$(basename "$file")

    if [ ! -f "$bundle" ]; then
        echo "  SKIP: $name (no .sigstore.json bundle)"
        return
    fi

    if cosign verify-blob "$file" \
        --key "$PUBLIC_KEY" \
        --bundle "$bundle" \
        --private-infrastructure 2>&1; then
        echo "  PASS: $name"
        PASS=$((PASS + 1))
    else
        echo "  FAIL: $name"
        FAIL=$((FAIL + 1))
    fi
}

echo "=== Verifying JAR signatures ==="
for jar in "$WORKDIR"/*.jar; do
    [ -f "$jar" ] || continue
    verify "$jar"
done
echo ""

echo "=== Verifying SBOM ==="
for sbom in "$WORKDIR"/*-sbom.json; do
    [ -f "$sbom" ] || continue
    verify "$sbom"
done
echo ""

echo "=== Verifying provenance ==="
for prov in "$WORKDIR"/*.provenance.json; do
    [ -f "$prov" ] || continue
    verify "$prov"

    echo ""
    echo "  Provenance content:"
    jq '.subject[] | "    \(.name): sha256:\(.digest.sha256)"' -r "$prov"
    echo ""
    echo "  Git commit: $(jq -r '.predicate.buildDefinition.resolvedDependencies[0].digest.gitCommit' "$prov")"
    echo "  Build ID:   $(jq -r '.predicate.runDetails.metadata.invocationId' "$prov")"
done
echo ""

echo "=== Results: $PASS passed, $FAIL failed ==="

rm -rf "$WORKDIR"

if [ "$FAIL" -gt 0 ]; then
    exit 1
fi
