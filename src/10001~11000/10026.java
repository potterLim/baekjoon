import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static char[][] grid;
    private static boolean[][] visitedForNormal;
    private static boolean[][] visitedForColorBlind;
    private static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(reader.readLine());

        grid = new char[n][n];
        for (int rowIndex = 0; rowIndex < n; ++rowIndex) {
            String line = reader.readLine();
            for (int colIndex = 0; colIndex < n; ++colIndex) {
                grid[rowIndex][colIndex] = line.charAt(colIndex);
            }
        }

        visitedForNormal = new boolean[n][n];
        visitedForColorBlind = new boolean[n][n];

        int normalCount = 0;
        int colorBlindCount = 0;

        for (int rowIndex = 0; rowIndex < n; ++rowIndex) {
            for (int colIndex = 0; colIndex < n; ++colIndex) {
                if (!visitedForNormal[rowIndex][colIndex]) {
                    searchNormalRecursive(rowIndex, colIndex, grid[rowIndex][colIndex]);
                    ++normalCount;
                }
            }
        }

        // 적록색약 관점 구역 수 계산
        for (int rowIndex = 0; rowIndex < n; ++rowIndex) {
            for (int colIndex = 0; colIndex < n; ++colIndex) {
                if (!visitedForColorBlind[rowIndex][colIndex]) {
                    searchColorBlindRecursive(rowIndex, colIndex, grid[rowIndex][colIndex]);
                    ++colorBlindCount;
                }
            }
        }

        System.out.println(normalCount + " " + colorBlindCount);
    }

    private static void searchNormalRecursive(int rowIndex, int colIndex, char color) {
        visitedForNormal[rowIndex][colIndex] = true;

        final int[] rowOffsets = {-1, 0, 1, 0};
        final int[] colOffsets = {0, 1, 0, -1};

        for (int i = 0; i < 4; ++i) {
            int nextRow = rowIndex + rowOffsets[i];
            int nextCol = colIndex + colOffsets[i];

            if (isInsideGrid(nextRow, nextCol)
                    && !visitedForNormal[nextRow][nextCol]
                    && grid[nextRow][nextCol] == color) {
                searchNormalRecursive(nextRow, nextCol, color);
            }
        }
    }

    private static void searchColorBlindRecursive(int rowIndex, int colIndex, char color) {
        visitedForColorBlind[rowIndex][colIndex] = true;

        final int[] rowOffsets = {-1, 0, 1, 0};
        final int[] colOffsets = {0, 1, 0, -1};

        for (int i = 0; i < 4; ++i) {
            int nextRow = rowIndex + rowOffsets[i];
            int nextCol = colIndex + colOffsets[i];

            if (isInsideGrid(nextRow, nextCol) && !visitedForColorBlind[nextRow][nextCol]) {
                if (isSameColorForColorBlind(grid[nextRow][nextCol], color)) {
                    searchColorBlindRecursive(nextRow, nextCol, color);
                }
            }
        }
    }

    private static boolean isInsideGrid(int rowIndex, int colIndex) {
        return (rowIndex >= 0 && rowIndex < n && colIndex >= 0 && colIndex < n);
    }

    private static boolean isSameColorForColorBlind(char currentColor, char baseColor) {
        if ((baseColor == 'R' || baseColor == 'G')
                && (currentColor == 'R' || currentColor == 'G')) {
            return true;
        }

        return currentColor == baseColor;
    }
}
