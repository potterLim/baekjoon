import java.util.Scanner;
import java.lang.StringBuilder;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder outputBuilder = new StringBuilder();

        int size = scanner.nextInt();
        long[] initialValues = new long[size + 1];

        for (int i = 1; i <= size; ++i) {
            initialValues[i] = scanner.nextLong();
        }

        FenwickTree fenwickTree = new FenwickTree(size);

        int queryCount = scanner.nextInt();

        for (int i = 0; i < queryCount; ++i) {
            int type = scanner.nextInt();

            if (type == 1) {
                int left = scanner.nextInt();
                int right = scanner.nextInt();
                long delta = scanner.nextLong();

                fenwickTree.add(left, delta);

                if (right + 1 <= size) {
                    fenwickTree.add(right + 1, -delta);
                }
            } else {
                int index = scanner.nextInt();
                long value = initialValues[index] + fenwickTree.sum(index);

                outputBuilder.append(value);
                outputBuilder.append('\n');
            }
        }

        System.out.print(outputBuilder.toString());
    }

    private static final class FenwickTree {
        private final long[] tree;
        private final int size;

        public FenwickTree(int size) {
            this.size = size;
            this.tree = new long[size + 1];
        }

        public void add(int index, long delta) {
            int current = index;

            while (current <= size) {
                tree[current] += delta;
                current += current & -current;
            }
        }

        public long sum(int index) {
            int current = index;
            long result = 0L;

            while (current > 0) {
                result += tree[current];
                current -= current & -current;
            }

            return result;
        }
    }
}