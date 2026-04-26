import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int m = scanner.nextInt();

        boolean[][] graph = new boolean[n + 1][n + 1];
        boolean[] visited = new boolean[n + 1];

        for (int i = 0; i < m; ++i) {
            int u = scanner.nextInt();
            int v = scanner.nextInt();

            graph[u][v] = true;
            graph[v][u] = true;
        }

        int count = 0;

        for (int i = 1; i <= n; ++i) {
            if (visited[i] == false) {
                ++count;
                dfs(graph, visited, i, n);
            }
        }

        System.out.println(count);

        scanner.close();
    }

    private static void dfs(boolean[][] graph, boolean[] visited, int current, int n) {
        visited[current] = true;

        for (int i = 1; i <= n; ++i) {
            if (graph[current][i] == true && visited[i] == false) {
                dfs(graph, visited, i, n);
            }
        }
    }
}