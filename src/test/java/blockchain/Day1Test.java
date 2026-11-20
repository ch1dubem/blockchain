package blockchain;

public final class Day1Test {
    public static void main(String[] args) {
        if (args.length == 0 || args[0].equals("sha256")) {
            sha256MatchesKnownDigest();
        }
        if (args.length == 0 || args[0].equals("block")) {
            blockHashIncludesItsFields();
        }
        if (args.length == 0 || args[0].equals("tamper")) {
            changingBlockContentChangesItsHash();
        }
        if (args.length == 0 || args[0].equals("link")) {
            nextBlockLinksToPreviousHash();
        }
        System.out.println(args.length == 0 ? "Day 1 checks passed" : args[0] + " check passed");
    }

    private static void sha256MatchesKnownDigest() {
        equal("e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855",
                BlockUtils.applySha256(""), "SHA-256 empty input");
    }

    private static void blockHashIncludesItsFields() {
        Block block = new Block("first payload", "0", 1234L);
        equal(BlockUtils.applySha256("012340first payload"), block.getHash(), "calculated block hash");
        equal(block.getHash(), block.calculateHash(), "stored and recalculated hashes");
        equal(64, block.getHash().length(), "SHA-256 hex length");
    }

    private static void changingBlockContentChangesItsHash() {
        Block original = new Block("first payload", "0", 1234L);
        Block changedData = new Block("changed payload", "0", 1234L);
        Block changedParent = new Block("first payload", "different parent", 1234L);
        check(!original.getHash().equals(changedData.getHash()), "data must affect hash");
        check(!original.getHash().equals(changedParent.getHash()), "previous hash must affect hash");
    }

    private static void nextBlockLinksToPreviousHash() {
        Block first = new Block("first", "0", 1L);
        Block second = new Block("second", first.getHash(), 2L);
        equal(first.getHash(), second.getPreviousHash(), "second block's previous hash");
    }

    private static void equal(Object expected, Object actual, String message) {
        if (!expected.equals(actual)) {
            throw new AssertionError(message + ": expected " + expected + ", got " + actual);
        }
    }

    private static void check(boolean condition, String message) {
        if (!condition) {
            throw new AssertionError(message);
        }
    }
}
