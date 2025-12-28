import java.util.Scanner;

public class Main {
    private static final int MOD = 1_000_000_003;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int colorCount = scanner.nextInt();
        int pickCount = scanner.nextInt();

        if (pickCount > colorCount / 2) {
            System.out.print(0);
            return;
        }

        if (pickCount == 1) {
            System.out.print(colorCount % MOD);
            return;
        }

        int[][] lineDp = buildLineDp(colorCount, pickCount);

        long excludeFirst = lineDp[colorCount - 1][pickCount];
        long includeFirst = lineDp[colorCount - 3][pickCount - 1];

        long answer = (excludeFirst + includeFirst) % MOD;
        System.out.print(answer);
    }

    private static int[][] buildLineDp(int maxColors, int maxPicks) {
        int[][] dp = new int[maxColors + 1][maxPicks + 1];

        dp[0][0] = 1;

        if (maxColors >= 1) {
            dp[1][0] = 1;
            if (maxPicks >= 1) {
                dp[1][1] = 1;
            }
        }

        for (int i = 2; i <= maxColors; i++) {
            dp[i][0] = 1;

            if (maxPicks >= 1) {
                dp[i][1] = i;
            }

            int maxJ = Math.min(maxPicks, (i + 1) / 2);
            for (int j = 2; j <= maxJ; j++) {
                long value = dp[i - 1][j];
                value += dp[i - 2][j - 1];
                dp[i][j] = (int) (value % MOD);
            }
        }

        return dp;
    }
}