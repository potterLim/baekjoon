import java.io.BufferedInputStream;
import java.util.Arrays;

public class Main {
    private static class FastScanner {
        private final BufferedInputStream mInputStream;
        private final byte[] mBuffer;
        private int mBufferIndex;
        private int mBufferSize;

        public FastScanner() {
            mInputStream = new BufferedInputStream(System.in);
            mBuffer = new byte[1 << 16];

            mBufferSize = 0;
        }

        private int read() {
            if (mBufferIndex >= mBufferSize) {
                try {
                    mBufferSize = mInputStream.read(mBuffer);
                    mBufferIndex = 0;
                } catch (Exception e) {
                    return -1;
                }

                if (mBufferSize <= 0) {
                    return -1;
                }
            }

            return mBuffer[mBufferIndex++];
        }

        public int nextInt() {
            int c = read();

            while (c <= ' ') {
                c = read();
            }

            int value = 0;

            while (c > ' ') {
                value = value * 10 + (c - '0');
                c = read();
            }

            return value;
        }
    }

    public static void main(String[] args) {
        FastScanner scanner = new FastScanner();

        int n = scanner.nextInt();
        long[] numbers = new long[n];

        for (int i = 0; i < n; ++i) {
            int value = scanner.nextInt();
            numbers[i] = ((long) value << 20) | i;
        }

        Arrays.sort(numbers);

        int maxMove = 0;
        int mask = (1 << 20) - 1;

        for (int i = 0; i < n; ++i) {
            int originalIndex = (int) (numbers[i] & mask);
            int move = originalIndex - i;

            if (move > maxMove) {
                maxMove = move;
            }
        }

        System.out.println(maxMove + 1);
    }
}