package blockchain;

import java.util.List;

public final class Day4Test {
    public static void main(String[] args) {
        minesHashWithRequestedPrefix();
        rejectsInvalidDifficulty();
        minesLinkedChain();
        System.out.println("Day 4 checks passed");
    }

    private static void minesHashWithRequestedPrefix() {
        Block block = new Block("Day 4 mining test", "0", 12345L);
        check(!block.getHash().startsWith("00"), "fixed starting hash requires mining");
        block.mineBlock(BlockUtils.DIFFICULTY_EASY);
        check(block.getNonce() > 0, "mining increments the nonce");
        check(block.getHash().startsWith("00"), "mined hash meets difficulty");
        equal(block.calculateHash(), block.getHash(), "stored mined hash matches block fields");
    }

    private static void rejectsInvalidDifficulty() {
        expectIllegalArgument(() -> new Block("low", "0").mineBlock(-1));
        expectIllegalArgument(() -> new Block("high", "0").mineBlock(65));
    }

    private static void minesLinkedChain() {
        List<Block> chain = MiningDemo.createMinedChain(4, BlockUtils.DIFFICULTY_EASY);
        equal(4, chain.size(), "mined chain length");
        for (Block block : chain) {
            check(block.getHash().startsWith("00"), "every block meets difficulty");
        }
        check(BlockUtils.isChainValid(chain), "mined blocks remain linked and valid");
    }

    private static void expectIllegalArgument(Runnable action) {
        try {
            action.run();
            throw new AssertionError("Expected IllegalArgumentException");
        } catch (IllegalArgumentException expected) {
            // Expected for invalid mining difficulty.
        }
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
