import java.io.BufferedInputStream;
import java.io.IOException;

public class Main {
    private static final long MOD = 1_000_000_009L;

    public static void main(String[] args) throws Exception {
        FastScanner scanner = new FastScanner();

        int testCaseCount = scanner.readInt();
        int[] ns = new int[testCaseCount];
        int[] ms = new int[testCaseCount];

        int maxN = 0;
        int maxM = 0;

        for (int i = 0; i < testCaseCount; i++) {
            int n = scanner.readInt();
            int m = scanner.readInt();

            ns[i] = n;
            ms[i] = m;

            if (n > maxN) {
                maxN = n;
            }

            if (m > maxM) {
                maxM = m;
            }
        }

        long[][] dp = buildDp(maxN, maxM);

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < testCaseCount; i++) {
            int n = ns[i];
            int m = ms[i];

            builder.append(dp[n][m]).append('\n');
        }

        System.out.print(builder);
    }

    private static long[][] buildDp(int maxN, int maxM) {
        long[][] dp = new long[maxN + 1][maxM + 1];
        dp[0][0] = 1;

        for (int count = 1; count <= maxM; count++) {
            for (int sum = 1; sum <= maxN; sum++) {
                long value = 0;

                if (sum - 1 >= 0) {
                    value += dp[sum - 1][count - 1];
                }

                if (sum - 2 >= 0) {
                    value += dp[sum - 2][count - 1];
                }

                if (sum - 3 >= 0) {
                    value += dp[sum - 3][count - 1];
                }

                dp[sum][count] = value % MOD;
            }
        }

        return dp;
    }

    private static final class FastScanner {
        private static final int BUFFER_SIZE = 1 << 16;

        private final BufferedInputStream inputStream;
        private final byte[] buffer;

        private int bufferIndex;
        private int bufferSize;

        public FastScanner() {
            this.inputStream = new BufferedInputStream(System.in, BUFFER_SIZE);
            this.buffer = new byte[BUFFER_SIZE];
        }

        public int readInt() throws IOException {
            int c = readNonSpace();
            int sign = 1;

            if (c == '-') {
                sign = -1;
                c = readByte();
            }

            int value = 0;
            while (c > ' ') {
                value = (value * 10) + (c - '0');
                c = readByte();
            }

            return value * sign;
        }

        private int readNonSpace() throws IOException {
            int c = readByte();
            while (c <= ' ' && c != -1) {
                c = readByte();
            }

            return c;
        }

        private int readByte() throws IOException {
            if (bufferIndex >= bufferSize) {
                bufferSize = inputStream.read(buffer, 0, buffer.length);
                bufferIndex = 0;

                if (bufferSize <= 0) {
                    return -1;
                }
            }

            return buffer[bufferIndex++];
        }
    }
}
