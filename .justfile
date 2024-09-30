# see https://github.com/casey/just

_recipes:
    @just --list --unsorted --list-heading $'Available recipes:\n' --list-prefix "  "

# builds the static site
@build:
    hugo --minify -s website

# serves the static site
@serve:
    #!/usr/bin/env sh
    interfaces=$(ifconfig -l | tr ' ' '\n' | grep '^en')
    base_url=$(
      if test "$(uname -s)" == "Darwin"
      then
        for interface in $interfaces; do
            ip_address=$(ipconfig getifaddr $interface 2>/dev/null)

            if [ -n "$ip_address" ]; then
                echo "$ip_address"
                exit 0
            fi
        done
      else
        hostname --ip-address
      fi
    )
    hugo server \
      --baseURL=http://${base_url} \
      --bind=0.0.0.0 \
      --source=src/website \
      --buildDrafts \
      --disableFastRender \
      --navigateToChanged

@serve-linux:
    hugo server \
      --baseURL=http://localhost \
      --source=website \
      --buildDrafts \
      --disableFastRender \
      --navigateToChanged

# installs dependencies
@install:
    brew install hugo
    git submodule update --init --recursive

# run the entire build
check +args="":
    rm -rf src/website/public
    ./gradlew check

# run the entire build
versions +args="":
    ./gradlew refreshVersions

# run the entire build
rerun +args="":
    rm -rf src/website/public
    ./gradlew clean check --rerun-tasks
