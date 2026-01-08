import java.io.BufferedInputStream;
import java.io.IOException;

public class Main {
    private static final class FastScanner {
        private final BufferedInputStream inputStream = new BufferedInputStream(System.in);
        private final byte[] buffer = new byte[1 << 16];
        private int bufferSize = 0;
        private int bufferPos = 0;

        private int readByte() throws IOException {
            if (bufferPos >= bufferSize) {
                bufferSize = inputStream.read(buffer);
                bufferPos = 0;
                if (bufferSize <= 0) {
                    return -1;
                }
            }
            return buffer[bufferPos++];
        }

        String next() throws IOException {
            int c;
            do {
                c = readByte();
                if (c == -1) {
                    return null;
                }
            } while (c <= ' ');

            StringBuilder token = new StringBuilder();
            while (c > ' ') {
                token.append((char) c);
                c = readByte();
                if (c == -1) {
                    break;
                }
            }
            return token.toString();
        }
    }

    private static void countingSort(int[] sourceSuffixArray, int[] targetSuffixArray, int[] rank, int offset, int length, int maxRank) {
        int[] count = new int[maxRank + 1];

        for (int i = 0; i < length; ++i) {
            int suffixStart = sourceSuffixArray[i];
            int key = 0;
            int keyIndex = suffixStart + offset;
            if (keyIndex < length) {
                key = rank[keyIndex];
            }
            ++count[key];
        }

        int prefixSum = 0;
        for (int i = 0; i <= maxRank; ++i) {
            int value = count[i];
            count[i] = prefixSum;
            prefixSum += value;
        }

        for (int i = 0; i < length; ++i) {
            int suffixStart = sourceSuffixArray[i];
            int key = 0;
            int keyIndex = suffixStart + offset;
            if (keyIndex < length) {
                key = rank[keyIndex];
            }
            targetSuffixArray[count[key]++] = suffixStart;
        }
    }

    private static int[] buildSuffixArray(char[] text) {
        int length = text.length;

        int[] suffixArray = new int[length];
        int[] tempSuffixArray = new int[length];
        int[] rank = new int[length];
        int[] nextRank = new int[length];

        for (int i = 0; i < length; ++i) {
            suffixArray[i] = i;
            rank[i] = (text[i] - 'a') + 1;
        }

        int maxRank = 26;

        for (int step = 1; step < length; step <<= 1) {
            int rankUpperBound = maxRank;
            if (rankUpperBound < length) {
                rankUpperBound = length;
            }
            ++rankUpperBound;

            countingSort(suffixArray, tempSuffixArray, rank, step, length, rankUpperBound);
            countingSort(tempSuffixArray, suffixArray, rank, 0, length, rankUpperBound);

            int newRankValue = 1;
            nextRank[suffixArray[0]] = newRankValue;

            for (int i = 1; i < length; ++i) {
                int prev = suffixArray[i - 1];
                int curr = suffixArray[i];

                int prevFirst = rank[prev];
                int currFirst = rank[curr];

                int prevSecond = 0;
                int currSecond = 0;

                int prevSecondIndex = prev + step;
                int currSecondIndex = curr + step;

                if (prevSecondIndex < length) {
                    prevSecond = rank[prevSecondIndex];
                }
                if (currSecondIndex < length) {
                    currSecond = rank[currSecondIndex];
                }

                if (prevFirst != currFirst || prevSecond != currSecond) {
                    ++newRankValue;
                }

                nextRank[curr] = newRankValue;
            }

            int[] swap = rank;
            rank = nextRank;
            nextRank = swap;

            maxRank = newRankValue;
            if (maxRank == length) {
                break;
            }
        }

        return suffixArray;
    }

    private static int[] buildLcpArray(char[] text, int[] suffixArray) {
        int length = text.length;

        int[] inverseIndex = new int[length];
        int[] lcp = new int[length];

        for (int i = 0; i < length; ++i) {
            inverseIndex[suffixArray[i]] = i;
        }

        int common = 0;
        for (int i = 0; i < length; ++i) {
            int position = inverseIndex[i];
            if (position == 0) {
                continue;
            }

            int prevSuffixStart = suffixArray[position - 1];

            while (i + common < length && prevSuffixStart + common < length && text[i + common] == text[prevSuffixStart + common]) {
                ++common;
            }

            lcp[position] = common;
            if (common > 0) {
                --common;
            }
        }

        return lcp;
    }

    public static void main(String[] args) throws Exception {
        FastScanner scanner = new FastScanner();
        String s = scanner.next();
        if (s == null) {
            return;
        }

        char[] text = s.toCharArray();
        int length = text.length;

        int[] suffixArray = buildSuffixArray(text);
        int[] lcp = buildLcpArray(text, suffixArray);

        long totalSubstrings = (long) length * (long) (length + 1) / 2L;

        long lcpSum = 0L;
        for (int i = 0; i < length; ++i) {
            lcpSum += lcp[i];
        }

        long distinctSubstringCount = totalSubstrings - lcpSum;
        System.out.println(distinctSubstringCount);
    }
}