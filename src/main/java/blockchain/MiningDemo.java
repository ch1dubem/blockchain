package blockchain;

import com.google.gson.GsonBuilder;
import java.util.ArrayList;
import java.util.List;

public final class MiningDemo {
    private MiningDemo() {
    }

    public static List<Block> createMinedChain(int blockCount, int difficulty) {
        if (blockCount < 1) {
            throw new IllegalArgumentException("A chain must contain at least one block");
        }

        List<Block> chain = new ArrayList<>();
        for (int i = 0; i < blockCount; i++) {
            String previousHash = chain.isEmpty() ? "0" : chain.get(i - 1).getHash();
            Block block = new Block("Block " + i, previousHash);
            block.mineBlock(difficulty);
            chain.add(block);
        }
        return chain;
    }

    public static void main(String[] args) {
        int difficulty = BlockUtils.DIFFICULTY_EASY;
        List<Block> chain = createMinedChain(4, difficulty);
        for (Block block : chain) {
            System.out.println("Block mined: " + block.getHash());
        }
        System.out.println("Chain is valid: " + BlockUtils.isChainValid(chain, difficulty));
        String json = new GsonBuilder().setPrettyPrinting().create().toJson(chain);
        System.out.println(json);
    }
}
