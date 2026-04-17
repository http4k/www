---
category: Reference
type: ecosystem
ecosystem: http4k Enterprise
title: Enterprise Maven
description: Setup and configuration for the http4k Enterprise Repository at maven.http4k.org
---

All http4k Enterprise Edition artifacts are available through **[maven.http4k.org](https://maven.http4k.org)**. This includes community (`org.http4k`) and pro (`org.http4k.pro`) modules, each published with cryptographically signed provenance, CycloneDX SBOMs, and license compliance reports.

Access credentials are included with your [http4k Enterprise Edition](/enterprise/) subscription.

### Gradle Configuration

{{< kotlin file="gradle-repository.kts" >}}

Add your credentials to `~/.gradle/gradle.properties`:

{{< shell file="gradle-credentials.properties" >}}

### Maven Configuration

Add the repository to your `pom.xml`:

{{< xml file="pom-repository.xml" >}}

And credentials to `~/.m2/settings.xml`:

{{< xml file="settings-credentials.xml" >}}

### Artifactory Configuration

If your organisation uses Artifactory as a repository manager, add **[maven.http4k.org](https://maven.http4k.org)** as a remote repository:

1. Navigate to **Administration > Repositories > Remote**
2. Click **New Remote Repository** and select **Maven**
3. Set the **URL** to `https://maven.http4k.org`
4. Under **Advanced**, configure **Username** and **Password** with your http4k credentials
5. Save and add the remote repository to your virtual repository resolution order

### Nexus Configuration

For Sonatype Nexus, add a proxy repository:

1. Navigate to **Repository > Repositories > Create repository**
2. Select **maven2 (proxy)**
3. Set the **Remote storage URL** to `https://maven.http4k.org`
4. Under **HTTP > Authentication**, enter your http4k credentials
5. Add the proxy repository to your maven-public group

