import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private static final int PIECE_COUNT = 5;
    private static int boardSize;
    private static char[][] resultBoard;
    private static boolean[][] occupied;
    private static boolean[] placedPiece;
    private static List<int[]>[] pieceShape;
    private static List<Placement>[] piecePlacements;
    private static int[] pieceHeight;
    private static int[] pieceWidth;

    private static final class Placement {
        private final int y;
        private final int x;
        private final int[][] coords;

        private Placement(int y, int x, int[][] coords) {
            this.y = y;
            this.x = x;
            this.coords = coords;
        }
    }

    public static void main(String[] args) throws Exception {
        FastScanner scanner = new FastScanner();

        boardSize = scanner.nextInt();

        resultBoard = new char[boardSize][boardSize];
        occupied = new boolean[boardSize][boardSize];
        placedPiece = new boolean[PIECE_COUNT];

        pieceShape = new ArrayList[PIECE_COUNT];
        piecePlacements = new ArrayList[PIECE_COUNT];
        pieceHeight = new int[PIECE_COUNT];
        pieceWidth = new int[PIECE_COUNT];

        int totalBlocks = 0;

        for (int i = 0; i < PIECE_COUNT; i++) {
            int height = scanner.nextInt();
            int width = scanner.nextInt();

            pieceHeight[i] = height;
            pieceWidth[i] = width;

            List<int[]> cells = new ArrayList<>();

            for (int j = 0; j < height; j++) {
                String line = scanner.next();

                for (int k = 0; k < width; k++) {
                    char ch = line.charAt(k);

                    if (ch == '#') {
                        cells.add(new int[]{j, k});
                        totalBlocks += 1;
                    }
                }
            }

            pieceShape[i] = cells;
        }

        int boardArea = boardSize * boardSize;

        if (totalBlocks != boardArea) {
            System.out.println("gg");
            return;
        }

        for (int i = 0; i < PIECE_COUNT; i++) {
            piecePlacements[i] = new ArrayList<>();

            int height = pieceHeight[i];
            int width = pieceWidth[i];

            for (int y = 0; y + height <= boardSize; y++) {
                for (int x = 0; x + width <= boardSize; x++) {
                    int size = pieceShape[i].size();

                    int[][] coords = new int[size][2];

                    int idx = 0;

                    for (int[] rc : pieceShape[i]) {
                        int row = y + rc[0];
                        int col = x + rc[1];

                        coords[idx][0] = row;
                        coords[idx][1] = col;

                        idx += 1;
                    }

                    piecePlacements[i].add(new Placement(y, x, coords));
                }
            }
        }

        for (int i = 0; i < boardSize; i++) {
            Arrays.fill(resultBoard[i], '0');
        }

        boolean solved = dfs();

        if (!solved) {
            System.out.println("gg");
            return;
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < boardSize; i++) {
            sb.append(resultBoard[i]);

            if (i + 1 < boardSize) {
                sb.append('\n');
            }
        }

        System.out.print(sb.toString());
    }

    private static boolean dfs() {
        int[] emptyCell = findFirstEmpty();

        if (emptyCell == null) {
            return true;
        }

        int targetRow = emptyCell[0];
        int targetCol = emptyCell[1];

        for (int i = 0; i < PIECE_COUNT; i++) {
            if (placedPiece[i]) {
                continue;
            }

            for (Placement placement : piecePlacements[i]) {
                boolean cover = covers(placement, targetRow, targetCol);

                if (!cover) {
                    continue;
                }

                boolean can = canPlace(placement);

                if (!can) {
                    continue;
                }

                place(placement, (char) ('1' + i), true);
                placedPiece[i] = true;

                boolean ok = dfs();

                if (ok) {
                    return true;
                }

                place(placement, '0', false);
                placedPiece[i] = false;
            }
        }

        return false;
    }

    private static int[] findFirstEmpty() {
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                boolean isUsed = occupied[i][j];

                if (!isUsed) {
                    return new int[]{i, j};
                }
            }
        }

        return null;
    }

    private static boolean covers(Placement placement, int row, int col) {
        for (int[] rc : placement.coords) {
            boolean sameRow = rc[0] == row;
            boolean sameCol = rc[1] == col;

            if (sameRow && sameCol) {
                return true;
            }
        }

        return false;
    }

    private static boolean canPlace(Placement placement) {
        for (int[] rc : placement.coords) {
            int r = rc[0];
            int c = rc[1];

            boolean isUsed = occupied[r][c];

            if (isUsed) {
                return false;
            }
        }

        return true;
    }

    private static void place(Placement placement, char label, boolean fill) {
        for (int[] rc : placement.coords) {
            int r = rc[0];
            int c = rc[1];

            occupied[r][c] = fill;
            resultBoard[r][c] = fill ? label : '0';
        }
    }

    private static final class FastScanner {
        private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        private StringTokenizer tokenizer;

        private String next() throws IOException {
            while (tokenizer == null || !tokenizer.hasMoreElements()) {
                String line = reader.readLine();

                if (line == null) {
                    return null;
                }

                tokenizer = new StringTokenizer(line);
            }

            return tokenizer.nextToken();
        }

        private int nextInt() throws IOException {
            String token = next();
            return Integer.parseInt(token);
        }
    }
}
