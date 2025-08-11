import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int countRepaints(char[][] board, int r, int c) {
        int repaintWhiteStart = 0;
        int repaintBlackStart = 0;

        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                char current = board[r + i][c + j];

                char expectedIfWhite;
                if ((i + j) % 2 == 0) {
                    expectedIfWhite = 'W';
                } else {
                    expectedIfWhite = 'B';
                }

                char expectedIfBlack;
                if ((i + j) % 2 == 0) {
                    expectedIfBlack = 'B';
                } else {
                    expectedIfBlack = 'W';
                }

                if (current != expectedIfWhite) {
                    ++repaintWhiteStart;
                }
                if (current != expectedIfBlack) {
                    ++repaintBlackStart;
                }
            }
        }

        if (repaintWhiteStart < repaintBlackStart) {
            return repaintWhiteStart;
        } else {
            return repaintBlackStart;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        char[][] board = new char[n][m];
        for (int i = 0; i < n; ++i) {
            String line = br.readLine().trim();
            for (int j = 0; j < m; ++j) {
                board[i][j] = line.charAt(j);
            }
        }

        int answer = Integer.MAX_VALUE;
        for (int r = 0; r <= n - 8; ++r) {
            for (int c = 0; c <= m - 8; ++c) {
                int repaint = countRepaints(board, r, c);
                if (repaint < answer) {
                    answer = repaint;
                }
            }
        }

        System.out.println(answer);
    }
}
