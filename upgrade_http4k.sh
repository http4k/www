#!/bin/bash
set -e

NEW_VERSION=$1

OLD_VERSION=$(grep -oP 'http4k_version=\K[^ ]+' gradle.properties

echo "Old version: $OLD_VERSION"

sed -i "s/\$OLD_VERSION\b/$NEW_VERSION/g" gradle.properties
sed -i "s/\$OLD_VERSION\b/$NEW_VERSION/g" src/website/config.yaml
