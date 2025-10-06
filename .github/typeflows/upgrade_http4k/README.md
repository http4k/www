# Upgrade http4k (upgrade_http4k.yml)

```mermaid
%%{init: {"flowchart": {"curve": "basis"}}}%%
flowchart TD
    repositorydispatch(["🔔 repository_dispatch<br/>(http4k-release)"])
    subgraph upgradehttp4kyml["Upgrade http4k"]
        upgradehttp4kyml_upgrade["upgrade<br/>🐧 ubuntu-latest"]
    end
    repositorydispatch --> upgradehttp4kyml_upgrade
```

## Job: upgrade

| Job | OS | Dependencies | Config |
|-----|----|--------------|---------| 
| `upgrade` | 🐧 ubuntu-latest | - | - |

### Steps

```mermaid
%%{init: {"flowchart": {"curve": "basis"}}}%%
flowchart TD
    step1["Step 1: Checkout"]
    style step1 fill:#f8f9fa,stroke:#495057
    action1["🎬 actions<br/>checkout"]
    style action1 fill:#e1f5fe,stroke:#0277bd
    step1 -.-> action1
    step2["Step 2: Configure git and upgrade<br/>💻 bash"]
    style step2 fill:#f3e5f5,stroke:#7b1fa2
    step1 --> step2
    step3["Step 3: Push changes<br/>💻 bash"]
    style step3 fill:#f3e5f5,stroke:#7b1fa2
    step2 --> step3
    step4["Step 4: Trigger release"]
    style step4 fill:#f8f9fa,stroke:#495057
    action4["🎬 peter-evans<br/>repository-dispatch<br/><br/>📝 Inputs:<br/>• event-type: release"]
    style action4 fill:#e1f5fe,stroke:#0277bd
    step4 -.-> action4
    step3 --> step4
```

**Step Types Legend:**
- 🔘 **Step Nodes** (Gray): Workflow step execution
- 🔵 **Action Blocks** (Blue): External GitHub Actions
- 🔷 **Action Blocks** (Light Blue): Local repository actions
- 🟣 **Script Nodes** (Purple): Run commands/scripts
- **Solid arrows** (→): Step execution flow
- **Dotted arrows** (-.->): Action usage with inputs