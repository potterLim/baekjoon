import java.util.Scanner;
import java.lang.StringBuilder;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int k = scanner.nextInt();

        FenwickTree fenwickTree = new FenwickTree(n);

        for (int i = 1; i <= n; ++i) {
            fenwickTree.add(i, 1);
        }

        StringBuilder outputBuilder = new StringBuilder();
        outputBuilder.append('<');

        int aliveCount = n;
        int currentIndex = 0;

        for (int removedCount = 0; removedCount < n; ++removedCount) {
            currentIndex = (currentIndex + k - 1) % aliveCount;
            int order = currentIndex + 1;
            int person = fenwickTree.findByOrder(order);

            fenwickTree.add(person, -1);
            --aliveCount;

            outputBuilder.append(person);
            if (removedCount != n - 1) {
                outputBuilder.append(", ");
            }
        }

        outputBuilder.append('>');
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