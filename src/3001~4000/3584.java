import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int testCaseCount = scanner.nextInt();
        StringBuilder output = new StringBuilder();

        int t = 0;
        while (t < testCaseCount) {
            int nodeCount = scanner.nextInt();

            int[] parent = new int[nodeCount + 1];
            boolean[] hasParent = new boolean[nodeCount + 1];

            List<Integer>[] children = createChildrenLists(nodeCount);

            int i = 0;
            while (i < nodeCount - 1) {
                int p = scanner.nextInt();
                int c = scanner.nextInt();

                parent[c] = p;
                hasParent[c] = true;
                children[p].add(c);

                ++i;
            }

            int nodeA = scanner.nextInt();
            int nodeB = scanner.nextInt();

            int root = findRoot(hasParent, nodeCount);

            int[] depth = new int[nodeCount + 1];
            buildDepth(children, depth, root);

            int answer = findLca(parent, depth, nodeA, nodeB);

            output.append(answer).append('\n');

            ++t;
        }

        System.out.print(output.toString());
    }

    private static List<Integer>[] createChildrenLists(int nodeCount) {
        @SuppressWarnings("unchecked")
        List<Integer>[] children = (List<Integer>[]) new List[nodeCount + 1];

        int i = 1;
        while (i <= nodeCount) {
            children[i] = new ArrayList<Integer>();
            ++i;
        }

        return children;
    }

    private static int findRoot(boolean[] hasParent, int nodeCount) {
        int i = 1;
        while (i <= nodeCount) {
            if (!hasParent[i]) {
                return i;
            }
            ++i;
        }

        return 1;
    }

    private static void buildDepth(List<Integer>[] children, int[] depth, int root) {
        Deque<Integer> queue = new ArrayDeque<Integer>();

        depth[root] = 0;
        queue.addLast(root);

        while (!queue.isEmpty()) {
            int current = queue.removeFirst();

            List<Integer> nextList = children[current];
            int i = 0;
            while (i < nextList.size()) {
                int next = nextList.get(i);

                depth[next] = depth[current] + 1;
                queue.addLast(next);

                ++i;
            }
        }
    }

    private static int findLca(int[] parent, int[] depth, int nodeA, int nodeB) {
        int a = nodeA;
        int b = nodeB;

        while (depth[a] > depth[b]) {
            a = parent[a];
        }

        while (depth[b] > depth[a]) {
            b = parent[b];
        }

        while (a != b) {
            a = parent[a];
            b = parent[b];
        }

        return a;
    }
}