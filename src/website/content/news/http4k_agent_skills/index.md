---
title: "http4k Mastery, On Tap for Your AI Coding Agent"
description: "http4k Agent Skills gives your AI coding agent the patterns, tricks, and hard-won knowledge of the http4k masters - regenerated with every release."
date: 2026-04-27
image: agents.webp
---

<img class="imageMid my-4" src="./agents.webp" alt="An AI coding agent learning http4k patterns from a discoverable skills index"/>

Here's a familiar scene: you're pairing with an AI coding agent, you ask it to wire up an http4k server, and it confidently generates code using an API that doesn't exist. It looks plausible. It compiles in the AI's imagination. But it's pure hallucination.

We got tired of that, so we did something about it.

## Agent Skills: Teach, Don't Hallucinate

[http4k Agent Skills](https://github.com/http4k/agent-skills) is a skill plugin that gives AI coding agents accurate, up-to-date knowledge of the entire http4k ecosystem - all 200+ modules, with real patterns, real APIs, and real examples.

Install it in [Claude Code](https://claude.ai/code) and it automatically detects which http4k modules your project uses by scanning your build files. No manual prompting, no pasting documentation links, no "please read the docs at..." preamble. It just works - loading only what's relevant to your project.

The skill is regenerated automatically with every http4k release, so your agent always has the latest APIs and patterns - no manual updates, no version drift.

The result? Your AI assistant writes http4k code that actually compiles. Servers, clients, lenses, MCP, serverless, testing - it knows the patterns because we taught it the patterns.

## More Than an API Reference

But this isn't just a list of function signatures. We'll be continuously improving the tips, tricks, and hard-won knowledge from nine years of building http4k into the skill itself - the design patterns behind the toolkit, the compositional idioms that make http4k code sing, the advanced testing approaches that let you ship with confidence. The kind of things you'd pick up after months of pairing with the http4k creators.

Think of it as having an http4k master on your team. Your AI agent doesn't just know *what* the API is - it knows *how* we'd use it, *why* we'd compose things a certain way, and *when* to reach for which pattern. That knowledge gets deeper with every release.

## Discoverable by Design

We've published the skills index at a well-known URL following Cloudflare's open discovery protocol:

[https://http4k.org/.well-known/agent-skills/index.json](https://http4k.org/.well-known/agent-skills/index.json)

This means any AI agent or tool that understands the [Agent Skills discovery schema](https://agentskills.io/) can find and load http4k's skills automatically - no hardcoded integrations required. It's the same philosophy we apply to everything in http4k: composable, standards-based, and zero magic.

As more tools adopt the protocol, your http4k knowledge travels with you - regardless of which AI coding agent you're using.

## Why This Works for http4k

This is the kind of thing that only works when your toolkit has genuine consistency. If every module followed different conventions, no skill file could cover it. But http4k's 205 modules all follow the same functional patterns we established on day one - the same compositional approach, the same testing model, the same way of wiring things together.

That consistency is what makes a single skill file genuinely useful across the entire http4k ecosystems. One set of patterns, every module, zero special cases.

## Get Started

### Claude Code

Add the plugin directly using the http4k plugin marketplace:

```
/plugin marketplace add http4k/agent-skills
 /plugin install http4k
```

### OpenCode

Clone the skill into your project's `.opencode/skills/` directory:

```bash
git clone --branch <http4k version> --depth 1 https://github.com/http4k/agent-skills.git /tmp/http4k-agent-skills
cp -r /tmp/http4k-agent-skills/plugins/http4k/skills/http4k-development .opencode/skills/http4k-development
```

To update when a new http4k version drops, re-run the command with the new version tag.

That's it. Next time you're working on an http4k project, your agent will have the context it needs. No configuration, no ceremony.

We dogfood this every day and it's already changed how we work. Give it a try and [let us know what you think](https://github.com/http4k/http4k/issues).

Less vibe, more value.

# /the http4k team
