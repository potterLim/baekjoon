import java.io.BufferedInputStream;
import java.io.IOException;

public class Main {
    private static int[] headForward;
    private static int[] toForward;
    private static int[] nextForward;

    private static int[] headReverse;
    private static int[] toReverse;
    private static int[] nextReverse;

    private static boolean[] visited;

    private static int[] finishOrder;
    private static int finishCount;

    private static int[] stackNode;
    private static int[] stackEdge;
    private static int stackSize;

    private static int[] componentId;
    private static int componentCount;

    public static void main(String[] args) throws Exception {
        FastScanner scanner = new FastScanner();
        StringBuilder output = new StringBuilder();

        int testCases = scanner.nextInt();
        int tc = 0;

        while (tc < testCases) {
            int nodeCount = scanner.nextInt();
            int edgeCount = scanner.nextInt();

            headForward = new int[nodeCount + 1];
            headReverse = new int[nodeCount + 1];

            fillInt(headForward, -1);
            fillInt(headReverse, -1);

            toForward = new int[edgeCount];
            nextForward = new int[edgeCount];

            toReverse = new int[edgeCount];
            nextReverse = new int[edgeCount];

            int e = 0;
            while (e < edgeCount) {
                int from = scanner.nextInt();
                int to = scanner.nextInt();

                toForward[e] = to;
                nextForward[e] = headForward[from];
                headForward[from] = e;

                toReverse[e] = from;
                nextReverse[e] = headReverse[to];
                headReverse[to] = e;

                ++e;
            }

            visited = new boolean[nodeCount + 1];

            finishOrder = new int[nodeCount];
            finishCount = 0;

            stackNode = new int[nodeCount + 5];
            stackEdge = new int[nodeCount + 5];
            stackSize = 0;

            int v = 1;
            while (v <= nodeCount) {
                if (!visited[v]) {
                    dfsFinishOrderIterative(v);
                }
                ++v;
            }

            componentId = new int[nodeCount + 1];
            fillInt(componentId, -1);
            componentCount = 0;

            int idx = finishCount - 1;
            while (idx >= 0) {
                int start = finishOrder[idx];

                if (componentId[start] == -1) {
                    assignComponentIterative(start, componentCount);
                    ++componentCount;
                }

                --idx;
            }

            int[] indegree = new int[componentCount];

            int u = 1;
            while (u <= nodeCount) {
                int edge = headForward[u];

                while (edge != -1) {
                    int w = toForward[edge];

                    int cu = componentId[u];
                    int cw = componentId[w];

                    if (cu != cw) {
                        ++indegree[cw];
                    }

                    edge = nextForward[edge];
                }

                ++u;
            }

            int answer = 0;
            int c = 0;

            while (c < componentCount) {
                if (indegree[c] == 0) {
                    ++answer;
                }
                ++c;
            }

            output.append(answer).append('\n');
            ++tc;
        }

        System.out.print(output.toString());
    }

    private static void dfsFinishOrderIterative(int start) {
        stackNode[0] = start;
        stackEdge[0] = headForward[start];
        stackSize = 1;

        visited[start] = true;

        while (stackSize > 0) {
            int node = stackNode[stackSize - 1];
            int edge = stackEdge[stackSize - 1];

            if (edge == -1) {
                --stackSize;
                finishOrder[finishCount] = node;
                ++finishCount;
                continue;
            }

            stackEdge[stackSize - 1] = nextForward[edge];

            int nextNode = toForward[edge];

            if (!visited[nextNode]) {
                visited[nextNode] = true;

                stackNode[stackSize] = nextNode;
                stackEdge[stackSize] = headForward[nextNode];
                ++stackSize;
            }
        }
    }

    private static void assignComponentIterative(int start, int comp) {
        stackNode[0] = start;
        stackSize = 1;

        componentId[start] = comp;

        while (stackSize > 0) {
            --stackSize;

            int node = stackNode[stackSize];
            int edge = headReverse[node];

            while (edge != -1) {
                int nextNode = toReverse[edge];

                if (componentId[nextNode] == -1) {
                    componentId[nextNode] = comp;

                    stackNode[stackSize] = nextNode;
                    ++stackSize;
                }

                edge = nextReverse[edge];
            }
        }
    }

    private static void fillInt(int[] array, int value) {
        int i = 0;

        while (i < array.length) {
            array[i] = value;
            ++i;
        }
    }

    private static final class FastScanner {
        private final BufferedInputStream input;
        private final byte[] buffer;
        private int size;
        private int index;

        public FastScanner() {
            input = new BufferedInputStream(System.in);
            buffer = new byte[1 << 20];
            size = 0;
            index = 0;
        }

        public int nextInt() throws IOException {
            int c = read();

            while (c <= 32) {
                c = read();
            }

            int sign = 1;

            if (c == '-') {
                sign = -1;
                c = read();
            }

            int value = 0;

            while (c > 32) {
                value = value * 10 + (c - '0');
                c = read();
            }

            return value * sign;
        }

        private int read() throws IOException {
            if (index >= size) {
                size = input.read(buffer);
                index = 0;

                if (size <= 0) {
                    return -1;
                }
            }

            int c = buffer[index];
            ++index;
            return c;
        }
    }
}