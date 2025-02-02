#!/usr/bin/env bash

__file="$(readlink -f "$0")"
__dirname="$(dirname "$__file")"

cd "$__dirname/.."

if ! command -v git &> /dev/null; then
  echo "ERROR: git is not installed" >&2
  exit 1
fi

if ! git status &> /dev/null; then
  echo "ERROR: is not a git repo" >&2
  exit 1
fi

GITHUB_REPO_URL="$(git config --get remote.origin.url | sed -e 's/^git@github.com:/https:\/\/github.com\//' -e 's/\.git$//')"
GITHUB_REPO_NAME="$(echo "$GITHUB_REPO_URL" | sed -e 's/^https:\/\/github.com\///')"

LAST_TAG="$(git describe --tags --abbrev=0)"
LAST_TAG_COMMIT="$(git rev-list -n 1 "$LAST_TAG")"
HEAD_COMMIT="$(git rev-parse HEAD)"

if [ "$CURRENT_TAG" ]; then
  LAST_TAG="$CURRENT_TAG"
  LAST_TAG_COMMIT="$(git rev-list -n 1 "$LAST_TAG")"
fi

if [ "$HEAD_COMMIT" = "$LAST_TAG_COMMIT" ]; then
  # 获取倒数第二个标签
  LAST_TAG="$(git describe --tags --abbrev=0 "${LAST_TAG}~")"
  LAST_TAG_COMMIT="$(git rev-list -n 1 "$LAST_TAG")"
fi

LOG_FORMAT="- [%h](${GITHUB_REPO_URL/commit/%H}) %s"
GIT_LOG="$(git log --format="$LOG_FORMAT" --no-merges "${LAST_TAG_COMMIT}..HEAD")"

cat << EOF
# 更改

$GIT_LOG

EOF

exit 0