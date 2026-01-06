import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class Main {
    private static final int MAX_LOG = 17;
    private static final int INF = 1_000_000_000;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int nodeCount = scanner.nextInt();

        int[] head = new int[nodeCount + 1];
        int[] to = new int[(nodeCount - 1) * 2 + 5];
        int[] weight = new int[(nodeCount - 1) * 2 + 5];
        int[] next = new int[(nodeCount - 1) * 2 + 5];

        int edgeIndex = 0;

        int i = 0;
        while (i < nodeCount - 1) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            int c = scanner.nextInt();

            edgeIndex = addEdge(head, to, weight, next, edgeIndex, a, b, c);
            edgeIndex = addEdge(head, to, weight, next, edgeIndex, b, a, c);

            ++i;
        }

        int[] depth = new int[nodeCount + 1];
        int[][] up = new int[MAX_LOG + 1][nodeCount + 1];
        int[][] minEdge = new int[MAX_LOG + 1][nodeCount + 1];
        int[][] maxEdge = new int[MAX_LOG + 1][nodeCount + 1];

        buildTables(nodeCount, head, to, weight, next, depth, up, minEdge, maxEdge);

        int queryCount = scanner.nextInt();
        StringBuilder output = new StringBuilder();

        int q = 0;
        while (q < queryCount) {
            int u = scanner.nextInt();
            int v = scanner.nextInt();

            int[] result = queryMinMax(u, v, depth, up, minEdge, maxEdge);

            output.append(result[0]).append(' ').append(result[1]).append('\n');

            ++q;
        }

        System.out.print(output.toString());
    }

    private static int addEdge(int[] head, int[] to, int[] weight, int[] next, int edgeIndex, int u, int v, int w) {
        int newIndex = edgeIndex + 1;

        to[newIndex] = v;
        weight[newIndex] = w;
        next[newIndex] = head[u];
        head[u] = newIndex;

        return newIndex;
    }

    private static void buildTables(int nodeCount, int[] head, int[] to, int[] weight, int[] next,
                                    int[] depth, int[][] up, int[][] minEdge, int[][] maxEdge) {
        Deque<Integer> queue = new ArrayDeque<Integer>();
        int[] parent = new int[nodeCount + 1];
        boolean[] visited = new boolean[nodeCount + 1];

        int v = 1;
        while (v <= nodeCount) {
            minEdge[0][v] = INF;
            maxEdge[0][v] = 0;
            ++v;
        }

        visited[1] = true;
        parent[1] = 0;
        depth[1] = 0;
        up[0][1] = 0;
        minEdge[0][1] = INF;
        maxEdge[0][1] = 0;

        queue.addLast(1);

        while (!queue.isEmpty()) {
            int u = queue.removeFirst();

            int edge = head[u];
            while (edge != 0) {
                int nx = to[edge];

                if (!visited[nx]) {
                    visited[nx] = true;
                    parent[nx] = u;
                    depth[nx] = depth[u] + 1;

                    up[0][nx] = u;
                    minEdge[0][nx] = weight[edge];
                    maxEdge[0][nx] = weight[edge];

                    queue.addLast(nx);
                }

                edge = next[edge];
            }
        }

        int p = 1;
        while (p <= MAX_LOG) {
            int x = 1;
            while (x <= nodeCount) {
                int mid = up[p - 1][x];

                up[p][x] = up[p - 1][mid];

                int leftMin = minEdge[p - 1][x];
                int leftMax = maxEdge[p - 1][x];
                int rightMin = minEdge[p - 1][mid];
                int rightMax = maxEdge[p - 1][mid];

                minEdge[p][x] = Math.min(leftMin, rightMin);
                maxEdge[p][x] = Math.max(leftMax, rightMax);

                ++x;
            }

            ++p;
        }
    }

    private static int[] queryMinMax(int u, int v, int[] depth, int[][] up, int[][] minEdge, int[][] maxEdge) {
        int nodeU = u;
        int nodeV = v;

        int bestMin = INF;
        int bestMax = 0;

        if (depth[nodeU] < depth[nodeV]) {
            int temp = nodeU;
            nodeU = nodeV;
            nodeV = temp;
        }

        int diff = depth[nodeU] - depth[nodeV];
        int bit = 0;

        while (diff != 0) {
            if ((diff & 1) != 0) {
                bestMin = Math.min(bestMin, minEdge[bit][nodeU]);
                bestMax = Math.max(bestMax, maxEdge[bit][nodeU]);
                nodeU = up[bit][nodeU];
            }

            diff >>= 1;
            ++bit;
        }

        if (nodeU == nodeV) {
            return new int[]{ bestMin, bestMax };
        }

        int p = MAX_LOG;
        while (p >= 0) {
            if (up[p][nodeU] != up[p][nodeV]) {
                bestMin = Math.min(bestMin, minEdge[p][nodeU]);
                bestMin = Math.min(bestMin, minEdge[p][nodeV]);

                bestMax = Math.max(bestMax, maxEdge[p][nodeU]);
                bestMax = Math.max(bestMax, maxEdge[p][nodeV]);

                nodeU = up[p][nodeU];
                nodeV = up[p][nodeV];
            }

            --p;
        }

        bestMin = Math.min(bestMin, minEdge[0][nodeU]);
        bestMin = Math.min(bestMin, minEdge[0][nodeV]);

        bestMax = Math.max(bestMax, maxEdge[0][nodeU]);
        bestMax = Math.max(bestMax, maxEdge[0][nodeV]);

        return new int[]{ bestMin, bestMax };
    }
}