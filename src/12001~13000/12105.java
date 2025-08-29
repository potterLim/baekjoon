import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

final class Main {
    private static final int MOD = 1_000_000_007;

    private static int[] buildPrefixFunction(String pattern) {
        int length = pattern.length();
        int[] prefix = new int[length];
        for (int i = 1, matched = 0; i < length; ) {
            if (pattern.charAt(i) == pattern.charAt(matched)) {
                prefix[i++] = ++matched;
            } else if (matched > 0) {
                matched = prefix[matched - 1];
            } else {
                prefix[i++] = 0;
            }
        }
        return prefix;
    }

    private static ArrayList<Integer> findPatternPositions(String text, String pattern) {
        ArrayList<Integer> positions = new ArrayList<>();
        if (pattern.length() > text.length()) {
            return positions;
        }
        int[] prefix = buildPrefixFunction(pattern);
        int n = text.length();
        int m = pattern.length();
        for (int i = 0, j = 0; i < n; ) {
            if (text.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
                if (j == m) {
                    int startIndex = i - m + 1;
                    positions.add(startIndex);
                    j = prefix[j - 1];
                }
            } else if (j > 0) {
                j = prefix[j - 1];
            } else {
                i++;
            }
        }
        return positions;
    }

    private static int primeExponentCapped(int value, int prime, int cap) {
        int exponent = 0;
        int x = value;
        while (x % prime == 0) {
            exponent++;
            if (exponent == cap) {
                return cap;
            }
            x /= prime;
        }
        return exponent;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String pattern = br.readLine().trim();
        String text = br.readLine().trim();

        ArrayList<Integer> startPositions = findPatternPositions(text, pattern);
        if (startPositions.isEmpty()) {
            System.out.println(0);
            return;
        }

        int[][][][] dp = new int[4][3][2][2];
        dp[0][0][0][0] = 1;

        for (int startPos : startPositions) {
            int exp2 = primeExponentCapped(startPos, 2, 3);
            int exp3 = primeExponentCapped(startPos, 3, 2);
            int exp5 = primeExponentCapped(startPos, 5, 1);
            int exp7 = primeExponentCapped(startPos, 7, 1);

            int[][][][] nextDp = new int[4][3][2][2];

            for (int e2 = 0; e2 <= 3; ++e2) {
                for (int e3 = 0; e3 <= 2; ++e3) {
                    for (int e5 = 0; e5 <= 1; ++e5) {
                        for (int e7 = 0; e7 <= 1; ++e7) {
                            int ways = dp[e2][e3][e5][e7];
                            if (ways == 0) {
                                continue;
                            }
                            nextDp[e2][e3][e5][e7] = (nextDp[e2][e3][e5][e7] + ways) % MOD;
                            int newE2 = Math.min(3, e2 + exp2);
                            int newE3 = Math.min(2, e3 + exp3);
                            int newE5 = Math.min(1, e5 + exp5);
                            int newE7 = Math.min(1, e7 + exp7);
                            nextDp[newE2][newE3][newE5][newE7] =
                                    (nextDp[newE2][newE3][newE5][newE7] + ways) % MOD;
                        }
                    }
                }
            }
            dp = nextDp;
        }

        System.out.println(dp[3][2][1][1]);
    }
}
