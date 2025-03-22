import java.util.Scanner;

public class Main {
    static int N;
    static int F;
    static int[] perm;
    static boolean[] visited;
    static int[][] comb;
    static boolean found = false;

    static void buildComb(int n) {
        comb = new int[n + 1][n + 1];
        for (int i = 0; i <= n; ++i) {
            comb[i][0] = 1;
            comb[i][i] = 1;
        }

        for (int i = 2; i <= n; ++i) {
            for (int j = 1; j < i; ++j) {
                comb[i][j] = comb[i - 1][j - 1] + comb[i - 1][j];
            }
        }
    }

    static void dfs(int level, int sumSoFar) {
        if (found) {
            return;
        }

        if (level == N) {
            if (sumSoFar == F) {
                for (int i = 0; i < N; ++i) {
                    System.out.print(perm[i]);
                    if (i < N - 1) {
                        System.out.print(" ");
                    }
                }

                System.out.println();
                found = true;
            }
            return;
        }

        for (int num = 1; num <= N; ++num) {
            if (!visited[num]) {
                visited[num] = true;
                perm[level] = num;
                int newSum = sumSoFar + num * comb[N - 1][level];
                if (newSum <= F) {
                    dfs(level + 1, newSum);
                }
                visited[num] = false;
                if (found) {
                    return;
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        N = scanner.nextInt();
        F = scanner.nextInt();
        scanner.close();

        perm = new int[N];
        visited = new boolean[N + 1];
        buildComb(N);

        dfs(0, 0);
    }
}