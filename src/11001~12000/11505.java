import java.util.Scanner;

public class Main {
    private static final long MOD = 1_000_000_007L;

    private static long[] segmentTree;
    private static int leafStartIndex;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int elementCount = scanner.nextInt();
        int updateCount = scanner.nextInt();
        int queryCount = scanner.nextInt();

        int treeSize = 1;
        while (treeSize < elementCount) {
            treeSize <<= 1;
        }

        leafStartIndex = treeSize;
        segmentTree = new long[treeSize << 1];

        for (int i = 0; i < (treeSize << 1); ++i) {
            segmentTree[i] = 1L;
        }

        for (int i = 0; i < elementCount; ++i) {
            long value = scanner.nextLong();
            segmentTree[leafStartIndex + i] = value % MOD;
        }

        for (int i = leafStartIndex - 1; i > 0; --i) {
            segmentTree[i] = (segmentTree[i << 1] * segmentTree[(i << 1) + 1]) % MOD;
        }

        StringBuilder resultBuilder = new StringBuilder();

        int totalOperations = updateCount + queryCount;

        for (int i = 0; i < totalOperations; ++i) {
            int operationType = scanner.nextInt();
            int leftIndex = scanner.nextInt();
            int rightValue = scanner.nextInt();

            if (operationType == 1) {
                updateValue(leftIndex - 1, rightValue);
            } else {
                long product = queryProduct(leftIndex - 1, rightValue - 1);
                resultBuilder.append(product).append('\n');
            }
        }

        System.out.print(resultBuilder.toString());
    }

    private static void updateValue(int index, int newValue) {
        int treeIndex = leafStartIndex + index;
        segmentTree[treeIndex] = newValue % MOD;

        treeIndex >>= 1;
        while (treeIndex > 0) {
            segmentTree[treeIndex] = (segmentTree[treeIndex << 1] * segmentTree[(treeIndex << 1) + 1]) % MOD;
            treeIndex >>= 1;
        }
    }

    private static long queryProduct(int left, int right) {
        int leftIndex = leafStartIndex + left;
        int rightIndex = leafStartIndex + right;

        long result = 1L;

        while (leftIndex <= rightIndex) {
            if ((leftIndex & 1) == 1) {
                result = (result * segmentTree[leftIndex]) % MOD;
                ++leftIndex;
            }

            if ((rightIndex & 1) == 0) {
                result = (result * segmentTree[rightIndex]) % MOD;
                --rightIndex;
            }

            leftIndex >>= 1;
            rightIndex >>= 1;
        }

        return result;
    }
}