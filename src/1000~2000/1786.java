import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public final class Main {
    private static int[] buildPrefix(final String pattern) {
        int m = pattern.length();
        int[] pi = new int[m];
        int j = 0;
        for (int i = 1; i < m; ++i) {
            while (j > 0 && pattern.charAt(i) != pattern.charAt(j)) {
                j = pi[j - 1];
            }
            if (pattern.charAt(i) == pattern.charAt(j)) {
                j += 1;
                pi[i] = j;
            }
        }
        return pi;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String text = br.readLine();
        String pattern = br.readLine();

        int n = text.length();
        int m = pattern.length();

        int[] pi = buildPrefix(pattern);

        StringBuilder outPositions = new StringBuilder();
        int matchCount = 0;

        int j = 0;
        for (int i = 0; i < n; ++i) {
            while (j > 0 && text.charAt(i) != pattern.charAt(j)) {
                j = pi[j - 1];
            }
            if (text.charAt(i) == pattern.charAt(j)) {
                if (j == m - 1) {
                    matchCount += 1;
                    if (outPositions.length() > 0) {
                        outPositions.append(' ');
                    }
                    outPositions.append(i - m + 2);
                    j = pi[j];
                } else {
                    j += 1;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(matchCount).append('\n');
        if (matchCount > 0) {
            sb.append(outPositions);
        }
        System.out.print(sb.toString());
    }
}
