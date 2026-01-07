import java.io.BufferedInputStream;
import java.io.IOException;

public class Main {
    private static int[] headForward;
    private static int[] toForward;
    private static int[] nextForward;

    private static int[] headReverse;
    private static int[] toReverse;
    private static int[] nextReverse;

    private static int[] cashAtNode;
    private static boolean[] isRestaurantNode;

    private static boolean[] visited;
    private static int[] finishOrder;
    private static int finishCount;

    private static int[] dfsNodeStack;
    private static int[] dfsEdgeStack;
    private static int dfsStackSize;

    private static int[] componentId;
    private static long[] componentCash;
    private static boolean[] componentHasRestaurant;
    private static int componentCount;

    private static int[] stack;
    private static int stackSize;

    private static int[] headComponent;
    private static int[] toComponent;
    private static int[] nextComponent;
    private static int componentEdgeCount;

    private static int[] indegree;

    public static void main(String[] args) throws Exception {
        FastScanner scanner = new FastScanner();

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

        int[] fromNode = new int[edgeCount];
        int[] toNode = new int[edgeCount];

        int e = 0;
        while (e < edgeCount) {
            int from = scanner.nextInt();
            int to = scanner.nextInt();

            fromNode[e] = from;
            toNode[e] = to;

            toForward[e] = to;
            nextForward[e] = headForward[from];
            headForward[from] = e;

            toReverse[e] = from;
            nextReverse[e] = headReverse[to];
            headReverse[to] = e;

            ++e;
        }

        cashAtNode = new int[nodeCount + 1];

        int v = 1;
        while (v <= nodeCount) {
            cashAtNode[v] = scanner.nextInt();
            ++v;
        }

        int startNode = scanner.nextInt();
        int restaurantCount = scanner.nextInt();

        isRestaurantNode = new boolean[nodeCount + 1];

        int r = 0;
        while (r < restaurantCount) {
            int restaurantNode = scanner.nextInt();
            isRestaurantNode[restaurantNode] = true;
            ++r;
        }

        visited = new boolean[nodeCount + 1];
        finishOrder = new int[nodeCount + 1];
        finishCount = 0;

        dfsNodeStack = new int[nodeCount + 5];
        dfsEdgeStack = new int[nodeCount + 5];
        dfsStackSize = 0;

        int start = 1;
        while (start <= nodeCount) {
            if (!visited[start]) {
                buildFinishOrderIterative(start);
            }
            ++start;
        }

        componentId = new int[nodeCount + 1];
        fillInt(componentId, -1);

        stack = new int[nodeCount + 5];
        stackSize = 0;

        componentCash = new long[nodeCount + 1];
        componentHasRestaurant = new boolean[nodeCount + 1];
        componentCount = 0;

        int idx = finishCount - 1;
        while (idx >= 0) {
            int root = finishOrder[idx];

            if (componentId[root] == -1) {
                assignComponentIterative(root, componentCount);
                ++componentCount;
            }

            --idx;
        }

        headComponent = new int[componentCount];
        fillInt(headComponent, -1);

        toComponent = new int[edgeCount];
        nextComponent = new int[edgeCount];
        componentEdgeCount = 0;

        indegree = new int[componentCount];

        int i = 0;
        while (i < edgeCount) {
            int u = fromNode[i];
            int w = toNode[i];

            int cu = componentId[u];
            int cw = componentId[w];

            if (cu != cw) {
                toComponent[componentEdgeCount] = cw;
                nextComponent[componentEdgeCount] = headComponent[cu];
                headComponent[cu] = componentEdgeCount;
                ++componentEdgeCount;

                ++indegree[cw];
            }

            ++i;
        }

        int startComponent = componentId[startNode];

        long[] best = new long[componentCount];
        fillLong(best, -1L);
        best[startComponent] = componentCash[startComponent];

        int[] queue = new int[componentCount];
        int front = 0;
        int back = 0;

        int c = 0;
        while (c < componentCount) {
            if (indegree[c] == 0) {
                queue[back] = c;
                ++back;
            }
            ++c;
        }

        while (front < back) {
            int current = queue[front];
            ++front;

            int edge = headComponent[current];

            while (edge != -1) {
                int next = toComponent[edge];

                if (best[current] != -1L) {
                    long candidate = best[current] + componentCash[next];

                    if (best[next] < candidate) {
                        best[next] = candidate;
                    }
                }

                --indegree[next];
                if (indegree[next] == 0) {
                    queue[back] = next;
                    ++back;
                }

                edge = nextComponent[edge];
            }
        }

        long answer = 0L;

        int comp = 0;
        while (comp < componentCount) {
            if (componentHasRestaurant[comp] && best[comp] > answer) {
                answer = best[comp];
            }
            ++comp;
        }

        System.out.print(Long.toString(answer));
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

        long sumCash = 0L;
        boolean hasRestaurant = false;

        while (stackSize > 0) {
            --stackSize;

            int node = stack[stackSize];

            sumCash += cashAtNode[node];
            if (isRestaurantNode[node]) {
                hasRestaurant = true;
            }

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

        componentCash[componentIndex] = sumCash;
        componentHasRestaurant[componentIndex] = hasRestaurant;
    }

    private static void fillInt(int[] array, int value) {
        int i = 0;

        while (i < array.length) {
            array[i] = value;
            ++i;
        }
    }

    private static void fillLong(long[] array, long value) {
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