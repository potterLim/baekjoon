import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

final class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine().trim();
        int n = s.length();
        int[][] dp = new int[n][n];

        for (int i = 0; i < n; ++i) {
            dp[i][i] = 1;
        }

        for (int len = 2; len <= n; ++len) {
            for (int i = 0; i + len - 1 < n; ++i) {
                int j = i + len - 1;
                int best = len;

                for (int k = i; k < j; ++k) {
                    int v = dp[i][k] + dp[k + 1][j];
                    if (v < best) {
                        best = v;
                    }
                }

                for (int p = 1; p <= len / 2; ++p) {
                    if (len % p != 0) {
                        continue;
                    }
                    boolean rep = true;
                    for (int k = i + p; k <= j; ++k) {
                        if (s.charAt(k) != s.charAt(i + (k - i) % p)) {
                            rep = false;
                            break;
                        }
                    }
                    if (!rep) {
                        continue;
                    }
                    int times = len / p;
                    if (times >= 2) {
                        int t = times, d = 0;
                        while (t > 0) {
                            t /= 10;
                            ++d;
                        }
                        int v = d + 2 + dp[i][i + p - 1];
                        if (v < best) {
                            best = v;
                        }
                    }
                }

                dp[i][j] = best;
            }
        }

        System.out.println(dp[0][n - 1]);
    }
}
