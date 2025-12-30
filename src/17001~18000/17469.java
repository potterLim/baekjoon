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

    private static final class IntIntHashMap {
        private static final int EMPTY_KEY = 0;

        private int[] keys;
        private int[] values;
        private int size;
        private int used;
        private int resizeThreshold;

        public IntIntHashMap(int expectedEntries) {
            int capacity = 1;
            int desired = expectedEntries * 2;
            if (desired < 2) {
                desired = 2;
            }
            while (capacity < desired) {
                capacity <<= 1;
            }

            keys = new int[capacity];
            values = new int[capacity];
            size = 0;
            used = 0;
            resizeThreshold = (int) (capacity * 0.7);
        }

        public int size() {
            return size;
        }

        public int[] keyTable() {
            return keys;
        }

        public int[] valueTable() {
            return values;
        }

        public boolean addTo(int key, int delta) {
            if (key == EMPTY_KEY) {
                return false;
            }

            if (used + 1 > resizeThreshold) {
                rehash();
            }

            int mask = keys.length - 1;
            int pos = mix(key) & mask;

            while (true) {
                int currentKey = keys[pos];
                if (currentKey == EMPTY_KEY) {
                    keys[pos] = key;
                    values[pos] = delta;
                    size++;
                    used++;
                    return true;
                }

                if (currentKey == key) {
                    values[pos] += delta;
                    return false;
                }

                pos = (pos + 1) & mask;
            }
        }

        private void rehash() {
            int[] oldKeys = keys;
            int[] oldValues = values;

            int newCapacity = oldKeys.length << 1;
            int[] newKeys = new int[newCapacity];
            int[] newValues = new int[newCapacity];
            int mask = newCapacity - 1;

            for (int i = 0; i < oldKeys.length; i++) {
                int key = oldKeys[i];
                if (key == EMPTY_KEY) {
                    continue;
                }

                int pos = mix(key) & mask;
                while (newKeys[pos] != EMPTY_KEY) {
                    pos = (pos + 1) & mask;
                }
                newKeys[pos] = key;
                newValues[pos] = oldValues[i];
            }

            keys = newKeys;
            values = newValues;
            used = size;
            resizeThreshold = (int) (newCapacity * 0.7);
        }

        private static int mix(int x) {
            x ^= (x >>> 16);
            x *= 0x7feb352d;
            x ^= (x >>> 15);
            x *= 0x846ca68b;
            x ^= (x >>> 16);
            return x;
        }
    }

    private static final class DisjointSetUnion {
        private final int[] parent;
        private final int[] componentSize;
        private final int[] distinctColorCount;
        private final IntIntHashMap[] colorCountMapByRoot;

        public DisjointSetUnion(int nodeCount, int[] colorOfNode) {
            parent = new int[nodeCount + 1];
            componentSize = new int[nodeCount + 1];
            distinctColorCount = new int[nodeCount + 1];
            colorCountMapByRoot = new IntIntHashMap[nodeCount + 1];

            for (int i = 1; i <= nodeCount; i++) {
                parent[i] = i;
                componentSize[i] = 1;

                IntIntHashMap map = new IntIntHashMap(1);
                map.addTo(colorOfNode[i], 1);
                colorCountMapByRoot[i] = map;
                distinctColorCount[i] = 1;
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

            if (componentSize[rootA] < componentSize[rootB]) {
                int temp = rootA;
                rootA = rootB;
                rootB = temp;
            }

            parent[rootB] = rootA;
            componentSize[rootA] += componentSize[rootB];

            IntIntHashMap bigMap = colorCountMapByRoot[rootA];
            IntIntHashMap smallMap = colorCountMapByRoot[rootB];

            int[] smallKeys = smallMap.keyTable();
            int[] smallValues = smallMap.valueTable();

            int addedDistinct = 0;

            for (int i = 0; i < smallKeys.length; i++) {
                int color = smallKeys[i];
                if (color == 0) {
                    continue;
                }

                boolean insertedNew = bigMap.addTo(color, smallValues[i]);
                if (insertedNew) {
                    addedDistinct++;
                }
            }

            distinctColorCount[rootA] += addedDistinct;
            colorCountMapByRoot[rootB] = null;
            distinctColorCount[rootB] = 0;
        }

        public int distinctCountOfComponent(int node) {
            int root = find(node);
            return distinctColorCount[root];
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

        int[] colorOfNode = new int[nodeCount + 1];
        for (int i = 1; i <= nodeCount; i++) {
            colorOfNode[i] = input.nextInt();
        }

        int operationCount = (nodeCount - 1) + queryCount;
        int[] operationType = new int[operationCount];
        int[] operationNode = new int[operationCount];

        for (int i = 0; i < operationCount; i++) {
            operationType[i] = input.nextInt();
            operationNode[i] = input.nextInt();
        }

        DisjointSetUnion dsu = new DisjointSetUnion(nodeCount, colorOfNode);

        int[] answers = new int[queryCount];
        int answerWriteIndex = 0;

        for (int i = operationCount - 1; i >= 0; i--) {
            int type = operationType[i];
            int node = operationNode[i];

            if (type == 2) {
                answers[answerWriteIndex] = dsu.distinctCountOfComponent(node);
                answerWriteIndex++;
            } else {
                int parent = parentOf[node];
                dsu.union(node, parent);
            }
        }

        StringBuilder output = new StringBuilder();
        for (int i = queryCount - 1; i >= 0; i--) {
            output.append(answers[i]).append('\n');
        }

        System.out.print(output.toString());
    }
}