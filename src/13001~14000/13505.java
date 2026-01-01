import java.util.Scanner;

public class Main {
    private static final int MAX_BIT = 30;

    private static final class BitwiseTrie {
        private final int[] left;
        private final int[] right;
        private int nodeCount;

        public BitwiseTrie(int maxNumbers) {
            int maxNodes = (maxNumbers * (MAX_BIT + 1)) + 1;

            this.left = new int[maxNodes];
            this.right = new int[maxNodes];
            this.nodeCount = 1;
        }

        public void insert(int value) {
            int node = 1;

            for (int bit = MAX_BIT; bit >= 0; --bit) {
                int b = (value >> bit) & 1;

                if (b == 0) {
                    int next = this.left[node];
                    if (next == 0) {
                        next = ++this.nodeCount;
                        this.left[node] = next;
                    }
                    node = next;
                } else {
                    int next = this.right[node];
                    if (next == 0) {
                        next = ++this.nodeCount;
                        this.right[node] = next;
                    }
                    node = next;
                }
            }
        }

        public int queryMaxXor(int value) {
            int node = 1;
            int xorValue = 0;

            for (int bit = MAX_BIT; bit >= 0; --bit) {
                int b = (value >> bit) & 1;

                if (b == 0) {
                    int preferred = this.right[node];
                    if (preferred != 0) {
                        xorValue |= (1 << bit);
                        node = preferred;
                    } else {
                        node = this.left[node];
                    }
                } else {
                    int preferred = this.left[node];
                    if (preferred != 0) {
                        xorValue |= (1 << bit);
                        node = preferred;
                    } else {
                        node = this.right[node];
                    }
                }
            }

            return xorValue;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int count = scanner.nextInt();
        int[] numbers = new int[count];

        for (int i = 0; i < count; ++i) {
            numbers[i] = scanner.nextInt();
        }

        BitwiseTrie trie = new BitwiseTrie(count);

        trie.insert(numbers[0]);

        int bestXor = 0;

        for (int i = 1; i < count; ++i) {
            int candidate = trie.queryMaxXor(numbers[i]);
            if (candidate > bestXor) {
                bestXor = candidate;
            }
            trie.insert(numbers[i]);
        }

        System.out.print(bestXor);
    }
}