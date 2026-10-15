---
type: ecosystem
category: Project
ecosystem: http4k Core
title: Changelog
description: http4k Core Changelog
---

http4k is released in two flavours. Community releases (`A.B.C.D`) are published to Maven Central (repo1.maven.org) for everyone. Enterprise releases (`A.B.C.D-ee`) are published to [maven.http4k.org](https://maven.http4k.org) for [http4k Enterprise](/enterprise/) customers. See [Distribution](/distribution/) for more on the release channels.

<ul class="nav nav-tabs mb-4" role="tablist">
    <li class="nav-item" role="presentation">
        <button class="nav-link active" data-bs-toggle="tab" data-changelog-tab="community" data-bs-target="#changelog-community" type="button" role="tab">Community</button>
    </li>
    <li class="nav-item" role="presentation">
        <button class="nav-link" data-bs-toggle="tab" data-changelog-tab="enterprise" data-bs-target="#changelog-enterprise" type="button" role="tab">Enterprise</button>
    </li>
</ul>

<div class="tab-content">
<div class="tab-pane fade show active" id="changelog-community" role="tabpanel">
{{< remote remote="https://raw.githubusercontent.com/http4k/http4k/refs/heads/master/CHANGELOG.md" >}}
</div>
<div class="tab-pane fade" id="changelog-enterprise" role="tabpanel">
{{< remote remote="https://raw.githubusercontent.com/http4k/http4k/refs/heads/ee/CHANGELOG.md" >}}
</div>
</div>

<script>
    document.addEventListener("DOMContentLoaded", function () {
        var tabs = Array.from(document.querySelectorAll("[data-changelog-tab]"));
        var selected = tabs.find(function (t) { return "#" + t.dataset.changelogTab === location.hash; });
        if (selected) selected.click();
        tabs.forEach(function (t) {
            t.addEventListener("shown.bs.tab", function () {
                history.replaceState(null, "", "#" + t.dataset.changelogTab);
            });
        });
    });
</script>
