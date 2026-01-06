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

    private static int[] dfsNodeStack;
    private static int[] dfsEdgeStack;
    private static int dfsStackSize;

    private static int[] stack;
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

            headForward = new int[nodeCount];
            headReverse = new int[nodeCount];

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

            visited = new boolean[nodeCount];

            finishOrder = new int[nodeCount];
            finishCount = 0;

            dfsNodeStack = new int[nodeCount + 5];
            dfsEdgeStack = new int[nodeCount + 5];
            dfsStackSize = 0;

            int v = 0;
            while (v < nodeCount) {
                if (!visited[v]) {
                    buildFinishOrderIterative(v);
                }
                ++v;
            }

            componentId = new int[nodeCount];
            fillInt(componentId, -1);

            stack = new int[nodeCount + 5];
            stackSize = 0;

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

            int u = 0;
            while (u < nodeCount) {
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

            int sourceComponent = -1;
            int sourceCount = 0;

            int c = 0;
            while (c < componentCount) {
                if (indegree[c] == 0) {
                    ++sourceCount;
                    sourceComponent = c;
                }
                ++c;
            }

            if (sourceCount != 1) {
                output.append("Confused\n\n");
            } else {
                int i = 0;
                while (i < nodeCount) {
                    if (componentId[i] == sourceComponent) {
                        output.append(i).append('\n');
                    }
                    ++i;
                }
                output.append('\n');
            }

            ++tc;
        }

        System.out.print(output.toString());
    }

    private static void buildFinishOrderIterative(int start) {
        dfsNodeStack[0] = start;
        dfsEdgeStack[0] = headForward[start];
        dfsStackSize = 1;

        visited[start] = true;

        while (dfsStackSize > 0) {
            int node = dfsNodeStack[dfsStackSize - 1];
            int edge = dfsEdgeStack[dfsStackSize - 1];

            if (edge == -1) {
                --dfsStackSize;
                finishOrder[finishCount] = node;
                ++finishCount;
                continue;
            }

            dfsEdgeStack[dfsStackSize - 1] = nextForward[edge];

            int nextNode = toForward[edge];

            if (!visited[nextNode]) {
                visited[nextNode] = true;

                dfsNodeStack[dfsStackSize] = nextNode;
                dfsEdgeStack[dfsStackSize] = headForward[nextNode];
                ++dfsStackSize;
            }
        }
    }

    private static void assignComponentIterative(int start, int componentIndex) {
        stack[0] = start;
        stackSize = 1;

        componentId[start] = componentIndex;

        while (stackSize > 0) {
            --stackSize;

            int node = stack[stackSize];
            int edge = headReverse[node];

            while (edge != -1) {
                int nextNode = toReverse[edge];

                if (componentId[nextNode] == -1) {
                    componentId[nextNode] = componentIndex;

                    stack[stackSize] = nextNode;
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