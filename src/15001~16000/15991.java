import java.io.BufferedInputStream;
import java.io.IOException;

public class Main {
    private static final long MOD = 1_000_000_009L;

    public static void main(String[] args) throws Exception {
        FastScanner scanner = new FastScanner();

        int testCaseCount = scanner.readInt();
        int[] ns = new int[testCaseCount];

        int maxN = 0;
        for (int i = 0; i < testCaseCount; i++) {
            int n = scanner.readInt();
            ns[i] = n;

            if (n > maxN) {
                maxN = n;
            }
        }

        int maxHalf = maxN / 2;
        long[] f = buildCompositions(maxHalf);

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < testCaseCount; i++) {
            int n = ns[i];
            long answer = 0;

            if ((n & 1) == 0) {
                answer = (answer + f[n / 2]) % MOD;
            }

            for (int mid = 1; mid <= 3; mid++) {
                int remaining = n - mid;
                if (remaining < 0) {
                    continue;
                }

                if ((remaining & 1) != 0) {
                    continue;
                }

                answer += f[remaining / 2];
                answer %= MOD;
            }

            builder.append(answer).append('\n');
        }

        System.out.print(builder);
    }

    private static long[] buildCompositions(int maxSum) {
        long[] f = new long[maxSum + 1];
        f[0] = 1;

        for (int i = 1; i <= maxSum; i++) {
            long value = 0;

            value += f[i - 1];
            if (i >= 2) {
                value += f[i - 2];
            }
            if (i >= 3) {
                value += f[i - 3];
            }

            f[i] = value % MOD;
        }

        return f;
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
