import java.io.BufferedInputStream;
import java.lang.StringBuilder;

public class Main {
    private static int[] values;
    private static int[] minTree;
    private static int[] maxTree;

    public static void main(String[] args) throws Exception {
        FastScanner scanner = new FastScanner();

        int testCaseCount = scanner.nextInt();
        StringBuilder outputBuilder = new StringBuilder();

        for (int tc = 0; tc < testCaseCount; ++tc) {
            int dvdCount = scanner.nextInt();
            int eventCount = scanner.nextInt();

            values = new int[dvdCount];

            for (int i = 0; i < dvdCount; ++i) {
                values[i] = i;
            }

            int treeSize = dvdCount * 4;
            minTree = new int[treeSize];
            maxTree = new int[treeSize];

            buildTree(1, 0, dvdCount - 1);

            for (int i = 0; i < eventCount; ++i) {
                int queryType = scanner.nextInt();
                int left = scanner.nextInt();
                int right = scanner.nextInt();

                if (queryType == 0) {
                    swapPositions(left, right, dvdCount);
                } else {
                    long result = queryRange(1, 0, dvdCount - 1, left, right);
                    int minValue = (int) (result >>> 32);
                    int maxValue = (int) result;

                    if (minValue == left && maxValue == right) {
                        outputBuilder.append("YES\n");
                    } else {
                        outputBuilder.append("NO\n");
                    }
                }
            }
        }

        System.out.print(outputBuilder.toString());
    }

    private static void swapPositions(int indexA, int indexB, int dvdCount) {
        int temp = values[indexA];
        values[indexA] = values[indexB];
        values[indexB] = temp;

        updateTree(1, 0, dvdCount - 1, indexA, values[indexA]);
        updateTree(1, 0, dvdCount - 1, indexB, values[indexB]);
    }

    private static void buildTree(int node, int start, int end) {
        if (start == end) {
            int value = values[start];
            minTree[node] = value;
            maxTree[node] = value;
            return;
        }

        int mid = (start + end) >>> 1;

        buildTree(node << 1, start, mid);
        buildTree((node << 1) + 1, mid + 1, end);

        minTree[node] = Math.min(minTree[node << 1], minTree[(node << 1) + 1]);
        maxTree[node] = Math.max(maxTree[node << 1], maxTree[(node << 1) + 1]);
    }

    private static void updateTree(int node, int start, int end, int index, int newValue) {
        if (start == end) {
            minTree[node] = newValue;
            maxTree[node] = newValue;
            return;
        }

        int mid = (start + end) >>> 1;

        if (index <= mid) {
            updateTree(node << 1, start, mid, index, newValue);
        } else {
            updateTree((node << 1) + 1, mid + 1, end, index, newValue);
        }

        minTree[node] = Math.min(minTree[node << 1], minTree[(node << 1) + 1]);
        maxTree[node] = Math.max(maxTree[node << 1], maxTree[(node << 1) + 1]);
    }

    private static long queryRange(int node, int start, int end, int left, int right) {
        if (right < start || end < left) {
            int minValue = Integer.MAX_VALUE;
            int maxValue = Integer.MIN_VALUE;
            return pack(minValue, maxValue);
        }

        if (left <= start && end <= right) {
            return pack(minTree[node], maxTree[node]);
        }

        int mid = (start + end) >>> 1;

        long leftResult = queryRange(node << 1, start, mid, left, right);
        long rightResult = queryRange((node << 1) + 1, mid + 1, end, left, right);

        int leftMin = (int) (leftResult >>> 32);
        int leftMax = (int) leftResult;

        int rightMin = (int) (rightResult >>> 32);
        int rightMax = (int) rightResult;

        int mergedMin = Math.min(leftMin, rightMin);
        int mergedMax = Math.max(leftMax, rightMax);

        return pack(mergedMin, mergedMax);
    }

    private static long pack(int minValue, int maxValue) {
        long high = ((long) minValue) << 32;
        long low = maxValue & 0xffffffffL;
        return high | low;
    }

    private static final class FastScanner {
        private final BufferedInputStream input;
        private final byte[] buffer;
        private int bufferSize;
        private int bufferIndex;

        public FastScanner() {
            input = new BufferedInputStream(System.in);
            buffer = new byte[1 << 16];
            bufferSize = 0;
            bufferIndex = 0;
        }

        public int nextInt() throws Exception {
            int value = 0;
            int sign = 1;
            int ch = read();

            while (ch <= ' ' && ch != -1) {
                ch = read();
            }

            if (ch == '-') {
                sign = -1;
                ch = read();
            }

            while (ch > ' ') {
                value = value * 10 + (ch - '0');
                ch = read();
            }

            return value * sign;
        }

        private int read() throws Exception {
            if (bufferIndex >= bufferSize) {
                bufferSize = input.read(buffer);
                bufferIndex = 0;

                if (bufferSize <= 0) {
                    return -1;
                }
            }

            return buffer[bufferIndex++];
        }
    }
}