import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    private static void countingSort(int[] suffixArray, int[] sortedSuffixArray, int[] rank, int offset, int maxRank, int length) {
        int[] count = new int[maxRank + 1];

        for (int i = 0; i < length; ++i) {
            int index = suffixArray[i] + offset;
            int key = 0;
            if (index < length) {
                key = rank[index];
            }
            ++count[key];
        }

        int sum = 0;
        for (int i = 0; i <= maxRank; ++i) {
            int c = count[i];
            count[i] = sum;
            sum += c;
        }

        for (int i = 0; i < length; ++i) {
            int suffix = suffixArray[i];
            int index = suffix + offset;
            int key = 0;
            if (index < length) {
                key = rank[index];
            }
            sortedSuffixArray[count[key]] = suffix;
            ++count[key];
        }
    }

    private static int[] buildSuffixArray(char[] text) {
        int length = text.length;

        int[] suffixArray = new int[length];
        int[] sortedSuffixArray = new int[length];

        int[] rank = new int[length];
        int[] nextRank = new int[length];

        for (int i = 0; i < length; ++i) {
            suffixArray[i] = i;
            rank[i] = (text[i] - 'a') + 1;
        }

        int maxRank = 26;

        for (int k = 1; k < length; k <<= 1) {
            countingSort(suffixArray, sortedSuffixArray, rank, k, maxRank, length);
            countingSort(sortedSuffixArray, suffixArray, rank, 0, maxRank, length);

            int classes = 1;
            nextRank[suffixArray[0]] = 1;

            for (int i = 1; i < length; ++i) {
                int prev = suffixArray[i - 1];
                int cur = suffixArray[i];

                int prevFirst = rank[prev];
                int curFirst = rank[cur];

                int prevSecond = 0;
                int curSecond = 0;

                int prevIndex = prev + k;
                int curIndex = cur + k;

                if (prevIndex < length) {
                    prevSecond = rank[prevIndex];
                }
                if (curIndex < length) {
                    curSecond = rank[curIndex];
                }

                if (prevFirst != curFirst || prevSecond != curSecond) {
                    ++classes;
                }

                nextRank[cur] = classes;
            }

            int[] temp = rank;
            rank = nextRank;
            nextRank = temp;

            maxRank = classes;
            if (classes == length) {
                break;
            }
        }

        return suffixArray;
    }

    private static int[] buildLcpArray(char[] text, int[] suffixArray) {
        int length = text.length;

        int[] inverseSuffixArray = new int[length];
        for (int i = 0; i < length; ++i) {
            inverseSuffixArray[suffixArray[i]] = i;
        }

        int[] lcp = new int[length];

        int commonLength = 0;
        for (int i = 0; i < length; ++i) {
            int pos = inverseSuffixArray[i];
            if (pos == 0) {
                lcp[pos] = 0;
                continue;
            }

            int j = suffixArray[pos - 1];
            while (i + commonLength < length && j + commonLength < length && text[i + commonLength] == text[j + commonLength]) {
                ++commonLength;
            }

            lcp[pos] = commonLength;

            if (commonLength > 0) {
                --commonLength;
            }
        }

        return lcp;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s = reader.readLine();

        char[] text = s.toCharArray();
        int length = text.length;

        int[] suffixArray = buildSuffixArray(text);
        int[] lcp = buildLcpArray(text, suffixArray);

        StringBuilder output = new StringBuilder(length * 6);

        for (int i = 0; i < length; ++i) {
            if (i > 0) {
                output.append(' ');
            }
            output.append(suffixArray[i] + 1);
        }
        output.append('\n');

        output.append('x');
        for (int i = 1; i < length; ++i) {
            output.append(' ');
            output.append(lcp[i]);
        }

        System.out.print(output.toString());
    }
}