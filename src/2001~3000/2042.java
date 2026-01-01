import java.io.BufferedInputStream;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws Exception {
        FastInput input;
        StringBuilder output;

        input = new FastInput();
        output = new StringBuilder();

        int elementCount;
        int updateCount;
        int queryCount;

        elementCount = input.nextInt();
        updateCount = input.nextInt();
        queryCount = input.nextInt();

        FenwickTree fenwickTree;

        fenwickTree = new FenwickTree(elementCount);

        for (int i = 1; i <= elementCount; i++) {
            long value;
            value = input.nextLong();
            fenwickTree.add(i, value);
        }

        int operationCount;
        operationCount = updateCount + queryCount;

        for (int i = 0; i < operationCount; i++) {
            int operationType;
            int leftOrIndex;
            long rightOrValue;

            operationType = input.nextInt();
            leftOrIndex = input.nextInt();
            rightOrValue = input.nextLong();

            if (operationType == 1) {
                int index;
                long newValue;
                long currentValue;
                long delta;

                index = leftOrIndex;
                newValue = rightOrValue;

                currentValue = fenwickTree.getValue(index);
                delta = newValue - currentValue;
                fenwickTree.add(index, delta);
            } else {
                int left;
                int right;
                long rangeSum;

                left = leftOrIndex;
                right = (int) rightOrValue;

                if (left > right) {
                    int temp;
                    temp = left;
                    left = right;
                    right = temp;
                }

                rangeSum = fenwickTree.sum(right) - fenwickTree.sum(left - 1);
                output.append(rangeSum).append('\n');
            }
        }

        System.out.print(output.toString());
    }

    private static final class FenwickTree {
        private final int size;
        private final long[] tree;
        private final long[] values;

        public FenwickTree(int size) {
            this.size = size;
            this.tree = new long[size + 1];
            this.values = new long[size + 1];
        }

        public void add(int index, long delta) {
            int i;

            values[index] += delta;

            i = index;
            while (i <= size) {
                tree[i] += delta;
                i += (i & -i);
            }
        }

        public long sum(int index) {
            long result;
            int i;

            result = 0L;
            i = index;

            while (i > 0) {
                result += tree[i];
                i -= (i & -i);
            }

            return result;
        }

        public long getValue(int index) {
            return values[index];
        }
    }

    private static final class FastInput {
        private final BufferedInputStream inputStream;
        private final byte[] buffer;
        private int bufferSize;
        private int bufferIndex;

        public FastInput() {
            this.inputStream = new BufferedInputStream(System.in);
            this.buffer = new byte[1 << 16];
            this.bufferSize = 0;
            this.bufferIndex = 0;
        }

        public int nextInt() throws IOException {
            int c;
            int sign;
            int value;

            c = read();
            while (c <= ' ' && c != -1) {
                c = read();
            }

            sign = 1;
            if (c == '-') {
                sign = -1;
                c = read();
            }

            value = 0;
            while (c > ' ') {
                value = value * 10 + (c - '0');
                c = read();
            }

            return value * sign;
        }

        public long nextLong() throws IOException {
            int c;
            int sign;
            long value;

            c = read();
            while (c <= ' ' && c != -1) {
                c = read();
            }

            sign = 1;
            if (c == '-') {
                sign = -1;
                c = read();
            }

            value = 0L;
            while (c > ' ') {
                value = value * 10L + (long) (c - '0');
                c = read();
            }

            return value * (long) sign;
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