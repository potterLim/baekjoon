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

            int value = 0;
            while (c > ' ') {
                value = value * 10 + (c - '0');
                c = readByte();
            }
            return value;
        }
    }

    private static final class FindResult {
        private int root;
        private int parityToRoot;
    }

    private static final class BipartiteDsu {
        private final int[] parent;
        private final int[] componentSize;
        private final int[] parityToParent;
        private final boolean[] isBipartite;

        private long nonBipartiteVertexCount;

        public BipartiteDsu(int nodeCount) {
            parent = new int[nodeCount + 1];
            componentSize = new int[nodeCount + 1];
            parityToParent = new int[nodeCount + 1];
            isBipartite = new boolean[nodeCount + 1];

            for (int i = 1; i <= nodeCount; i++) {
                parent[i] = i;
                componentSize[i] = 1;
                parityToParent[i] = 0;
                isBipartite[i] = true;
            }

            nonBipartiteVertexCount = 0;
        }

        public long getNonBipartiteVertexCount() {
            return nonBipartiteVertexCount;
        }

        private FindResult findWithParity(int x) {
            int current = x;
            int parity = 0;

            while (current != parent[current]) {
                parity ^= parityToParent[current];
                current = parent[current];
            }

            int root = current;

            current = x;
            int pathParity = 0;
            while (current != parent[current]) {
                int next = parent[current];
                int edgeParity = parityToParent[current];

                parent[current] = root;
                parityToParent[current] = parity ^ pathParity;

                pathParity ^= edgeParity;
                current = next;
            }

            FindResult result = new FindResult();
            result.root = root;
            result.parityToRoot = parity;
            return result;
        }

        public void addEdge(int u, int v) {
            FindResult uInfo = findWithParity(u);
            FindResult vInfo = findWithParity(v);

            int rootU = uInfo.root;
            int rootV = vInfo.root;

            int parityU = uInfo.parityToRoot;
            int parityV = vInfo.parityToRoot;

            if (rootU == rootV) {
                int impliedParity = parityU ^ parityV;
                if (impliedParity == 1) {
                    return;
                }

                if (isBipartite[rootU]) {
                    isBipartite[rootU] = false;
                    nonBipartiteVertexCount += componentSize[rootU];
                }
                return;
            }

            if (!isBipartite[rootU]) {
                nonBipartiteVertexCount -= componentSize[rootU];
            }
            if (!isBipartite[rootV]) {
                nonBipartiteVertexCount -= componentSize[rootV];
            }

            if (componentSize[rootU] < componentSize[rootV]) {
                int tempRoot = rootU;
                rootU = rootV;
                rootV = tempRoot;

                int tempParity = parityU;
                parityU = parityV;
                parityV = tempParity;
            }

            parent[rootV] = rootU;
            parityToParent[rootV] = parityU ^ parityV ^ 1;
            componentSize[rootU] += componentSize[rootV];
            isBipartite[rootU] = isBipartite[rootU] && isBipartite[rootV];

            if (!isBipartite[rootU]) {
                nonBipartiteVertexCount += componentSize[rootU];
            }
        }
    }

    public static void main(String[] args) throws Exception {
        FastInput input = new FastInput();

        int nodeCount = input.nextInt();
        int queryCount = input.nextInt();

        BipartiteDsu dsu = new BipartiteDsu(nodeCount);

        StringBuilder output = new StringBuilder();

        for (int i = 0; i < queryCount; i++) {
            int a = input.nextInt();
            int b = input.nextInt();

            dsu.addEdge(a, b);
            output.append(dsu.getNonBipartiteVertexCount()).append('\n');
        }

        System.out.print(output.toString());
    }
}