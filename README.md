# Blockchain / Cryptocurrency

## Description of the Project

This Java project builds a small blockchain from linked blocks. Each block stores data, a timestamp, its previous block's hash, and a SHA-256 hash of its contents. A nonce lets the demo mine blocks until their hashes meet a proof-of-work target. The chain validator checks the hashes, links, and mining difficulty.

## User Stories

- As a learner, I want to create linked blocks so I can see how each block depends on its predecessor.
- As a learner, I want to validate block hashes and links so I can detect changes to the chain.
- As a learner, I want to mine blocks at a chosen difficulty so I can see proof of work in action.
- As a learner, I want to inspect the chain as JSON so I can see each block's stored values.

## Setup

### Prerequisites

- Java 17 or later
- A Gson JAR

Set `GSON_JAR` to the location of Gson, then run the saved checks and both demos from the project directory:

```sh
GSON_JAR=/path/to/gson.jar scripts/test.sh
```

The script compiles into a temporary directory. It runs the Day 1–5 checks, then prints a linked sample chain and a mined chain as formatted JSON.

## Technologies Used

- Java 17
- SHA-256 through `java.security.MessageDigest`
- Gson for JSON output
- `javac` and shell scripts for compiling and running the saved checks

## Demo

`ChainDemo` prints an ordinary linked chain and its validity. `MiningDemo` mines four blocks at easy difficulty, prints each mined hash, validates the required proof-of-work prefix, and displays the blocks as JSON.

## Future Work

- Store blocks on disk and reload the chain between runs
- Add transactions and digital signatures
- Make the mining difficulty configurable from the command line

## Resources

- [Creating Your First Blockchain with Java](https://medium.com/programmers-blockchain/create-simple-blockchain-java-tutorial-from-scratch-6eeed3cb03fa)
- [Gson User Guide](https://github.com/google/gson/blob/main/UserGuide.md)
- [Java 17 MessageDigest documentation](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/security/MessageDigest.html)
