import java.util.Scanner;
import java.lang.StringBuilder;

public class Main {
    private static final int MAX_VALUE = 2000000;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder outputBuilder = new StringBuilder();

        int queryCount = scanner.nextInt();

        FenwickTree fenwickTree = new FenwickTree(MAX_VALUE);

        for (int i = 0; i < queryCount; ++i) {
            int type = scanner.nextInt();
            int value = scanner.nextInt();

            if (type == 1) {
                fenwickTree.add(value, 1);
            } else {
                int result = fenwickTree.findByOrder(value);
                outputBuilder.append(result);
                outputBuilder.append('\n');
                fenwickTree.add(result, -1);
            }
        }

        System.out.print(outputBuilder.toString());
    }

    private static final class FenwickTree {
        private final int[] tree;
        private final int size;

        public FenwickTree(int size) {
            this.size = size;
            this.tree = new int[size + 1];
        }

        public void add(int index, int delta) {
            int current = index;

            while (current <= size) {
                tree[current] += delta;
                current += current & -current;
            }
        }

        public int prefixSum(int index) {
            int current = index;
            int sum = 0;

            while (current > 0) {
                sum += tree[current];
                current -= current & -current;
            }

            return sum;
        }

        public int findByOrder(int order) {
            int index = 0;
            int bitMask = Integer.highestOneBit(size);

            while (bitMask > 0) {
                int nextIndex = index + bitMask;

                if (nextIndex <= size && tree[nextIndex] < order) {
                    order -= tree[nextIndex];
                    index = nextIndex;
                }

                bitMask >>= 1;
            }

            return index + 1;
        }
    }
}