public class Main {
    private static final class FastInput {
        private final byte[] buffer = new byte[1 << 16];
        private int bufferIndex;
        private int bufferSize;

        private int readByte() throws Exception {
            if (bufferIndex >= bufferSize) {
                bufferSize = System.in.read(buffer);
                bufferIndex = 0;
                if (bufferSize <= 0) {
                    return -1;
                }
            }
            return buffer[bufferIndex++];
        }

        public int nextInt() throws Exception {
            int c;
            do {
                c = readByte();
            } while (c <= ' ');

            int sign = 1;
            if (c == '-') {
                sign = -1;
                c = readByte();
            }

            int value = 0;
            while (c > ' ') {
                value = value * 10 + (c - '0');
                c = readByte();
            }

            return value * sign;
        }
    }

    private static final class DisjointSetUnion {
        private final int[] parent;
        private final int[] size;

        public DisjointSetUnion(int n) {
            parent = new int[n + 1];
            size = new int[n + 1];

            for (int i = 1; i <= n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        public int find(int x) {
            int root = x;
            while (root != parent[root]) {
                root = parent[root];
            }

            int current = x;
            while (current != root) {
                int next = parent[current];
                parent[current] = root;
                current = next;
            }

            return root;
        }

        public void union(int a, int b) {
            int rootA = find(a);
            int rootB = find(b);

            if (rootA == rootB) {
                return;
            }

            if (size[rootA] < size[rootB]) {
                int temp = rootA;
                rootA = rootB;
                rootB = temp;
            }

            parent[rootB] = rootA;
            size[rootA] += size[rootB];
        }

        public boolean isConnected(int a, int b) {
            return find(a) == find(b);
        }
    }

    private static final class Operation {
        private final int type;
        private final int first;
        private final int second;

        public Operation(int type, int first, int second) {
            this.type = type;
            this.first = first;
            this.second = second;
        }
    }

    public static void main(String[] args) throws Exception {
        FastInput input = new FastInput();

        int nodeCount = input.nextInt();
        int queryCount = input.nextInt();

        int[] parentOf = new int[nodeCount + 1];
        parentOf[1] = 0;
        for (int i = 2; i <= nodeCount; i++) {
            parentOf[i] = input.nextInt();
        }

        int operationCount = (nodeCount - 1) + queryCount;
        Operation[] operations = new Operation[operationCount];
        boolean[] willBeRemoved = new boolean[nodeCount + 1];

        for (int i = 0; i < operationCount; i++) {
            int type = input.nextInt();
            if (type == 0) {
                int child = input.nextInt();
                willBeRemoved[child] = true;
                operations[i] = new Operation(0, child, 0);
            } else {
                int u = input.nextInt();
                int v = input.nextInt();
                operations[i] = new Operation(1, u, v);
            }
        }

        DisjointSetUnion dsu = new DisjointSetUnion(nodeCount);

        for (int i = 2; i <= nodeCount; i++) {
            if (!willBeRemoved[i]) {
                dsu.union(i, parentOf[i]);
            }
        }

        String[] answers = new String[queryCount];
        int answerIndex = 0;

        for (int i = operationCount - 1; i >= 0; i--) {
            Operation op = operations[i];

            if (op.type == 1) {
                boolean connected = dsu.isConnected(op.first, op.second);
                if (connected) {
                    answers[answerIndex] = "YES";
                } else {
                    answers[answerIndex] = "NO";
                }
                answerIndex++;
            } else {
                int child = op.first;
                dsu.union(child, parentOf[child]);
            }
        }

        StringBuilder output = new StringBuilder();
        for (int i = queryCount - 1; i >= 0; i--) {
            output.append(answers[i]).append('\n');
        }

        System.out.print(output.toString());
    }
}