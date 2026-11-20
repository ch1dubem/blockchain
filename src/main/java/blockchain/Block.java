package blockchain;

import java.util.Objects;

public final class Block {
    private final String data;
    private final String previousHash;
    private final long timestamp;
    private String hash;
    private int nonce;

    public Block(String data, String previousHash) {
        this(data, previousHash, System.currentTimeMillis());
    }

    Block(String data, String previousHash, long timestamp) {
        this.data = Objects.requireNonNull(data, "data");
        this.previousHash = Objects.requireNonNull(previousHash, "previousHash");
        this.timestamp = timestamp;
        this.hash = calculateHash();
    }

    public String calculateHash() {
        return BlockUtils.applySha256(previousHash + timestamp + nonce + data);
    }

    public String getData() {
        return data;
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getHash() {
        return hash;
    }

    public int getNonce() {
        return nonce;
    }

    public void mineBlock(int difficulty) {
        if (difficulty < 0 || difficulty > 64) {
            throw new IllegalArgumentException("Difficulty must be between 0 and 64");
        }

        String target = "0".repeat(difficulty);
        hash = calculateHash();
        while (!hash.startsWith(target)) {
            if (nonce == Integer.MAX_VALUE) {
                throw new IllegalStateException("Nonce space exhausted before finding a valid hash");
            }
            nonce++;
            hash = calculateHash();
        }
    }
}
