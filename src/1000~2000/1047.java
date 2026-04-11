import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int treeCount = scanner.nextInt();

        int[] xCoordinatesByTree = new int[treeCount];
        int[] yCoordinatesByTree = new int[treeCount];
        int[] fenceLengthsByTree = new int[treeCount];

        int[] sortedXCoordinates = new int[treeCount];
        int[] sortedYCoordinates = new int[treeCount];

        for (int treeIndex = 0; treeIndex < treeCount; ++treeIndex) {
            int xCoordinate = scanner.nextInt();
            int yCoordinate = scanner.nextInt();
            int fenceLength = scanner.nextInt();

            xCoordinatesByTree[treeIndex] = xCoordinate;
            yCoordinatesByTree[treeIndex] = yCoordinate;
            fenceLengthsByTree[treeIndex] = fenceLength;

            sortedXCoordinates[treeIndex] = xCoordinate;
            sortedYCoordinates[treeIndex] = yCoordinate;
        }

        Arrays.sort(sortedXCoordinates);
        Arrays.sort(sortedYCoordinates);

        int minimumCutCount = treeCount;

        for (int leftIndex = 0; leftIndex < treeCount; ++leftIndex) {
            int leftX = sortedXCoordinates[leftIndex];

            for (int rightIndex = leftIndex; rightIndex < treeCount; ++rightIndex) {
                int rightX = sortedXCoordinates[rightIndex];

                for (int bottomIndex = 0; bottomIndex < treeCount; ++bottomIndex) {
                    int bottomY = sortedYCoordinates[bottomIndex];

                    for (int topIndex = bottomIndex; topIndex < treeCount; ++topIndex) {
                        int topY = sortedYCoordinates[topIndex];

                        long requiredFenceLength = 2L * ((long) (rightX - leftX) + (long) (topY - bottomY));

                        int cutCount = 0;
                        long collectedFenceLength = 0L;

                        int insideTreeCount = 0;
                        int[] insideFenceLengths = new int[treeCount];

                        for (int treeIndex = 0; treeIndex < treeCount; ++treeIndex) {
                            int xCoordinate = xCoordinatesByTree[treeIndex];
                            int yCoordinate = yCoordinatesByTree[treeIndex];
                            int fenceLength = fenceLengthsByTree[treeIndex];

                            boolean isInsideXRange = leftX <= xCoordinate && xCoordinate <= rightX;
                            boolean isInsideYRange = bottomY <= yCoordinate && yCoordinate <= topY;

                            if (isInsideXRange && isInsideYRange) {
                                insideFenceLengths[insideTreeCount] = fenceLength;
                                ++insideTreeCount;
                            } else {
                                ++cutCount;
                                collectedFenceLength += fenceLength;
                            }
                        }

                        if (cutCount >= minimumCutCount) {
                            continue;
                        }

                        if (collectedFenceLength >= requiredFenceLength) {
                            minimumCutCount = cutCount;
                            continue;
                        }

                        Arrays.sort(insideFenceLengths, 0, insideTreeCount);

                        for (int insideIndex = insideTreeCount - 1; insideIndex >= 0; --insideIndex) {
                            ++cutCount;
                            collectedFenceLength += insideFenceLengths[insideIndex];

                            if (collectedFenceLength >= requiredFenceLength) {
                                if (cutCount < minimumCutCount) {
                                    minimumCutCount = cutCount;
                                }
                                break;
                            }

                            if (cutCount >= minimumCutCount) {
                                break;
                            }
                        }
                    }
                }
            }
        }

        System.out.println(minimumCutCount);
    }
}