---
title: http4k v6 and beyond! Introducing Enterprise Edition and Long-Term Support
description:  There's a new major http4k release! We're ready to announce the next chapter in the http4k ecosystem, including the introduction of the http4k Enterprise Edition (EE) subscription.
date: 2024-12-03
---

#### TL;DR
- http4k will release its next major version (6) in January 2025
- http4k Community Edition (CE) - will continue to be released under the existing Apache 2 license.
- From v6, http4k CE will set its minimum supported target JVM version to 21.
- We are introducing the [http4k Enterprise Edition (EE)](/enterprise) subscription under a new commercial license, which will provide LTS support for http4k v5 and older JVMs as well as enterprise support features.

#### A brief history of time

From its inception over seven years ago, http4k has been increasingly adopted by teams looking to leverage its powerful simplicity, focus on testability and uncompromised stability in their projects. That now translates to over two million monthly downloads. With over 150 modules, http4k has continued to grow steadily and to have some of the most flexible support and integrations available in the Kotlin ecosystem, all backed up by rock-solid test suites and our trademark lightweight approach.

As the JVM and Kotlin ecosystems evolve, we’re committed to taking advantage of the latest features of the underlying platforms, while continuing to provide the best level of support for teams working on all versions.

With that in mind, we’re very excited to announce that the **next major version of the http4k ecosystem (all 150+ modules of it!) will be released in January 2025**. This release marks the start of a new chapter for the project and as usual we will be taking the opportunity to make some strategic breaking changes to the core of the library, removing all those annoying deprecations, and do some more general tidying on the entire codebase as groundwork for the future. We will also be renaming some modules to make things a bit more consistent.

We will of course, document all of these changes and provide a simple migration guide.

#### Into the future

From the beginning of the project, the http4k team have strived to support the widest array of environments that we could, and have continued to support JVM versions 8 and above for all http4k releases, avoiding backward incompatibilities wherever possible. However, we always knew that this decision could not last indefinitely and we are taking this opportunity to move forward into the future in a more strategic way. As well as learning from our mistakes and making some changes to the core of the library, this will allow us to take advantage of newer JVM features and optimisations in the target class file format which should provide a performance boost for all users.

From http4k v6, major versions of the open source http4k ecosystem - now taking on the name **http4k Community Edition (CE)** - will be released to be aligned with the regular support cadence of the underlying Long Term Support (LTS) JVM versions from Oracle. As such, http4k CE version 6 will set its minimum Java version to 21 (which Oracle will provide public updates until late 2026) for a period of 24 months until the aligned major Community version is released in January 2027.

To put that more visually, this is the timeline for current and future Java LTS releases:

{{< timeline data="Java" >}}

### What does that mean for me?

For teams adopting the non-legacy versions of Java and http4k, nothing changes. The development of http4k as a primarily open-source solution means teams can count on the existing release cadence of http4k CE (averaging to once a week) and active community support by the toolkit creators in the [Kotlin Slack](https://kotlinlang.slack.com) (you can join via [this link](https://surveys.jetbrains.com/s3/kotlin-slack-sign-up)).

In addition, we’re officially introducing an LTS support option for http4k v5 through the **http4k Enterprise Edition (EE) subscription**, to be released under the new commercial http4k Enterprise License, to provide peace of mind to companies who are unable to move to the most recent Java versions but still want to ensure stability and security in their http4k applications.

Based on the above Java timeline, the plan is for future major http4k versions to have the following Community and Enterprise Edition (LTS) update schedule:

{{< timeline data="http4k" >}}

The launch feature set for http4k EE will consist of:

- **24 months of LTS support** for http4k ecosystem v5, including access to both source and binary versions of the libraries, which will be accessed through private GitHub and Maven repositories. Security patches and essential bug fixes will be proactively released as required.
- Access to **priority support** channels (via email and dedicated [http4k Slack](https://http4k.slack.com) instance) for direct support access to http4k expertise.
- Full **licence reporting** for all http4k modules.
- **Discounted consultancy and training** rates delivered by core contributors to the http4k ecosystem.

In future, subscribers to http4k EE will also automatically gain access to **planned premium features and integrations** as they become available which will provide further advantages to teams. These expert-designed, proprietary extensions will reflect real-world requirements and enterprise patterns built from years of successfully delivering complex systems with http4k. Watch this space!

The aim of http4k EE is to provide a level of support to major releases of the toolkit through a commercial offering and strengthen the investment in the evolution of the http4k ecosystem as a whole.

If your company is interested in longer-term access to http4k version 5 or want to take advantage of the peace-of-mind benefits provided by http4k EE, **please get in touch with the team to arrange a call**, either by reaching out on the Kotlin Slack instance or through our email channels at enterprise@http4k.org. You can also read more about the offering on the [http4k Enterprise Edition](/enterprise) page.

In the meantime, the team will be busy paving the way for the next chapter in the whole http4k ecosystem. Please join us in the [Kotlin Slack](https://kotlinlang.slack.com) to discuss the upcoming changes and to provide feedback on the roadmap, 

Get ready for version 6 in January!

/Peace out

Team http4k
