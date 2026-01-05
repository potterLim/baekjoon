import java.util.Scanner;

public class Main {
    private static final int MAX_BIT = 60;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int itemCount = scanner.nextInt();

        long[] positions = new long[itemCount];
        int i = 0;
        while (i < itemCount) {
            positions[i] = scanner.nextLong();
            ++i;
        }

        int maxNodes = itemCount * (MAX_BIT + 1) + 2;

        int[] leftChild = new int[maxNodes];
        int[] rightChild = new int[maxNodes];
        int[] prefixCount = new int[maxNodes];

        int rootIndex = 1;
        int nodeCount = 1;

        i = 0;
        while (i < itemCount) {
            long value = positions[i];
            int currentNode = rootIndex;

            int bitIndex = 0;
            while (bitIndex <= MAX_BIT) {
                int bit = 0;
                if (((value >> bitIndex) & 1L) != 0L) {
                    bit = 1;
                }

                int nextNode = 0;
                if (bit == 0) {
                    nextNode = leftChild[currentNode];
                } else {
                    nextNode = rightChild[currentNode];
                }

                if (nextNode == 0) {
                    ++nodeCount;
                    nextNode = nodeCount;

                    if (bit == 0) {
                        leftChild[currentNode] = nextNode;
                    } else {
                        rightChild[currentNode] = nextNode;
                    }
                }

                currentNode = nextNode;
                ++prefixCount[currentNode];

                ++bitIndex;
            }

            ++i;
        }

        int stackCapacity = nodeCount + 5;

        int[] nodeStack = new int[stackCapacity];
        int[] depthStack = new int[stackCapacity];
        long[] minStack = new long[stackCapacity];

        int top = 0;

        nodeStack[top] = rootIndex;
        depthStack[top] = 0;
        minStack[top] = Long.MAX_VALUE;
        ++top;

        long best = 0L;

        while (top > 0) {
            --top;

            int node = nodeStack[top];
            int depth = depthStack[top];
            long currentMin = minStack[top];

            if (depth > 0) {
                long candidate = (long) prefixCount[node] + (long) depth - 1L;
                if (candidate < currentMin) {
                    currentMin = candidate;
                }
            }

            if (depth == MAX_BIT + 1) {
                if (currentMin > best) {
                    best = currentMin;
                }
                continue;
            }

            int left = leftChild[node];
            if (left != 0) {
                nodeStack[top] = left;
                depthStack[top] = depth + 1;
                minStack[top] = currentMin;
                ++top;
            }

            int right = rightChild[node];
            if (right != 0) {
                nodeStack[top] = right;
                depthStack[top] = depth + 1;
                minStack[top] = currentMin;
                ++top;
            }
        }

        System.out.print(best);
    }
}