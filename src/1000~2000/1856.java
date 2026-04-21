import java.util.Scanner;

public class Main {
    private static final int INF = 1_000_000_000;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int wordCount = scanner.nextInt();
        int textLength = scanner.nextInt();

        char[] text = scanner.next().toCharArray();
        char[][] words = new char[wordCount][];

        for (int i = 0; i < wordCount; ++i) {
            words[i] = scanner.next().toCharArray();
        }

        scanner.close();

        int[] dp = new int[textLength + 1];
        for (int i = 0; i <= textLength; ++i) {
            dp[i] = INF;
        }

        dp[0] = 0;

        for (int i = 0; i < textLength; ++i) {
            if (dp[i] == INF) {
                continue;
            }

            if (dp[i] + 1 < dp[i + 1]) {
                dp[i + 1] = dp[i] + 1;
            }

            for (int j = 0; j < wordCount; ++j) {
                char[] word = words[j];
                int wordIndex = 0;
                int textIndex = i;

                while (textIndex < textLength && wordIndex < word.length) {
                    if (text[textIndex] == word[wordIndex]) {
                        ++wordIndex;
                    }

                    ++textIndex;
                }

                if (wordIndex == word.length) {
                    int deleteCount = (textIndex - i) - word.length;
                    int nextCost = dp[i] + deleteCount;

                    if (nextCost < dp[textIndex]) {
                        dp[textIndex] = nextCost;
                    }
                }
            }
        }

        System.out.print(dp[textLength]);
    }
}