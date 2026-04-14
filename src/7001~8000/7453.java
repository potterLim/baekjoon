import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.Arrays;

public class Main {
    private static final class FastScanner {
        private final BufferedInputStream mInputStream = new BufferedInputStream(System.in);
        private final byte[] mBuffer = new byte[1 << 16];
        private int mBufferSize = 0;
        private int mBufferIndex = 0;

        public int nextInt() throws IOException {
            int currentByte = read();

            while (currentByte <= ' ') {
                currentByte = read();
            }

            int sign = 1;

            if (currentByte == '-') {
                sign = -1;
                currentByte = read();
            }

            int value = 0;

            while (currentByte > ' ') {
                value = value * 10 + currentByte - '0';
                currentByte = read();
            }

            return value * sign;
        }

        private int read() throws IOException {
            if (mBufferIndex >= mBufferSize) {
                mBufferSize = mInputStream.read(mBuffer);
                mBufferIndex = 0;
            }

            if (mBufferSize == -1) {
                return -1;
            }

            return mBuffer[mBufferIndex++];
        }
    }

    public static void main(String[] args) throws Exception {
        FastScanner scanner = new FastScanner();

        int n = scanner.nextInt();

        int[] a = new int[n];
        int[] b = new int[n];
        int[] c = new int[n];
        int[] d = new int[n];

        for (int i = 0; i < n; ++i) {
            a[i] = scanner.nextInt();
            b[i] = scanner.nextInt();
            c[i] = scanner.nextInt();
            d[i] = scanner.nextInt();
        }

        int pairCount = n * n;
        int[] abSums = new int[pairCount];
        int index = 0;

        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                abSums[index] = a[i] + b[j];
                ++index;
            }
        }

        Arrays.sort(abSums);

        long answer = 0L;

        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                int target = -(c[i] + d[j]);
                int lowerIndex = lowerBound(abSums, target);
                int upperIndex = upperBound(abSums, target);
                answer += upperIndex - lowerIndex;
            }
        }

        System.out.println(answer);
    }

    private static int lowerBound(int[] values, int target) {
        int left = 0;
        int right = values.length;

        while (left < right) {
            int middle = left + (right - left) / 2;

            if (values[middle] < target) {
                left = middle + 1;
            } else {
                right = middle;
            }
        }

        return left;
    }

    private static int upperBound(int[] values, int target) {
        int left = 0;
        int right = values.length;

        while (left < right) {
            int middle = left + (right - left) / 2;

            if (values[middle] <= target) {
                left = middle + 1;
            } else {
                right = middle;
            }
        }

        return left;
    }
}