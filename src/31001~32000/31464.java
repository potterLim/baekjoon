import java.util.ArrayDeque;
import java.util.Scanner;

public class Main {
    private static final int[] ROW_OFFSETS = { -1, 1, 0, 0 };
    private static final int[] COLUMN_OFFSETS = { 0, 0, -1, 1 };

    private static int sSize;
    private static char[][] sChocolate;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        sSize = scanner.nextInt();
        sChocolate = new char[sSize][sSize];

        for (int row = 0; row < sSize; ++row) {
            String line = scanner.next();
            for (int column = 0; column < sSize; ++column) {
                sChocolate[row][column] = line.charAt(column);
            }
        }

        StringBuilder builder = new StringBuilder();
        int removableChocolateCount = 0;

        for (int row = 0; row < sSize; ++row) {
            for (int column = 0; column < sSize; ++column) {
                if (sChocolate[row][column] != '#') {
                    continue;
                }

                if (canRemoveChocolate(row, column)) {
                    ++removableChocolateCount;
                    builder.append(row + 1)
                            .append(' ')
                            .append(column + 1)
                            .append('\n');
                }
            }
        }

        System.out.println(removableChocolateCount);
        System.out.print(builder);
    }

    private static boolean canRemoveChocolate(int removedRow, int removedColumn) {
        boolean[][] isVisited = new boolean[sSize][sSize];
        ArrayDeque<Integer> rowQueue = new ArrayDeque<>();
        ArrayDeque<Integer> columnQueue = new ArrayDeque<>();

        int remainingChocolateCount = 0;
        int edgeCount = 0;
        int startRow = -1;
        int startColumn = -1;

        for (int row = 0; row < sSize; ++row) {
            for (int column = 0; column < sSize; ++column) {
                if (row == removedRow && column == removedColumn) {
                    continue;
                }

                if (sChocolate[row][column] != '#') {
                    continue;
                }

                ++remainingChocolateCount;

                if (startRow == -1) {
                    startRow = row;
                    startColumn = column;
                }

                for (int direction = 0; direction < 4; ++direction) {
                    int nextRow = row + ROW_OFFSETS[direction];
                    int nextColumn = column + COLUMN_OFFSETS[direction];

                    if (nextRow < 0 || nextRow >= sSize || nextColumn < 0 || nextColumn >= sSize) {
                        continue;
                    }

                    if (nextRow == removedRow && nextColumn == removedColumn) {
                        continue;
                    }

                    if (sChocolate[nextRow][nextColumn] == '#') {
                        ++edgeCount;
                    }
                }
            }
        }

        edgeCount /= 2;

        rowQueue.add(startRow);
        columnQueue.add(startColumn);
        isVisited[startRow][startColumn] = true;

        int visitedChocolateCount = 0;

        while (!rowQueue.isEmpty()) {
            int currentRow = rowQueue.poll();
            int currentColumn = columnQueue.poll();
            ++visitedChocolateCount;

            for (int direction = 0; direction < 4; ++direction) {
                int nextRow = currentRow + ROW_OFFSETS[direction];
                int nextColumn = currentColumn + COLUMN_OFFSETS[direction];

                if (nextRow < 0 || nextRow >= sSize || nextColumn < 0 || nextColumn >= sSize) {
                    continue;
                }

                if (nextRow == removedRow && nextColumn == removedColumn) {
                    continue;
                }

                if (sChocolate[nextRow][nextColumn] != '#') {
                    continue;
                }

                if (isVisited[nextRow][nextColumn]) {
                    continue;
                }

                isVisited[nextRow][nextColumn] = true;
                rowQueue.add(nextRow);
                columnQueue.add(nextColumn);
            }
        }

        if (visitedChocolateCount != remainingChocolateCount) {
            return false;
        }

        return edgeCount == remainingChocolateCount - 1;
    }
}