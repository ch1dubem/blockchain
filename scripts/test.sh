#!/bin/sh
set -eu

SCRIPT_DIR=$(CDPATH= cd -- "$(dirname -- "$0")/.." && pwd)
cd "$SCRIPT_DIR"

if [ -z "${GSON_JAR:-}" ]; then
    printf '%s\n' 'Set GSON_JAR to the path of the Gson JAR.' >&2
    exit 2
fi

OUT_DIR=$(mktemp -d "${TMPDIR:-/tmp}/blockchain-test.XXXXXX")
trap 'rm -rf "$OUT_DIR"' EXIT
javac --release 17 -cp "$GSON_JAR" -d "$OUT_DIR" \
    src/main/java/blockchain/BlockUtils.java \
    src/main/java/blockchain/Block.java \
    src/main/java/blockchain/ChainDemo.java \
    src/test/java/blockchain/Day1Test.java \
    src/test/java/blockchain/Day2Test.java \
    src/test/java/blockchain/Day3Test.java
java -cp "$OUT_DIR:$GSON_JAR" blockchain.Day1Test
java -cp "$OUT_DIR:$GSON_JAR" blockchain.Day2Test
java -cp "$OUT_DIR:$GSON_JAR" blockchain.Day3Test
java -cp "$OUT_DIR:$GSON_JAR" blockchain.ChainDemo
