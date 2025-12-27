import java.io.BufferedInputStream;
import java.io.IOException;

public class Main {
    private static final long MOD = 1_000_000_009L;

    public static void main(String[] args) throws Exception {
        FastScanner scanner = new FastScanner();

        int testCaseCount = scanner.readInt();
        int[] values = new int[testCaseCount];

        int maxValue = 0;
        for (int i = 0; i < testCaseCount; i++) {
            int value = scanner.readInt();
            values[i] = value;

            if (value > maxValue) {
                maxValue = value;
            }
        }

        long[][] dp = buildDp(maxValue);

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < testCaseCount; i++) {
            int n = values[i];
            long answer = (dp[n][1] + dp[n][2] + dp[n][3]) % MOD;
            builder.append(answer).append('\n');
        }

        System.out.print(builder);
    }

    private static long[][] buildDp(int maxValue) {
        long[][] dp = new long[maxValue + 1][4];

        if (maxValue >= 1) {
            dp[1][1] = 1;
        }

        if (maxValue >= 2) {
            dp[2][2] = 1;
        }

        if (maxValue >= 3) {
            dp[3][1] = 1;
            dp[3][2] = 1;
            dp[3][3] = 1;
        }

        for (int n = 4; n <= maxValue; n++) {
            dp[n][1] = (dp[n - 1][2] + dp[n - 1][3]) % MOD;
            dp[n][2] = (dp[n - 2][1] + dp[n - 2][3]) % MOD;
            dp[n][3] = (dp[n - 3][1] + dp[n - 3][2]) % MOD;
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