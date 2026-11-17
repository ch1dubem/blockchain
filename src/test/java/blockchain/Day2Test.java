package blockchain;

import java.util.List;

public final class Day2Test {
    public static void main(String[] args) {
        List<Block> chain = ChainDemo.createExampleChain();
        contains(3, chain.size(), "example chain length");
        equal("0", chain.get(0).getPreviousHash(), "genesis parent hash");
        equal(chain.get(0).getHash(), chain.get(1).getPreviousHash(), "second block parent hash");
        equal(chain.get(1).getHash(), chain.get(2).getPreviousHash(), "third block parent hash");

        String json = ChainDemo.toPrettyJson(chain);
        contains(true, json.contains("\n"), "pretty output contains line breaks");
        contains(true, json.contains("\"previousHash\""), "JSON includes parent hashes");
        contains(true, json.contains("\"timestamp\""), "JSON includes timestamps");
        contains(true, json.contains("\"hash\""), "JSON includes block hashes");
        contains(3, count(json, "\"data\""), "JSON includes all three blocks");
        System.out.println("Day 2 checks passed");
    }

    private static int count(String input, String fragment) {
        int count = 0;
        int offset = 0;
        while ((offset = input.indexOf(fragment, offset)) >= 0) {
            count++;
            offset += fragment.length();
        }
        return count;
    }

    private static void equal(Object expected, Object actual, String message) {
        if (!expected.equals(actual)) {
            throw new AssertionError(message + ": expected " + expected + ", got " + actual);
        }
    }

    private static void contains(boolean expected, boolean actual, String message) {
        equal(expected, actual, message);
    }

    private static void contains(int expected, int actual, String message) {
        equal(expected, actual, message);
    }
}
