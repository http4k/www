# http4k Agent Skills

AI coding skills that teach your agent how to work with the [http4k](https://http4k.org) Kotlin HTTP toolkit. Works with [Claude Code](https://claude.ai/code), [OpenCode](https://opencode.ai), and any agent that supports the `.claude/skills/` directory format.

## What's in the skill

Comprehensive guidance for all 200+ http4k modules — servers, clients, formats, testing, serverless, cloud platforms, AI/LLM integrations, MCP, Connect adapters, and more. The skill automatically detects which http4k modules your project uses (via Gradle/Maven build files) and loads only the relevant references.

This isn't just API surface. The references include the patterns, idioms, and testing strategies we use to build http4k itself — the stuff that separates "it compiles" from "it's good."

## Installation

### Claude Code (recommended)

Install the plugin in Claude Code:

```
/plugin marketplace add http4k/agent-skills
/plugin install http4k
```

The plugin auto-updates when we release, so your agent's knowledge stays current automatically.

### OpenCode / manual install

Any agent that reads the `.claude/skills/` directory can use the skill content directly. Copy it into your project:

```bash
git clone --depth 1 https://github.com/http4k/agent-skills.git /tmp/http4k-skills && \
  mkdir -p .claude/skills && \
  cp -r /tmp/http4k-skills/plugins/http4k/skills/http4k-development .claude/skills/ && \
  rm -rf /tmp/http4k-skills
```

To update when a new http4k version drops, re-run the command.

## How it stays current

Skill content is regenerated from the http4k source code on every release. When we tag a new version of http4k, a GitHub Actions workflow checks out the new source, uses Claude to generate updated reference material for every module that changed, and commits the result. The skill version tracks the http4k release version exactly.

Your agent's knowledge of http4k is never stale.

## Compatible agents

| Agent | Method | Auto-updates |
|-------|--------|--------------|
| [Claude Code](https://claude.ai/code) | Plugin (`/plugin install`) | Yes |
| [OpenCode](https://opencode.ai) | `.claude/skills/` directory | Manual (re-run clone command) |
| Other agents | `.claude/skills/` directory | Manual |

## License

See [http4k.org](https://http4k.org) for licensing information.
