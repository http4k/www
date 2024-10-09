---
title: Contributing
type: contributing
description: Our contributors are the lifeblood of the http4k project. This page lists the people who have contributed to the project.
vendors:
  - tool: IntelliJ
    vendor: Jetbrains
    link: https://www.jetbrains.com
    description: "Jetbrains kindly provides the project with an Open Source License for the amazing IntelliJ IDE and their suite of developer productivity tools."
    image: /images/jetbrains.webp   
  - tool: Tuple
    vendor: Tuple
    link: https://tuple.app/
    description: "Tuple provides the http4k team with their amazing Pair-Programming tool Tuple allowing us to collaborate to build the library."
    image: /images/tuple.jpg
  - tool: JProfiler
    vendor: YourKit
    description: "YourKit supports open source projects with innovative and intelligent tools
for monitoring and profiling .Net and Java applications."
    image: /images/yourkit.png
---

There are many ways in which you can contribute to the development of the library:

- Give us a Star on Github - you know you want to. ;)
- Using http4k to build something? Get in touch and tell everyone about it, or even just us!
- [Back us!](https://opencollective.com/http4k#backer) The http4k team build the library out of love for software
  engineering and the OpenSource community, but running a project of this size is not without it's costs. Please see
  below for sponsorship options to help us keep the project running.
- [Get help!](/lts-support/) The http4k team support the library beyond the normal GitHub issues and PRs with our Long-term support packages. 

### General guidelines

- Questions can be directed towards the [Slack #http4k](http://slack.kotlinlang.org/) channel, or on
  X <a href="https://twitter.com/http4k">@http4k</a>
- For issues, please describe giving as much detail as you can - including version and steps to recreate
- At the moment, PRs should be sent to the master branch - this might change in future so check back everytime!
- Source/binary compatibility always must be kept as far as possible - this is a must for minor and patch versions
- PR changes should have test coverage. Note that we use Junit 5 as a test engine - which uses new `@Test` annotations.
- All the PRs must pass the GitHub CI jobs before merging them

Testing with default settings is required when push changes. Note that we currently build against Java
21 ([jEnv](https://www.jenv.be/) is good for managing multiple java versions):

```shell
./gradlew check
```
