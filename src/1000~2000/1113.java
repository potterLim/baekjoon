import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    private static final int[] DR = {-1, 1, 0, 0};
    private static final int[] DC = {0, 0, -1, 1};

    private static class Cell implements Comparable<Cell> {
        public int row;
        public int col;
        public int height;

        public Cell(int row, int col, int height) {
            this.row = row;
            this.col = col;
            this.height = height;
        }

        @Override
        public int compareTo(Cell other) {
            return Integer.compare(this.height, other.height);
        }
    }

    public static void main(String[] args) throws IOException {
        int[][] heightMap = readInput();
        int trappedWater = calculateTrappedWater(heightMap);
        System.out.println(trappedWater);
    }

    private static int[][] readInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String firstLine = reader.readLine();
        assert (firstLine != null);

        StringTokenizer tokenizer = new StringTokenizer(firstLine);
        int n = Integer.parseInt(tokenizer.nextToken());
        int m = Integer.parseInt(tokenizer.nextToken());

        int[][] heightMap = new int[n][m];

        for (int i = 0; i < n; ++i) {
            String line = reader.readLine();
            assert (line != null);
            for (int j = 0; j < m; ++j) {
                heightMap[i][j] = line.charAt(j) - '0';
            }
        }

        return heightMap;
    }

    private static int calculateTrappedWater(int[][] heightMap) {
        int n = heightMap.length;
        int m = heightMap[0].length;

        if (n <= 2 || m <= 2) {
            return 0;
        }

        boolean[][] visited = new boolean[n][m];
        PriorityQueue<Cell> minHeap = new PriorityQueue<>();

        for (int c = 0; c < m; ++c) {
            minHeap.offer(new Cell(0, c, heightMap[0][c]));
            minHeap.offer(new Cell(n - 1, c, heightMap[n - 1][c]));
            visited[0][c] = true;
            visited[n - 1][c] = true;
        }
        for (int r = 1; r < n - 1; ++r) {
            minHeap.offer(new Cell(r, 0, heightMap[r][0]));
            minHeap.offer(new Cell(r, m - 1, heightMap[r][m - 1]));
            visited[r][0] = true;
            visited[r][m - 1] = true;
        }

        int total = 0;

        while (!minHeap.isEmpty()) {
            Cell current = minHeap.poll();

            for (int d = 0; d < 4; ++d) {
                int nr = current.row + DR[d];
                int nc = current.col + DC[d];

                if (nr < 0 || nr >= n || nc < 0 || nc >= m) {
                    continue;
                }
                if (visited[nr][nc]) {
                    continue;
                }

                visited[nr][nc] = true;

                int neighborHeight = heightMap[nr][nc];
                if (neighborHeight < current.height) {
                    total += (current.height - neighborHeight);
                    minHeap.offer(new Cell(nr, nc, current.height));
                } else {
                    minHeap.offer(new Cell(nr, nc, neighborHeight));
                }
            }
        }

        return total;
    }
}
