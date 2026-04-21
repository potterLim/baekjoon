import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int k = scanner.nextInt();

        boolean[][] blockedRight = new boolean[n][m + 1];
        boolean[][] blockedUp = new boolean[n + 1][m];

        for (int i = 0; i < k; ++i) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            int c = scanner.nextInt();
            int d = scanner.nextInt();

            if (a == c) {
                int y = Math.min(b, d);
                blockedUp[a][y] = true;
            } else {
                int x = Math.min(a, c);
                int y = b;
                blockedRight[x][y] = true;
            }
        }

        scanner.close();

        long[][] dp = new long[n + 1][m + 1];
        dp[0][0] = 1L;

        for (int i = 0; i <= n; ++i) {
            for (int j = 0; j <= m; ++j) {
                if (i < n && !blockedRight[i][j]) {
                    dp[i + 1][j] += dp[i][j];
                }

                if (j < m && !blockedUp[i][j]) {
                    dp[i][j + 1] += dp[i][j];
                }
            }
        }

        System.out.print(dp[n][m]);
    }
}