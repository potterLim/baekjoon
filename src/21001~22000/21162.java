import java.io.BufferedInputStream;
import java.io.IOException;
import java.lang.StringBuilder;

public class Main {
    private static final class FastScanner {
        private final BufferedInputStream input;
        private final byte[] buffer;
        private int bufferSize;
        private int bufferIndex;

        public FastScanner() {
            this.input = new BufferedInputStream(System.in);
            this.buffer = new byte[1 << 16];
            this.bufferSize = 0;
            this.bufferIndex = 0;
        }

        private int readByte() throws IOException {
            if (this.bufferIndex >= this.bufferSize) {
                this.bufferSize = this.input.read(this.buffer);
                this.bufferIndex = 0;
                if (this.bufferSize <= 0) {
                    return -1;
                }
            }

            int value = this.buffer[this.bufferIndex];
            ++this.bufferIndex;
            return value;
        }

        public int nextInt() throws IOException {
            int c = this.readByte();
            while (c <= 32) {
                c = this.readByte();
            }

            int sign = 1;
            if (c == '-') {
                sign = -1;
                c = this.readByte();
            }

            int value = 0;
            while (c > 32) {
                value = value * 10 + (c - '0');
                c = this.readByte();
            }

            return value * sign;
        }
    }

    private static void countingSortByKey(int[] source, int[] dest, int[] rank, int offset, int maxRank, int length) {
        int bucketSize = maxRank + 1;
        int[] count = new int[bucketSize + 1];

        int i = 0;
        while (i < length) {
            int index = source[i];
            int keyIndex = index + offset;
            int key = 0;

            if (keyIndex < length) {
                key = rank[keyIndex];
            }

            ++count[key];
            ++i;
        }

        i = 1;
        while (i < count.length) {
            count[i] += count[i - 1];
            ++i;
        }

        i = length - 1;
        while (i >= 0) {
            int index = source[i];
            int keyIndex = index + offset;
            int key = 0;

            if (keyIndex < length) {
                key = rank[keyIndex];
            }

            --count[key];
            dest[count[key]] = index;

            --i;
        }
    }

    private static int[] buildSuffixArray(int[] values, int alphabetMax) {
        int length = values.length;

        int[] suffixArray = new int[length];
        int[] suffixArrayTmp = new int[length];
        int[] rank = new int[length];
        int[] newRank = new int[length];

        int i = 0;
        while (i < length) {
            suffixArray[i] = i;
            rank[i] = values[i] + 1;
            ++i;
        }

        int maxRank = alphabetMax + 1;

        int step = 1;
        while (step < length) {
            countingSortByKey(suffixArray, suffixArrayTmp, rank, step, maxRank, length);
            countingSortByKey(suffixArrayTmp, suffixArray, rank, 0, maxRank, length);

            int currentRank = 1;
            newRank[suffixArray[0]] = 1;

            i = 1;
            while (i < length) {
                int prev = suffixArray[i - 1];
                int curr = suffixArray[i];

                int prevA = rank[prev];
                int currA = rank[curr];

                int prevB = 0;
                int currB = 0;

                int prevBIndex = prev + step;
                if (prevBIndex < length) {
                    prevB = rank[prevBIndex];
                }

                int currBIndex = curr + step;
                if (currBIndex < length) {
                    currB = rank[currBIndex];
                }

                if (prevA != currA || prevB != currB) {
                    ++currentRank;
                }

                newRank[curr] = currentRank;
                ++i;
            }

            i = 0;
            while (i < length) {
                rank[i] = newRank[i];
                ++i;
            }

            maxRank = currentRank;
            if (maxRank == length) {
                break;
            }

            step <<= 1;
        }

        return suffixArray;
    }

    public static void main(String[] args) throws Exception {
        FastScanner scanner = new FastScanner();

        int n = scanner.nextInt();
        int k = scanner.nextInt();

        int[] original = new int[n];

        int i = 0;
        while (i < n) {
            original[i] = scanner.nextInt();
            ++i;
        }

        int[] reversed = new int[n];

        i = 0;
        while (i < n) {
            reversed[i] = original[n - 1 - i];
            ++i;
        }

        int doubledLength = n + n;
        int[] doubled = new int[doubledLength];

        i = 0;
        while (i < n) {
            doubled[i] = reversed[i];
            doubled[i + n] = reversed[i];
            ++i;
        }

        int maxValue = 0;

        i = 0;
        while (i < n) {
            if (reversed[i] > maxValue) {
                maxValue = reversed[i];
            }
            ++i;
        }

        int[] suffixArray = buildSuffixArray(doubled, maxValue);

        int targetStart = -1;
        int found = 0;

        i = 0;
        while (i < suffixArray.length) {
            int start = suffixArray[i];

            if (start > 0 && start < n) {
                ++found;
                if (found == k) {
                    targetStart = start;
                    break;
                }
            }

            ++i;
        }

        StringBuilder output = new StringBuilder();

        i = 0;
        while (i < n) {
            if (i > 0) {
                output.append(' ');
            }
            output.append(doubled[targetStart + i]);
            ++i;
        }

        System.out.print(output.toString());
    }
}