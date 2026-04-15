import java.util.Scanner;

public class Main {
    private static final double SQRT2 = Math.sqrt(2.0);

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int rowCount = scanner.nextInt();
        int columnCount = scanner.nextInt();

        char[][] board = new char[rowCount][];
        for (int row = 0; row < rowCount; ++row) {
            board[row] = scanner.next().toCharArray();
        }

        int degree = scanner.nextInt();

        double centerX = (columnCount - 1) / 2.0;
        double centerY = (rowCount - 1) / 2.0;

        double radian = Math.toRadians(degree);
        double cos = Math.cos(radian);
        double sin = Math.sin(radian);

        int cellCount = rowCount * columnCount;
        double[] rotatedRows = new double[cellCount];
        double[] rotatedColumns = new double[cellCount];
        char[] letters = new char[cellCount];

        double minRow = Double.POSITIVE_INFINITY;
        double minColumn = Double.POSITIVE_INFINITY;
        double maxRow = Double.NEGATIVE_INFINITY;
        double maxColumn = Double.NEGATIVE_INFINITY;

        int index = 0;
        for (int row = 0; row < rowCount; ++row) {
            for (int column = 0; column < columnCount; ++column) {
                double x = column - centerX;
                double y = centerY - row;

                double rotatedX = x * cos + y * sin;
                double rotatedY = -x * sin + y * cos;

                if (degree % 90 != 0) {
                    rotatedX *= SQRT2;
                    rotatedY *= SQRT2;
                }

                double outputRow = -rotatedY;
                double outputColumn = rotatedX;

                rotatedRows[index] = outputRow;
                rotatedColumns[index] = outputColumn;
                letters[index] = board[row][column];

                if (outputRow < minRow) {
                    minRow = outputRow;
                }

                if (outputRow > maxRow) {
                    maxRow = outputRow;
                }

                if (outputColumn < minColumn) {
                    minColumn = outputColumn;
                }

                if (outputColumn > maxColumn) {
                    maxColumn = outputColumn;
                }

                ++index;
            }
        }

        int height = (int) Math.round(maxRow - minRow) + 1;
        int width = (int) Math.round(maxColumn - minColumn) + 1;

        char[][] answer = new char[height][width];
        for (int row = 0; row < height; ++row) {
            for (int column = 0; column < width; ++column) {
                answer[row][column] = ' ';
            }
        }

        for (int i = 0; i < cellCount; ++i) {
            int row = (int) Math.round(rotatedRows[i] - minRow);
            int column = (int) Math.round(rotatedColumns[i] - minColumn);
            answer[row][column] = letters[i];
        }

        StringBuilder builder = new StringBuilder();

        for (int row = 0; row < height; ++row) {
            int lastColumn = width - 1;
            while (lastColumn >= 0 && answer[row][lastColumn] == ' ') {
                --lastColumn;
            }

            for (int column = 0; column <= lastColumn; ++column) {
                builder.append(answer[row][column]);
            }

            if (row + 1 < height) {
                builder.append('\n');
            }
        }

        System.out.print(builder);
        scanner.close();
    }
}