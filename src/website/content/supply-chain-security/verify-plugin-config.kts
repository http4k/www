http4kVerify {
    // Fail the build if any signature is invalid (default: true)
    failOnError = true

    // Use a custom public key instead of downloading from http4k.org
    publicKey = file("path/to/cosign.pub")
}
