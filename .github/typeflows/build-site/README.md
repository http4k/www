# Build and Deploy (build-site.yml)

```mermaid
%%{init: {"flowchart": {"curve": "basis"}}}%%
flowchart TD
    push(["📤 push<br/>branches(only: 1)"])
    repositorydispatch(["🔔 repository_dispatch<br/>(release)"])
    workflowdispatch(["👤 workflow_dispatch"])
    schedule(["⏰ schedule<br/>0 0,6,12,18 * * *"])
    subgraph buildsiteyml["Build and Deploy"]
        buildsiteyml_releasesite["release-site<br/>🐧 ubuntu-latest"]
    end
    push --> buildsiteyml_releasesite
    repositorydispatch --> buildsiteyml_releasesite
    workflowdispatch --> buildsiteyml_releasesite
    schedule --> buildsiteyml_releasesite
```

## Job: release-site

| Job | OS | Dependencies | Config |
|-----|----|--------------|---------| 
| `release-site` | 🐧 ubuntu-latest | - | - |

### Steps

```mermaid
%%{init: {"flowchart": {"curve": "basis"}}}%%
flowchart TD
    step1["Step 1: Checkout"]
    style step1 fill:#f8f9fa,stroke:#495057
    action1["🎬 actions<br/>checkout<br/><br/>📝 Inputs:<br/>• fetch-depth: 0"]
    style action1 fill:#e1f5fe,stroke:#0277bd
    step1 -.-> action1
    step2["Step 2: Setup Java"]
    style step2 fill:#f8f9fa,stroke:#495057
    action2["🎬 actions<br/>setup-java<br/><br/>📝 Inputs:<br/>• java-version: 21<br/>• distribution: temurin"]
    style action2 fill:#e1f5fe,stroke:#0277bd
    step2 -.-> action2
    step1 --> step2
    step3["Step 3: Setup Gradle"]
    style step3 fill:#f8f9fa,stroke:#495057
    action3["🎬 gradle<br/>actions/setup-gradle<br/><br/>📝 Inputs:<br/>• gradle-version: 9.0.0"]
    style action3 fill:#e1f5fe,stroke:#0277bd
    step3 -.-> action3
    step2 --> step3
    step4["Step 4: Build<br/>💻 bash<br/>⏱️ 25m timeout"]
    style step4 fill:#f3e5f5,stroke:#7b1fa2
    step3 --> step4
    step5["Step 5: Setup Hugo"]
    style step5 fill:#f8f9fa,stroke:#495057
    action5["🎬 peaceiris<br/>actions-hugo<br/><br/>📝 Inputs:<br/>• hugo-version: 0.163.0<br/>• extended: true"]
    style action5 fill:#e1f5fe,stroke:#0277bd
    step5 -.-> action5
    step4 --> step5
    step6["Step 6: Build Site<br/>💻 bash"]
    style step6 fill:#f3e5f5,stroke:#7b1fa2
    step5 --> step6
    step7["Step 7: Deploy"]
    style step7 fill:#f8f9fa,stroke:#495057
    action7["🎬 peaceiris<br/>actions-gh-pages<br/><br/>📝 Inputs:<br/>• personal_token: ${{ secrets.TOOLBOX_REPO_TOKEN...<br/>• external_repository: http4k/http4k.github.io<br/>• publish_branch: master<br/>• publish_dir: build/www<br/>• cname: www.http4k.org<br/>• force_orphan: true"]
    style action7 fill:#e1f5fe,stroke:#0277bd
    step7 -.-> action7
    step6 --> step7
```

**Step Types Legend:**
- 🔘 **Step Nodes** (Gray): Workflow step execution
- 🔵 **Action Blocks** (Blue): External GitHub Actions
- 🔷 **Action Blocks** (Light Blue): Local repository actions
- 🟣 **Script Nodes** (Purple): Run commands/scripts
- **Solid arrows** (→): Step execution flow
- **Dotted arrows** (-.->): Action usage with inputs