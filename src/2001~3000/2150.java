import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Main {
    private static final class SccGroup {
        int minVertex;
        ArrayList<Integer> vertices;

        SccGroup(int minVertex, ArrayList<Integer> vertices) {
            this.minVertex = minVertex;
            this.vertices = vertices;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int vertexCount = scanner.nextInt();
        int edgeCount = scanner.nextInt();

        int[] forwardHead = new int[vertexCount + 1];
        int[] forwardTo = new int[edgeCount];
        int[] forwardNext = new int[edgeCount];

        int[] reverseHead = new int[vertexCount + 1];
        int[] reverseTo = new int[edgeCount];
        int[] reverseNext = new int[edgeCount];

        Arrays.fill(forwardHead, -1);
        Arrays.fill(reverseHead, -1);

        int edgeIndex = 0;

        int i = 0;
        while (i < edgeCount) {
            int from = scanner.nextInt();
            int to = scanner.nextInt();

            forwardTo[edgeIndex] = to;
            forwardNext[edgeIndex] = forwardHead[from];
            forwardHead[from] = edgeIndex;

            reverseTo[edgeIndex] = from;
            reverseNext[edgeIndex] = reverseHead[to];
            reverseHead[to] = edgeIndex;

            ++edgeIndex;
            ++i;
        }

        boolean[] visited = new boolean[vertexCount + 1];
        int[] iteratorEdge = new int[vertexCount + 1];
        int[] nodeStack = new int[vertexCount + 1];
        int[] finishOrder = new int[vertexCount];
        int finishCount = 0;

        int start = 1;
        while (start <= vertexCount) {
            if (!visited[start]) {
                int stackSize = 0;

                visited[start] = true;
                iteratorEdge[start] = forwardHead[start];
                nodeStack[stackSize] = start;
                ++stackSize;

                while (stackSize > 0) {
                    int node = nodeStack[stackSize - 1];
                    int edge = iteratorEdge[node];

                    if (edge == -1) {
                        --stackSize;
                        finishOrder[finishCount] = node;
                        ++finishCount;
                    } else {
                        iteratorEdge[node] = forwardNext[edge];

                        int nextNode = forwardTo[edge];
                        if (!visited[nextNode]) {
                            visited[nextNode] = true;
                            iteratorEdge[nextNode] = forwardHead[nextNode];
                            nodeStack[stackSize] = nextNode;
                            ++stackSize;
                        }
                    }
                }
            }

            ++start;
        }

        Arrays.fill(visited, false);

        ArrayList<SccGroup> groups = new ArrayList<SccGroup>();

        int orderIndex = vertexCount - 1;
        while (orderIndex >= 0) {
            int root = finishOrder[orderIndex];

            if (!visited[root]) {
                ArrayList<Integer> component = new ArrayList<Integer>();

                int stackSize = 0;

                visited[root] = true;
                nodeStack[stackSize] = root;
                ++stackSize;

                while (stackSize > 0) {
                    --stackSize;

                    int node = nodeStack[stackSize];
                    component.add(node);

                    int edge = reverseHead[node];
                    while (edge != -1) {
                        int nextNode = reverseTo[edge];

                        if (!visited[nextNode]) {
                            visited[nextNode] = true;
                            nodeStack[stackSize] = nextNode;
                            ++stackSize;
                        }

                        edge = reverseNext[edge];
                    }
                }

                Collections.sort(component);

                int minVertex = component.get(0);
                groups.add(new SccGroup(minVertex, component));
            }

            --orderIndex;
        }

        Collections.sort(groups, new Comparator<SccGroup>() {
            @Override
            public int compare(SccGroup a, SccGroup b) {
                if (a.minVertex < b.minVertex) {
                    return -1;
                }
                if (a.minVertex > b.minVertex) {
                    return 1;
                }
                return 0;
            }
        });

        StringBuilder output = new StringBuilder();

        output.append(groups.size()).append('\n');

        int g = 0;
        while (g < groups.size()) {
            ArrayList<Integer> component = groups.get(g).vertices;

            int j = 0;
            while (j < component.size()) {
                output.append(component.get(j)).append(' ');
                ++j;
            }

            output.append(-1).append('\n');
            ++g;
        }

        System.out.print(output.toString());
        scanner.close();
    }
}