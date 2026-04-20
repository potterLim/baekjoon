import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder outputBuilder = new StringBuilder();

        int testCaseCount = scanner.nextInt();

        for (int i = 0; i < testCaseCount; ++i) {
            int startX = scanner.nextInt();
            int startY = scanner.nextInt();
            int endX = scanner.nextInt();
            int endY = scanner.nextInt();

            int planetSystemCount = scanner.nextInt();
            int boundaryCrossCount = 0;

            for (int j = 0; j < planetSystemCount; ++j) {
                int centerX = scanner.nextInt();
                int centerY = scanner.nextInt();
                int radius = scanner.nextInt();

                boolean isStartInside = isPointInsideCircle(startX, startY, centerX, centerY, radius);
                boolean isEndInside = isPointInsideCircle(endX, endY, centerX, centerY, radius);

                if (isStartInside != isEndInside) {
                    ++boundaryCrossCount;
                }
            }

            outputBuilder.append(boundaryCrossCount).append('\n');
        }

        System.out.print(outputBuilder.toString());
        scanner.close();
    }

    private static boolean isPointInsideCircle(int pointX, int pointY, int centerX, int centerY, int radius) {
        long xDifference = pointX - centerX;
        long yDifference = pointY - centerY;
        long distanceSquared = xDifference * xDifference + yDifference * yDifference;
        long radiusSquared = (long) radius * radius;

        return distanceSquared < radiusSquared;
    }
}