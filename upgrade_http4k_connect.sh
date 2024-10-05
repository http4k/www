#!/bin/bash
set -e

NEW_VERSION=$1

OLD_VERSION=$(grep -o 'http4k_connect_version=.*' gradle.properties | head -n 1 | cut -d '=' -f 2)

echo "Old version: $OLD_VERSION"

sed -i "s/${OLD_VERSION}/${NEW_VERSION}/g" gradle.properties
sed -i "s/${OLD_VERSION}/${NEW_VERSION}/g" src/website/config.yaml
