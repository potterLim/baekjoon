import java.util.Locale;
import java.util.Scanner;

public class Main {
    private static int sPointCount;
    private static long[] sXCoordinates;
    private static long[] sYCoordinates;
    private static long sTotalXSum;
    private static long sTotalYSum;
    private static double sMinimumVectorLength;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder outputBuilder = new StringBuilder();

        int testCaseCount = scanner.nextInt();

        for (int i = 0; i < testCaseCount; ++i) {
            sPointCount = scanner.nextInt();
            sXCoordinates = new long[sPointCount];
            sYCoordinates = new long[sPointCount];
            sTotalXSum = 0L;
            sTotalYSum = 0L;
            sMinimumVectorLength = Double.MAX_VALUE;

            for (int j = 0; j < sPointCount; ++j) {
                long xCoordinate = scanner.nextLong();
                long yCoordinate = scanner.nextLong();

                sXCoordinates[j] = xCoordinate;
                sYCoordinates[j] = yCoordinate;
                sTotalXSum += xCoordinate;
                sTotalYSum += yCoordinate;
            }

            searchMinimumVectorLengthRecursive(1, 1, sXCoordinates[0], sYCoordinates[0]);
            outputBuilder.append(String.format(Locale.US, "%.12f", sMinimumVectorLength)).append('\n');
        }

        System.out.print(outputBuilder.toString());
        scanner.close();
    }

    private static void searchMinimumVectorLengthRecursive(int currentIndex, int selectedPointCount, long selectedXSum, long selectedYSum) {
        int targetSelectedPointCount = sPointCount / 2;

        if (selectedPointCount == targetSelectedPointCount) {
            double currentVectorLength = calculateVectorLength(selectedXSum, selectedYSum);

            if (currentVectorLength < sMinimumVectorLength) {
                sMinimumVectorLength = currentVectorLength;
            }

            return;
        }

        if (currentIndex == sPointCount) {
            return;
        }

        int remainingPointCount = sPointCount - currentIndex;
        int requiredPointCount = targetSelectedPointCount - selectedPointCount;

        if (remainingPointCount < requiredPointCount) {
            return;
        }

        searchMinimumVectorLengthRecursive(
                currentIndex + 1,
                selectedPointCount + 1,
                selectedXSum + sXCoordinates[currentIndex],
                selectedYSum + sYCoordinates[currentIndex]
        );

        searchMinimumVectorLengthRecursive(currentIndex + 1, selectedPointCount, selectedXSum, selectedYSum);
    }

    private static double calculateVectorLength(long selectedXSum, long selectedYSum) {
        long resultX = selectedXSum * 2L - sTotalXSum;
        long resultY = selectedYSum * 2L - sTotalYSum;

        return Math.sqrt((double) resultX * resultX + (double) resultY * resultY);
    }
}