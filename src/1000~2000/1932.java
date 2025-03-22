import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[][] triangle;
        triangle = new int[n][n];

        for (int i = 0; i < n; ++i) {
            for (int j = 0; j <= i; ++j) {
                triangle[i][j] = scanner.nextInt();
            }
        }
        scanner.close();

        int[][] dp;
        dp = new int[n][n];
        dp[0][0] = triangle[0][0];

        for (int i = 1; i < n; ++i) {
            for (int j = 0; j <= i; ++j) {
                if (j == 0) {
                    dp[i][j] = dp[i - 1][j] + triangle[i][j];
                } else {
                    if (j == i) {
                        dp[i][j] = dp[i - 1][j - 1] + triangle[i][j];
                    } else {
                        dp[i][j] = Math.max(dp[i - 1][j - 1], dp[i - 1][j]) + triangle[i][j];
                    }
                }
            }
        }

        int answer;
        answer = 0;

        for (int j = 0; j < n; ++j) {
            if (dp[n - 1][j] > answer) {
                answer = dp[n - 1][j];
            }
        }

        System.out.println(answer);
    }
}