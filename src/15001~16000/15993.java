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

        long[] odd = new long[maxN + 1];
        long[] even = new long[maxN + 1];

        even[0] = 1;

        for (int n = 1; n <= maxN; n++) {
            long oddValue = 0;
            long evenValue = 0;

            if (n - 1 >= 0) {
                oddValue += even[n - 1];
                evenValue += odd[n - 1];
            }

            if (n - 2 >= 0) {
                oddValue += even[n - 2];
                evenValue += odd[n - 2];
            }

            if (n - 3 >= 0) {
                oddValue += even[n - 3];
                evenValue += odd[n - 3];
            }

            odd[n] = oddValue % MOD;
            even[n] = evenValue % MOD;
        }

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < testCaseCount; i++) {
            int n = ns[i];
            builder.append(odd[n]).append(' ').append(even[n]).append('\n');
        }

        System.out.print(builder);
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
