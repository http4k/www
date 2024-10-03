---
title: Contributing
description: Our contributors are the lifeblood of the http4k project. This page lists the people who have contributed to the project.
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

### Contributors

Thank you to all the people who have already contributed to http4k!
<a href="https://github.com/http4k/http4k/graphs/contributors"><img src="https://opencollective.com/http4k/contributors.svg?width=890" /></a>

### Vendor support

Many thanks to all of the software vendors who supply tools to help us deliver http4k to it's community:

#### Kotlin IDE

<img src="https://www.http4k.org/img/intellij-100.png" alt="intellij"/></a>

[Jetbrains](https://www.jetbrains.com) kindly supplies the project with an Open Source License for the amazing IntelliJ
IDE.

#### Pairing tools

<img src="https://www.http4k.org/img/tuple.png" alt="tuple"/></a>

[Tuple](https://tuple.app/) supplies the http4k team with their amazing Pair-Programming tool Tuple allowing us to
collaborate to build the library. Pairing is ace - everyone should do it!

#### JVM Profiling tools

<img src="https://www.yourkit.com/images/yklogo.png" alt="yourkit"/>

YourKit supports open source projects with innovative and intelligent tools
for monitoring and profiling Java and .NET applications.
YourKit is the creator of <a href="https://www.yourkit.com/java/profiler/">YourKit Java Profiler</a>,
<a href="https://www.yourkit.com/.net/profiler/">YourKit .NET Profiler</a>,
and <a href="https://www.yourkit.com/youmonitor/">YourKit YouMonitor</a>.
