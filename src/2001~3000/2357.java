import java.util.Scanner;

public class Main {
    private static int[] minTree;
    private static int[] maxTree;
    private static int leafStartIndex;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numberCount = scanner.nextInt();
        int queryCount = scanner.nextInt();

        int treeSize = 1;
        while (treeSize < numberCount) {
            treeSize <<= 1;
        }

        leafStartIndex = treeSize;

        minTree = new int[treeSize << 1];
        maxTree = new int[treeSize << 1];

        for (int i = 0; i < (treeSize << 1); ++i) {
            minTree[i] = Integer.MAX_VALUE;
            maxTree[i] = Integer.MIN_VALUE;
        }

        for (int i = 0; i < numberCount; ++i) {
            int value = scanner.nextInt();
            minTree[leafStartIndex + i] = value;
            maxTree[leafStartIndex + i] = value;
        }

        for (int i = leafStartIndex - 1; i > 0; --i) {
            minTree[i] = Math.min(minTree[i << 1], minTree[(i << 1) + 1]);
            maxTree[i] = Math.max(maxTree[i << 1], maxTree[(i << 1) + 1]);
        }

        StringBuilder outputBuilder = new StringBuilder();

        for (int i = 0; i < queryCount; ++i) {
            int left = scanner.nextInt() - 1;
            int right = scanner.nextInt() - 1;

            int minValue = queryMin(left, right);
            int maxValue = queryMax(left, right);

            outputBuilder.append(minValue).append(' ').append(maxValue).append('\n');
        }

        System.out.print(outputBuilder.toString());
    }

    private static int queryMin(int left, int right) {
        int leftIndex = leafStartIndex + left;
        int rightIndex = leafStartIndex + right;

        int result = Integer.MAX_VALUE;

        while (leftIndex <= rightIndex) {
            if ((leftIndex & 1) == 1) {
                result = Math.min(result, minTree[leftIndex]);
                ++leftIndex;
            }

            if ((rightIndex & 1) == 0) {
                result = Math.min(result, minTree[rightIndex]);
                --rightIndex;
            }

            leftIndex >>= 1;
            rightIndex >>= 1;
        }

        return result;
    }

    private static int queryMax(int left, int right) {
        int leftIndex = leafStartIndex + left;
        int rightIndex = leafStartIndex + right;

        int result = Integer.MIN_VALUE;

        while (leftIndex <= rightIndex) {
            if ((leftIndex & 1) == 1) {
                result = Math.max(result, maxTree[leftIndex]);
                ++leftIndex;
            }

            if ((rightIndex & 1) == 0) {
                result = Math.max(result, maxTree[rightIndex]);
                --rightIndex;
            }

            leftIndex >>= 1;
            rightIndex >>= 1;
        }

        return result;
    }
}