package blockchain;

import java.util.List;

public final class Day3Test {
    public static void main(String[] args) {
        acceptsValidChain();
        rejectsChangedBlockData();
        rejectsWrongParentHash();
        rejectsMissingGenesis();
        System.out.println("Day 3 checks passed");
    }

    private static void acceptsValidChain() {
        check(BlockUtils.isChainValid(ChainDemo.createExampleChain()), "valid example chain");
    }

    private static void rejectsChangedBlockData() {
        Block genesis = new Block("first", "0", 100L);
        Block originalSecond = new Block("second", genesis.getHash(), 200L);
        Block third = new Block("third", originalSecond.getHash(), 300L);
        Block replacementSecond = new Block("changed second", genesis.getHash(), 200L);
        List<Block> changed = List.of(genesis, replacementSecond, third);
        check(!BlockUtils.isChainValid(changed), "changed block leaves stale parent link");
    }

    private static void rejectsWrongParentHash() {
        Block genesis = new Block("first", "0", 100L);
        Block disconnected = new Block("second", "not the first block hash", 200L);
        check(!BlockUtils.isChainValid(List.of(genesis, disconnected)), "wrong parent hash");
    }

    private static void rejectsMissingGenesis() {
        check(!BlockUtils.isChainValid(List.of()), "empty chain");
        check(!BlockUtils.isChainValid(null), "null chain");
        check(!BlockUtils.isChainValid(List.of(new Block("first", "wrong", 100L))), "invalid genesis parent");
    }

    private static void check(boolean condition, String message) {
        if (!condition) {
            throw new AssertionError(message);
        }
    }
}
