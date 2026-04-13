import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String board = scanner.next();
        int rabbitCount = scanner.nextInt();

        int cellCount = board.length();
        char[] colors = board.toCharArray();

        long totalSurvivorCount = 0L;
        long caseCount = 0L;

        int limit = 1 << cellCount;

        for (int mask = 0; mask < limit; ++mask) {
            if (Integer.bitCount(mask) != rabbitCount) {
                continue;
            }

            totalSurvivorCount += getSurvivorCount(mask, colors, cellCount, rabbitCount);
            ++caseCount;
        }

        double expectedValue = (double) totalSurvivorCount / (double) caseCount;
        System.out.println(expectedValue);
    }

    private static int getSurvivorCount(int mask, char[] colors, int cellCount, int rabbitCount) {
        int[] currentPositions = new int[rabbitCount];
        int[] previousPositions = new int[rabbitCount];
        int[] nextPositions = new int[rabbitCount];
        boolean[] hasMoved = new boolean[rabbitCount];
        boolean[] isAlive = new boolean[rabbitCount];

        int rabbitIndex = 0;

        for (int i = 0; i < cellCount; ++i) {
            if ((mask & (1 << i)) == 0) {
                continue;
            }

            currentPositions[rabbitIndex] = i;
            previousPositions[rabbitIndex] = -1;
            isAlive[rabbitIndex] = true;
            ++rabbitIndex;
        }

        int boardSize = cellCount;

        while (boardSize > 2) {
            int[] positionCounts = new int[boardSize];

            for (int i = 0; i < rabbitCount; ++i) {
                if (!isAlive[i]) {
                    continue;
                }

                int currentPosition = currentPositions[i];
                int nextPosition;

                if (currentPosition == 0) {
                    nextPosition = 1;
                } else if (currentPosition == boardSize - 1 || currentPosition == boardSize - 2) {
                    nextPosition = currentPosition - 1;
                } else {
                    char color = colors[currentPosition];

                    if (color == 'W') {
                        nextPosition = currentPosition - 1;
                    } else if (color == 'B') {
                        nextPosition = currentPosition + 1;
                    } else {
                        if (!hasMoved[i]) {
                            nextPosition = currentPosition - 1;
                        } else {
                            nextPosition = previousPositions[i];
                        }
                    }
                }

                nextPositions[i] = nextPosition;
            }

            for (int i = 0; i < rabbitCount; ++i) {
                if (!isAlive[i]) {
                    continue;
                }

                previousPositions[i] = currentPositions[i];
                currentPositions[i] = nextPositions[i];
                hasMoved[i] = true;
                ++positionCounts[currentPositions[i]];
            }

            for (int i = 0; i < rabbitCount; ++i) {
                if (!isAlive[i]) {
                    continue;
                }

                if (positionCounts[currentPositions[i]] > 1) {
                    isAlive[i] = false;
                }
            }

            --boardSize;
        }

        int survivorCount = 0;

        for (int i = 0; i < rabbitCount; ++i) {
            if (isAlive[i]) {
                ++survivorCount;
            }
        }

        return survivorCount;
    }
}