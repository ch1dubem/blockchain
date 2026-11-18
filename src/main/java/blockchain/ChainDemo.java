package blockchain;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
import java.util.List;

public final class ChainDemo {
    private ChainDemo() {
    }

    public static List<Block> createExampleChain() {
        List<Block> chain = new ArrayList<>();
        Block first = new Block("My First Block Chain", "0");
        chain.add(first);
        Block second = new Block("My Second Block Chain", first.getHash());
        chain.add(second);
        Block third = new Block("My Third Block Chain", second.getHash());
        chain.add(third);
        return chain;
    }

    public static String toPrettyJson(List<Block> chain) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(chain);
    }

    public static void main(String[] args) {
        List<Block> chain = createExampleChain();
        System.out.println("Chain is valid: " + BlockUtils.isChainValid(chain));
        System.out.println(toPrettyJson(chain));
    }
}
