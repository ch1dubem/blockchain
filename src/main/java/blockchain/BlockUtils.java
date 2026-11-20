package blockchain;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;
import java.util.List;

public final class BlockUtils {
    public static final int DIFFICULTY_EASY = 2;
    public static final int DIFFICULTY_NORMAL = 4;
    public static final int DIFFICULTY_HARD = 6;
    private BlockUtils() {
    }

    public static String applySha256(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(input.getBytes(StandardCharsets.UTF_8));
            return HexFormat.of().formatHex(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("SHA-256 is not available", e);
        }
    }

    public static boolean isChainValid(List<Block> chain) {
        if (chain == null || chain.isEmpty()) {
            return false;
        }

        Block first = chain.get(0);
        if (first == null || !"0".equals(first.getPreviousHash())) {
            return false;
        }

        for (int i = 0; i < chain.size(); i++) {
            Block current = chain.get(i);
            if (current == null || !current.getHash().equals(current.calculateHash())) {
                return false;
            }
            if (i > 0 && !current.getPreviousHash().equals(chain.get(i - 1).getHash())) {
                return false;
            }
        }
        return true;
    }
}
