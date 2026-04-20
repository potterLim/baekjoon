import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder outputBuilder = new StringBuilder();

        int testCaseCount = scanner.nextInt();

        for (int i = 0; i < testCaseCount; ++i) {
            int firstCenterX = scanner.nextInt();
            int firstCenterY = scanner.nextInt();
            int firstRadius = scanner.nextInt();
            int secondCenterX = scanner.nextInt();
            int secondCenterY = scanner.nextInt();
            int secondRadius = scanner.nextInt();

            if (firstCenterX == secondCenterX && firstCenterY == secondCenterY) {
                if (firstRadius == secondRadius) {
                    outputBuilder.append(-1);
                } else {
                    outputBuilder.append(0);
                }

                outputBuilder.append('\n');
                continue;
            }

            long xDifference = firstCenterX - secondCenterX;
            long yDifference = firstCenterY - secondCenterY;
            long centerDistanceSquared = xDifference * xDifference + yDifference * yDifference;

            long radiusSum = firstRadius + secondRadius;
            long radiusSumSquared = radiusSum * radiusSum;

            long radiusDifference = Math.abs(firstRadius - secondRadius);
            long radiusDifferenceSquared = radiusDifference * radiusDifference;

            if (centerDistanceSquared > radiusSumSquared || centerDistanceSquared < radiusDifferenceSquared) {
                outputBuilder.append(0);
            } else if (centerDistanceSquared == radiusSumSquared || centerDistanceSquared == radiusDifferenceSquared) {
                outputBuilder.append(1);
            } else {
                outputBuilder.append(2);
            }

            outputBuilder.append('\n');
        }

        System.out.print(outputBuilder.toString());
        scanner.close();
    }
}