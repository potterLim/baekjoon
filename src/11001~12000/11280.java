import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int variableCount = scanner.nextInt();
        int clauseCount = scanner.nextInt();

        int nodeCount = variableCount * 2;

        int[] head = new int[nodeCount];
        int[] to = new int[clauseCount * 2];
        int[] next = new int[clauseCount * 2];

        FillInt(head, -1);

        int edgeCount = 0;

        int i = 0;
        while (i < clauseCount) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();

            int aNode = LiteralToNode(a, variableCount);
            int bNode = LiteralToNode(b, variableCount);

            int notA = aNode ^ 1;
            int notB = bNode ^ 1;

            edgeCount = AddEdge(head, to, next, edgeCount, notA, bNode);
            edgeCount = AddEdge(head, to, next, edgeCount, notB, aNode);

            ++i;
        }

        int[] index = new int[nodeCount];
        int[] low = new int[nodeCount];
        boolean[] onStack = new boolean[nodeCount];

        FillInt(index, -1);

        int[] stack = new int[nodeCount];
        int stackSize = 0;

        int[] callNode = new int[nodeCount * 2];
        int[] callEdge = new int[nodeCount * 2];
        int callSize = 0;

        int[] parent = new int[nodeCount];
        FillInt(parent, -1);

        int[] componentId = new int[nodeCount];
        FillInt(componentId, -1);

        int time = 0;
        int componentCount = 0;

        int start = 0;
        while (start < nodeCount) {
            if (index[start] != -1) {
                ++start;
                continue;
            }

            callNode[callSize] = start;
            callEdge[callSize] = head[start];
            parent[start] = -1;
            ++callSize;

            while (callSize > 0) {
                int v = callNode[callSize - 1];

                if (index[v] == -1) {
                    index[v] = time;
                    low[v] = time;
                    ++time;

                    stack[stackSize] = v;
                    ++stackSize;
                    onStack[v] = true;
                }

                int e = callEdge[callSize - 1];

                if (e != -1) {
                    callEdge[callSize - 1] = next[e];

                    int w = to[e];

                    if (index[w] == -1) {
                        parent[w] = v;

                        callNode[callSize] = w;
                        callEdge[callSize] = head[w];
                        ++callSize;
                        continue;
                    }

                    if (onStack[w]) {
                        if (low[v] > index[w]) {
                            low[v] = index[w];
                        }
                    }

                    continue;
                }

                --callSize;

                if (parent[v] != -1) {
                    int p = parent[v];
                    if (low[p] > low[v]) {
                        low[p] = low[v];
                    }
                }

                if (low[v] == index[v]) {
                    while (true) {
                        --stackSize;
                        int u = stack[stackSize];
                        onStack[u] = false;
                        componentId[u] = componentCount;

                        if (u == v) {
                            break;
                        }
                    }

                    ++componentCount;
                }
            }

            ++start;
        }

        int var = 1;
        while (var <= variableCount) {
            int trueNode = LiteralToNode(var, variableCount);
            int falseNode = trueNode ^ 1;

            if (componentId[trueNode] == componentId[falseNode]) {
                System.out.print("0");
                return;
            }

            ++var;
        }

        System.out.print("1");
    }

    private static int LiteralToNode(int literal, int variableCount) {
        int v = literal;
        int idx;
        int isNeg;

        if (v < 0) {
            idx = -v - 1;
            isNeg = 1;
        } else {
            idx = v - 1;
            isNeg = 0;
        }

        return (idx << 1) | isNeg;
    }

    private static int AddEdge(int[] head, int[] to, int[] next, int edgeCount, int from, int dest) {
        to[edgeCount] = dest;
        next[edgeCount] = head[from];
        head[from] = edgeCount;
        return edgeCount + 1;
    }

    private static void FillInt(int[] array, int value) {
        int i = 0;
        while (i < array.length) {
            array[i] = value;
            ++i;
        }
    }
}