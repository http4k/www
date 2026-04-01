cosign verify-blob http4k-core-0.0.0.0-sbom.json \
    --key cosign.pub \
    --bundle http4k-core-0.0.0.0-sbom.json.sigstore.json \
    --private-infrastructure
