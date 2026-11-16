package blockchain;

import java.util.Objects;

public final class Block {
    private final String data;
    private final String previousHash;
    private final long timestamp;
    private final String hash;

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
        return BlockUtils.applySha256(previousHash + timestamp + data);
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
}
