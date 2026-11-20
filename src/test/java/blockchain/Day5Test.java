package blockchain;

import java.util.List;

public final class Day5Test {
    public static void main(String[] args) {
        acceptsMinedChainAtItsDifficulty();
        rejectsUnminedBlock();
        rejectsInvalidValidationDifficulty();
        System.out.println("Day 5 checks passed");
    }

    private static void acceptsMinedChainAtItsDifficulty() {
        int difficulty = BlockUtils.DIFFICULTY_EASY;
        List<Block> chain = MiningDemo.createMinedChain(4, difficulty);
        check(BlockUtils.isChainValid(chain, difficulty), "mined chain meets its proof-of-work target");
        check(!BlockUtils.isChainValid(chain, difficulty + 1), "chain does not claim a harder target");
    }

    private static void rejectsUnminedBlock() {
        Block block = null;
        long timestamp = 1L;
        while (block == null || block.getHash().startsWith("00")) {
            block = new Block("unmined", "0", timestamp++);
        }
        check(!BlockUtils.isChainValid(List.of(block), BlockUtils.DIFFICULTY_EASY),
                "a valid hash without the proof-of-work prefix is rejected");
    }

    private static void rejectsInvalidValidationDifficulty() {
        List<Block> chain = MiningDemo.createMinedChain(1, BlockUtils.DIFFICULTY_EASY);
        check(!BlockUtils.isChainValid(chain, -1), "negative difficulty is rejected");
        check(!BlockUtils.isChainValid(chain, 65), "difficulty above hash length is rejected");
    }

    private static void check(boolean condition, String message) {
        if (!condition) {
            throw new AssertionError(message);
        }
    }
}
