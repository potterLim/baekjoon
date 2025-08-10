import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    private static final int INF = 1_000_000_000;
    private static final int[] DR = {-1, 1, 0, 0};
    private static final int[] DC = {0, 0, -1, 1};

    private static class FastScanner {
        private final BufferedReader br;
        private StringTokenizer st;

        public FastScanner() {
            this.br = new BufferedReader(new InputStreamReader(System.in));
        }

        public String nextToken() throws IOException {
            while (st == null || !st.hasMoreTokens()) {
                String line = br.readLine();
                if (line == null) {
                    return null;
                }
                if (line.length() == 0) {
                    continue;
                }
                st = new StringTokenizer(line);
            }
            return st.nextToken();
        }

        public Integer nextIntOrNull() throws IOException {
            String tok = nextToken();
            if (tok == null) {
                return null;
            }
            return Integer.parseInt(tok);
        }

        public String nextNonEmptyLine() throws IOException {
            String s;
            while (true) {
                s = br.readLine();
                if (s == null) {
                    return null;
                }
                if (s.length() == 0) {
                    continue;
                }
                return s;
            }
        }
    }

    private static class State implements Comparable<State> {
        public int row;
        public int col;
        public int time;

        public State(int row, int col, int time) {
            this.row = row;
            this.col = col;
            this.time = time;
        }

        @Override
        public int compareTo(State other) {
            return Integer.compare(this.time, other.time);
        }
    }

    private static class LightInfo {
        public boolean ewFirst;
        public int a;
        public int b;
    }

    public static void main(String[] args) throws IOException {
        FastScanner fs = new FastScanner();

        while (true) {
            Integer mObj = fs.nextIntOrNull();
            if (mObj == null) {
                break;
            }
            int m = mObj;
            int n = fs.nextIntOrNull();
            if (m == 0 && n == 0) {
                break;
            }

            char[][] grid = new char[m][n];
            int sr = -1;
            int sc = -1;
            int tr = -1;
            int tc = -1;
            int maxDigit = -1;

            for (int i = 0; i < m; ++i) {
                String row = fs.nextNonEmptyLine();
                while (row != null && row.length() < n) {
                    row = fs.nextNonEmptyLine();
                }
                if (row == null) {
                    System.out.println("impossible");
                    return;
                }
                for (int j = 0; j < n; ++j) {
                    char ch = row.charAt(j);
                    grid[i][j] = ch;
                    if (ch == 'A') {
                        sr = i;
                        sc = j;
                    } else if (ch == 'B') {
                        tr = i;
                        tc = j;
                    } else if (ch >= '0' && ch <= '9') {
                        int d = ch - '0';
                        if (d > maxDigit) {
                            maxDigit = d;
                        }
                    }
                }
            }

            LightInfo[] lights = new LightInfo[(maxDigit >= 0) ? (maxDigit + 1) : 0];
            for (int i = 0; i <= maxDigit; ++i) {
                Integer idxObj = fs.nextIntOrNull();
                if (idxObj == null) {
                    System.out.println("impossible");
                    return;
                }
                int idx = idxObj;
                String sym = fs.nextToken();
                Integer aObj = fs.nextIntOrNull();
                Integer bObj = fs.nextIntOrNull();
                if (sym == null || aObj == null || bObj == null) {
                    System.out.println("impossible");
                    return;
                }
                LightInfo li = new LightInfo();
                li.ewFirst = (sym.charAt(0) == '-');
                li.a = aObj;
                li.b = bObj;
                lights[idx] = li;
            }

            if (sr < 0 || sc < 0 || tr < 0 || tc < 0) {
                System.out.println("impossible");
                continue;
            }

            Integer ans = dijkstra(grid, sr, sc, tr, tc, lights);
            if (ans == null) {
                System.out.println("impossible");
            } else {
                System.out.println(ans);
            }
        }
    }

    private static Integer dijkstra(char[][] grid, int sr, int sc, int tr, int tc, LightInfo[] lights) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dist = new int[m][n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                dist[i][j] = INF;
            }
        }

        PriorityQueue<State> pq = new PriorityQueue<>();
        dist[sr][sc] = 0;
        pq.offer(new State(sr, sc, 0));

        while (!pq.isEmpty()) {
            State cur = pq.poll();
            if (cur.time != dist[cur.row][cur.col]) {
                continue;
            }
            if (cur.row == tr && cur.col == tc) {
                return cur.time;
            }

            for (int d = 0; d < 4; ++d) {
                int nr = cur.row + DR[d];
                int nc = cur.col + DC[d];
                if (nr < 0 || nr >= m || nc < 0 || nc >= n) {
                    continue;
                }
                char target = grid[nr][nc];
                if (target == '.') {
                    continue;
                }

                int add = 1;
                if (target >= '0' && target <= '9') {
                    int idx = target - '0';
                    if (lights.length == 0 || idx >= lights.length || lights[idx] == null) {
                        return null;
                    }
                    boolean isEW = (d == 2 || d == 3);
                    add += waitTime(lights[idx], cur.time + 1, isEW);
                }

                int nt = cur.time + add;
                if (nt < dist[nr][nc]) {
                    dist[nr][nc] = nt;
                    pq.offer(new State(nr, nc, nt));
                }
            }
        }

        return null;
    }

    private static int waitTime(LightInfo li, int tArrival, boolean isEW) {
        int p = li.a + li.b;
        int phase = ((tArrival - 1) % p) + 1;
        if (li.ewFirst) {
            if (isEW) {
                if (phase <= li.a) {
                    return 0;
                }
                return p - phase + 1;
            } else {
                if (phase > li.a) {
                    return 0;
                }
                return li.a - phase + 1;
            }
        } else {
            if (!isEW) {
                if (phase <= li.b) {
                    return 0;
                }
                return p - phase + 1;
            } else {
                if (phase > li.b) {
                    return 0;
                }
                return li.b - phase + 1;
            }
        }
    }
}
