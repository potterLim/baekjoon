import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    private static char[][] board;
    private static boolean[][][] visited;
    private static int n;
    private static int m;
    private static final int[] rowOffsets = {-1, 0, 1, 0};
    private static final int[] colOffsets = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] tokens = reader.readLine().split(" ");
        n = Integer.parseInt(tokens[0]);
        m = Integer.parseInt(tokens[1]);

        board = new char[n][m];
        int startRow = 0;
        int startCol = 0;

        for (int rowIndex = 0; rowIndex < n; ++rowIndex) {
            String line = reader.readLine();
            for (int colIndex = 0; colIndex < m; ++colIndex) {
                board[rowIndex][colIndex] = line.charAt(colIndex);
                if (board[rowIndex][colIndex] == '0') {
                    startRow = rowIndex;
                    startCol = colIndex;
                }
            }
        }

        visited = new boolean[n][m][64];
        System.out.println(runBfs(startRow, startCol));
    }

    private static int runBfs(int startRow, int startCol) {
        Queue<State> queue = new LinkedList<>();
        queue.offer(new State(startRow, startCol, 0, 0));
        visited[startRow][startCol][0] = true;

        while (!queue.isEmpty()) {
            State current = queue.poll();
            if (board[current.row][current.col] == '1') {
                return current.steps;
            }

            for (int i = 0; i < 4; ++i) {
                int nextRow = current.row + rowOffsets[i];
                int nextCol = current.col + colOffsets[i];
                int nextKeys = current.keys;

                if (nextRow < 0 || nextRow >= n || nextCol < 0 || nextCol >= m) {
                    continue;
                }
                if (board[nextRow][nextCol] == '#') {
                    continue;
                }
                if (board[nextRow][nextCol] >= 'A' && board[nextRow][nextCol] <= 'F') {
                    int doorIndex = board[nextRow][nextCol] - 'A';
                    if ((current.keys & (1 << doorIndex)) == 0) {
                        continue;
                    }
                }
                if (board[nextRow][nextCol] >= 'a' && board[nextRow][nextCol] <= 'f') {
                    int keyIndex = board[nextRow][nextCol] - 'a';
                    nextKeys = current.keys | (1 << keyIndex);
                }
                if (!visited[nextRow][nextCol][nextKeys]) {
                    visited[nextRow][nextCol][nextKeys] = true;
                    queue.offer(new State(nextRow, nextCol, nextKeys, current.steps + 1));
                }
            }
        }
        return -1;
    }

    private static class State {
        public int row;
        public int col;
        public int keys;
        public int steps;

        public State(int row, int col, int keys, int steps) {
            this.row = row;
            this.col = col;
            this.keys = keys;
            this.steps = steps;
        }
    }
}
