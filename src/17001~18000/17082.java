import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.TreeMap;

public class Main {
    private static final int IMPOSSIBLE_VALUE = 1000000000;

    public static void main(String[] args) throws Exception {
        InputReader inputReader;
        PrintWriter outputWriter;

        inputReader = new InputReader();
        outputWriter = new PrintWriter(System.out);

        int length;
        int pairCount;
        int queryCount;

        length = inputReader.nextInt();
        pairCount = inputReader.nextInt();
        queryCount = inputReader.nextInt();

        int[] valueAtIndex;
        valueAtIndex = new int[length + 2];

        for (int i = 1; i <= length; i++) {
            valueAtIndex[i] = inputReader.nextInt();
        }

        int[] leftPocket;
        leftPocket = new int[pairCount + 1];

        int[] rightPocket;
        rightPocket = new int[pairCount + 1];

        for (int i = 1; i <= pairCount; i++) {
            leftPocket[i] = inputReader.nextInt();
        }

        for (int i = 1; i <= pairCount; i++) {
            rightPocket[i] = inputReader.nextInt();
        }

        int[] coverageCount;
        coverageCount = new int[length + 3];

        for (int i = 1; i <= pairCount; i++) {
            int leftIndex;
            int rightIndexPlusOne;

            leftIndex = leftPocket[i];
            rightIndexPlusOne = rightPocket[i] + 1;

            coverageCount[leftIndex]++;
            if (rightIndexPlusOne <= length + 1) {
                coverageCount[rightIndexPlusOne]--;
            }
        }

        int openIntervals;
        openIntervals = 0;

        boolean impossible;
        impossible = false;

        for (int i = 1; i <= length; i++) {
            openIntervals += coverageCount[i];
            if (openIntervals < 0) {
                impossible = true;
                break;
            }
        }

        if (impossible) {
            for (int i = 0; i < queryCount; i++) {
                outputWriter.println(IMPOSSIBLE_VALUE);
            }
            outputWriter.flush();
            return;
        }

        TreeMap<Integer, Integer> multiset;
        multiset = new TreeMap<Integer, Integer>();

        openIntervals = 0;
        for (int i = 1; i <= length; i++) {
            openIntervals += coverageCount[i];
            coverageCount[i] = openIntervals;

            if (openIntervals > 0) {
                addToMultiset(multiset, valueAtIndex[i]);
            }
        }

        for (int i = 0; i < queryCount; i++) {
            int firstIndex;
            int secondIndex;

            firstIndex = inputReader.nextInt();
            secondIndex = inputReader.nextInt();

            int tempValue;
            tempValue = valueAtIndex[firstIndex];
            valueAtIndex[firstIndex] = valueAtIndex[secondIndex];
            valueAtIndex[secondIndex] = tempValue;

            boolean firstCovered;
            boolean secondCovered;

            firstCovered = coverageCount[firstIndex] > 0;
            secondCovered = coverageCount[secondIndex] > 0;

            if (firstCovered != secondCovered) {
                int coveredIndex;
                int uncoveredIndex;

                if (firstCovered) {
                    coveredIndex = firstIndex;
                    uncoveredIndex = secondIndex;
                } else {
                    coveredIndex = secondIndex;
                    uncoveredIndex = firstIndex;
                }

                removeFromMultiset(multiset, valueAtIndex[uncoveredIndex]);
                addToMultiset(multiset, valueAtIndex[coveredIndex]);
            }

            outputWriter.println(multiset.lastKey());
        }

        outputWriter.flush();
    }

    private static void addToMultiset(TreeMap<Integer, Integer> multiset, int value) {
        Integer count;

        count = multiset.get(value);
        if (count == null) {
            multiset.put(value, 1);
            return;
        }

        multiset.put(value, count + 1);
    }

    private static void removeFromMultiset(TreeMap<Integer, Integer> multiset, int value) {
        int count;

        count = multiset.get(value);
        if (count == 1) {
            multiset.remove(value);
            return;
        }

        multiset.put(value, count - 1);
    }

    private static final class InputReader {
        private final BufferedInputStream inputStream;

        private final byte[] buffer;
        private int bufferSize;
        private int bufferIndex;

        public InputReader() {
            this.inputStream = new BufferedInputStream(System.in);
            this.buffer = new byte[1 << 16];
            this.bufferSize = 0;
            this.bufferIndex = 0;
        }

        public int nextInt() throws IOException {
            int c;

            c = read();
            while (c <= ' ' && c != -1) {
                c = read();
            }

            int sign;
            sign = 1;

            if (c == '-') {
                sign = -1;
                c = read();
            }

            int value;
            value = 0;

            while (c > ' ') {
                value = value * 10 + (c - '0');
                c = read();
            }

            return value * sign;
        }

        private int read() throws IOException {
            if (bufferIndex >= bufferSize) {
                bufferSize = inputStream.read(buffer);
                bufferIndex = 0;

                if (bufferSize <= 0) {
                    return -1;
                }
            }

            return buffer[bufferIndex++];
        }
    }
}