import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private static boolean isMatch(String s, int endExclusive, String pattern) {
        int length = pattern.length();
        int start = endExclusive - length;

        for (int i = 0; i < length; ++i) {
            if (s.charAt(start + i) != pattern.charAt(i)) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String s = scanner.next();
        int m = scanner.nextInt();

        Map<String, Integer> bestScoreMap = new HashMap<String, Integer>();

        for (int i = 0; i < m; ++i) {
            String a = scanner.next();
            int score = scanner.nextInt();

            if (bestScoreMap.containsKey(a)) {
                if (bestScoreMap.get(a) < score) {
                    bestScoreMap.put(a, score);
                }
            } else {
                bestScoreMap.put(a, score);
            }
        }

        ArrayList<String> patterns = new ArrayList<String>();
        ArrayList<Integer> scores = new ArrayList<Integer>();

        for (Map.Entry<String, Integer> entry : bestScoreMap.entrySet()) {
            patterns.add(entry.getKey());
            scores.add(entry.getValue());
        }

        int n = s.length();
        int[] dp = new int[n + 1];

        for (int i = 1; i <= n; ++i) {
            dp[i] = dp[i - 1] + 1;

            for (int j = 0; j < patterns.size(); ++j) {
                String pattern = patterns.get(j);
                int length = pattern.length();

                if (i < length) {
                    continue;
                }

                if (isMatch(s, i, pattern)) {
                    int candidate = dp[i - length] + scores.get(j);

                    if (candidate > dp[i]) {
                        dp[i] = candidate;
                    }
                }
            }
        }

        System.out.println(dp[n]);
        scanner.close();
    }
}