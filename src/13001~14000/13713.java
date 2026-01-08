import java.io.BufferedInputStream;
import java.io.IOException;

public class Main {

    private static final class FastScanner {
        private final BufferedInputStream inputStream = new BufferedInputStream(System.in);
        private final byte[] buffer = new byte[1 << 16];
        private int bufferSize = 0;
        private int bufferIndex = 0;

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

        String next() throws IOException {
            int c = readByte();
            while (c <= 32 && c != -1) {
                c = readByte();
            }
            if (c == -1) {
                return null;
            }

            StringBuilder token = new StringBuilder();
            while (c > 32 && c != -1) {
                token.append((char) c);
                c = readByte();
            }
            return token.toString();
        }

        int nextInt() throws IOException {
            int c = readByte();
            while (c <= 32 && c != -1) {
                c = readByte();
            }

            int sign = 1;
            if (c == '-') {
                sign = -1;
                c = readByte();
            }

            int value = 0;
            while (c > 32 && c != -1) {
                value = value * 10 + (c - '0');
                c = readByte();
            }

            return value * sign;
        }
    }

    private static int min(int a, int b) {
        if (a < b) {
            return a;
        }
        return b;
    }

    private static int[] buildZArray(char[] text) {
        int length = text.length;
        int[] zValue = new int[length];

        zValue[0] = length;

        int left = 0;
        int right = 0;

        int i = 1;
        while (i < length) {
            int matchLength = 0;

            if (i <= right) {
                int mirroredIndex = i - left;
                matchLength = min(right - i + 1, zValue[mirroredIndex]);
            }

            while (i + matchLength < length && text[matchLength] == text[i + matchLength]) {
                ++matchLength;
            }

            zValue[i] = matchLength;

            int newRight = i + matchLength - 1;
            if (newRight > right) {
                left = i;
                right = newRight;
            }

            ++i;
        }

        return zValue;
    }

    public static void main(String[] args) throws Exception {
        FastScanner scanner = new FastScanner();

        String originalString = scanner.next();
        int length = originalString.length();

        char[] reversedString = new char[length];
        int i = 0;
        while (i < length) {
            reversedString[i] = originalString.charAt(length - 1 - i);
            ++i;
        }

        int[] zValue = buildZArray(reversedString);

        int queryCount = scanner.nextInt();
        StringBuilder output = new StringBuilder();

        i = 0;
        while (i < queryCount) {
            int prefixEndIndex = scanner.nextInt();
            int startIndexInReversed = length - prefixEndIndex;
            output.append(zValue[startIndexInReversed]).append('\n');
            ++i;
        }

        System.out.print(output.toString());
    }
}