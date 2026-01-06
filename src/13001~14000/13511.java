import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static class Edge {
        int to;
        int cost;

        Edge(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }

    private static int nodeCount;
    private static int queryCount;
    private static int maxLog;

    private static List<Edge>[] graph;
    private static int[][] parent;
    private static int[] depth;
    private static long[] distance;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        nodeCount = scanner.nextInt();

        graph = new ArrayList[nodeCount + 1];

        int i = 1;
        while (i <= nodeCount) {
            graph[i] = new ArrayList<>();
            ++i;
        }

        i = 0;
        while (i < nodeCount - 1) {
            int u = scanner.nextInt();
            int v = scanner.nextInt();
            int w = scanner.nextInt();

            graph[u].add(new Edge(v, w));
            graph[v].add(new Edge(u, w));

            ++i;
        }

        maxLog = 0;
        while ((1 << maxLog) <= nodeCount) {
            ++maxLog;
        }

        parent = new int[maxLog][nodeCount + 1];
        depth = new int[nodeCount + 1];
        distance = new long[nodeCount + 1];

        dfs(1, 0, 0L);

        int p = 1;
        while (p < maxLog) {
            i = 1;
            while (i <= nodeCount) {
                int midParent = parent[p - 1][i];
                parent[p][i] = parent[p - 1][midParent];
                ++i;
            }
            ++p;
        }

        queryCount = scanner.nextInt();
        StringBuilder result = new StringBuilder();

        i = 0;
        while (i < queryCount) {
            int type = scanner.nextInt();

            if (type == 1) {
                int u = scanner.nextInt();
                int v = scanner.nextInt();

                int lca = getLca(u, v);
                long cost = distance[u] + distance[v] - 2L * distance[lca];
                result.append(cost).append('\n');
            } else {
                int u = scanner.nextInt();
                int v = scanner.nextInt();
                int k = scanner.nextInt();

                int answer = getKthNode(u, v, k);
                result.append(answer).append('\n');
            }

            ++i;
        }

        System.out.print(result.toString());
        scanner.close();
    }

    private static void dfs(int current, int parentNode, long dist) {
        parent[0][current] = parentNode;
        distance[current] = dist;

        for (Edge edge : graph[current]) {
            int next = edge.to;

            if (next != parentNode) {
                depth[next] = depth[current] + 1;
                dfs(next, current, dist + edge.cost);
            }
        }
    }

    private static int getLca(int u, int v) {
        if (depth[u] < depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }

        int diff = depth[u] - depth[v];
        int bit = 0;

        while (diff > 0) {
            if ((diff & 1) == 1) {
                u = parent[bit][u];
            }
            diff >>= 1;
            ++bit;
        }

        if (u == v) {
            return u;
        }

        int p = maxLog - 1;
        while (p >= 0) {
            if (parent[p][u] != parent[p][v]) {
                u = parent[p][u];
                v = parent[p][v];
            }
            --p;
        }

        return parent[0][u];
    }

    private static int climb(int node, int step) {
        int current = node;
        int move = step;
        int bit = 0;

        while (move > 0) {
            if ((move & 1) == 1) {
                current = parent[bit][current];
            }
            move >>= 1;
            ++bit;
        }

        return current;
    }

    private static int getKthNode(int u, int v, int k) {
        int lca = getLca(u, v);

        int upLength = depth[u] - depth[lca] + 1;

        if (k <= upLength) {
            return climb(u, k - 1);
        }

        int downLength = depth[v] - depth[lca];
        int remain = k - upLength;
        int stepsFromV = downLength - remain;

        return climb(v, stepsFromV);
    }
}