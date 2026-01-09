import java.util.Scanner;

public class Main {
    private static final int INF = 1_000_000_000;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        String start = scanner.next();
        String target = scanner.next();

        int[][] dp = new int[n + 1][10];
        for (int i = 0; i <= n; ++i) {
            for (int acc = 0; acc < 10; ++acc) {
                dp[i][acc] = INF;
            }
        }

        dp[0][0] = 0;

        for (int i = 0; i < n; ++i) {
            int s = start.charAt(i) - '0';
            int t = target.charAt(i) - '0';

            for (int acc = 0; acc < 10; ++acc) {
                int baseCost = dp[i][acc];
                if (baseCost == INF) {
                    continue;
                }

                int cur = (s + acc) % 10;

                int left = (t - cur + 10) % 10;
                int accAfterLeft = (acc + left) % 10;
                dp[i + 1][accAfterLeft] = Math.min(dp[i + 1][accAfterLeft], baseCost + left);

                int right = (cur - t + 10) % 10;
                dp[i + 1][acc] = Math.min(dp[i + 1][acc], baseCost + right);
            }
        }

        int answer = INF;
        for (int acc = 0; acc < 10; ++acc) {
            answer = Math.min(answer, dp[n][acc]);
        }

        System.out.println(answer);
        scanner.close();
    }
}