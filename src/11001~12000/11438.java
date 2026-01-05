import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final int MAX_LOG = 17;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int nodeCount = scanner.nextInt();

        int[] head = new int[nodeCount + 1];
        int[] to = new int[(nodeCount - 1) * 2 + 5];
        int[] next = new int[(nodeCount - 1) * 2 + 5];

        int edgeIndex = 0;

        int i = 0;
        while (i < nodeCount - 1) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();

            edgeIndex = addEdge(head, to, next, edgeIndex, a, b);
            edgeIndex = addEdge(head, to, next, edgeIndex, b, a);

            ++i;
        }

        int[] depth = new int[nodeCount + 1];
        int[] parent = new int[nodeCount + 1];
        int[][] up = new int[MAX_LOG + 1][nodeCount + 1];

        buildParents(nodeCount, head, to, next, depth, parent, up);

        int queryCount = scanner.nextInt();
        StringBuilder output = new StringBuilder();

        int q = 0;
        while (q < queryCount) {
            int u = scanner.nextInt();
            int v = scanner.nextInt();

            int lca = getLca(u, v, depth, up);

            output.append(lca).append('\n');

            ++q;
        }

        System.out.print(output.toString());
    }

    private static int addEdge(int[] head, int[] to, int[] next, int edgeIndex, int u, int v) {
        int newIndex = edgeIndex + 1;

        to[newIndex] = v;
        next[newIndex] = head[u];
        head[u] = newIndex;

        return newIndex;
    }

    private static void buildParents(int nodeCount, int[] head, int[] to, int[] next, int[] depth, int[] parent, int[][] up) {
        Deque<Integer> queue = new ArrayDeque<Integer>();

        depth[1] = 0;
        parent[1] = 0;
        up[0][1] = 0;

        queue.addLast(1);

        while (!queue.isEmpty()) {
            int u = queue.removeFirst();

            int edge = head[u];
            while (edge != 0) {
                int v = to[edge];

                if (v != parent[u]) {
                    parent[v] = u;
                    depth[v] = depth[u] + 1;
                    up[0][v] = u;
                    queue.addLast(v);
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
                ++x;
            }
            ++p;
        }
    }

    private static int getLca(int u, int v, int[] depth, int[][] up) {
        int nodeU = u;
        int nodeV = v;

        if (depth[nodeU] < depth[nodeV]) {
            int temp = nodeU;
            nodeU = nodeV;
            nodeV = temp;
        }

        int diff = depth[nodeU] - depth[nodeV];
        int bit = 0;

        while (diff != 0) {
            if ((diff & 1) != 0) {
                nodeU = up[bit][nodeU];
            }
            diff >>= 1;
            ++bit;
        }

        if (nodeU == nodeV) {
            return nodeU;
        }

        int p = MAX_LOG;
        while (p >= 0) {
            if (up[p][nodeU] != up[p][nodeV]) {
                nodeU = up[p][nodeU];
                nodeV = up[p][nodeV];
            }
            --p;
        }

        return up[0][nodeU];
    }
}