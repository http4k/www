http4kVerify {
    // Fail the build if any signature is invalid (default: true)
    failOnError = true

    // Override the key list URL (default: https://http4k.org/.well-known/cosign-keys.json)
    keyListUrl = "https://http4k.org/.well-known/cosign-keys.json"

    // Or pin a single public key instead of using the key list
    publicKey = file("path/to/cosign.pub")
}
