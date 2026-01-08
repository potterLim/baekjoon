import java.io.BufferedInputStream;
import java.io.IOException;

public class Main {

    private static final class FastScanner {
        private final BufferedInputStream inputStream = new BufferedInputStream(System.in);
        private final byte[] buffer = new byte[1 << 16];
        private int bufferIndex = 0;
        private int bufferSize = 0;

        private int readByte() throws IOException {
            if (bufferIndex >= bufferSize) {
                bufferSize = inputStream.read(buffer);
                bufferIndex = 0;
                if (bufferSize <= 0) {
                    return -1;
                }
            }
            return buffer[bufferIndex++];
        }

        String nextString() throws IOException {
            int c = readByte();
            while (c <= 32 && c != -1) {
                c = readByte();
            }
            if (c == -1) {
                return null;
            }

            StringBuilder result = new StringBuilder();
            while (c > 32 && c != -1) {
                result.append((char) c);
                c = readByte();
            }
            return result.toString();
        }
    }

    private static int min(int a, int b) {
        if (a < b) {
            return a;
        }
        return b;
    }

    public static void main(String[] args) throws Exception {
        FastScanner scanner = new FastScanner();
        String inputString = scanner.nextString();
        if (inputString == null) {
            return;
        }

        int length = inputString.length();
        char[] characters = inputString.toCharArray();

        int[] oddPalindromeRadius = new int[length];
        int[] evenPalindromeRadius = new int[length];

        int left = 0;
        int right = -1;

        int i = 0;
        while (i < length) {
            int radius = 1;
            if (i <= right) {
                int mirrorIndex = left + right - i;
                radius = min(oddPalindromeRadius[mirrorIndex], right - i + 1);
            }

            while (i - radius >= 0 && i + radius < length && characters[i - radius] == characters[i + radius]) {
                ++radius;
            }

            oddPalindromeRadius[i] = radius;

            int farthest = i + radius - 1;
            if (farthest > right) {
                left = i - radius + 1;
                right = farthest;
            }

            ++i;
        }

        left = 0;
        right = -1;
        i = 0;

        while (i < length) {
            int radius = 0;
            if (i <= right) {
                int mirrorIndex = left + right - i + 1;
                radius = min(evenPalindromeRadius[mirrorIndex], right - i + 1);
            }

            while (i - radius - 1 >= 0 && i + radius < length && characters[i - radius - 1] == characters[i + radius]) {
                ++radius;
            }

            evenPalindromeRadius[i] = radius;

            int farthest = i + radius - 1;
            if (farthest > right) {
                left = i - radius;
                right = farthest;
            }

            ++i;
        }

        long palindromeCount = 0L;

        i = 0;
        while (i < length) {
            palindromeCount += oddPalindromeRadius[i];
            palindromeCount += evenPalindromeRadius[i];
            ++i;
        }

        System.out.print(palindromeCount);
    }
}