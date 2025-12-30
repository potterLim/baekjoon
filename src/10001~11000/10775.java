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

    private static int findAvailableGate(int gate, int[] parent) {
        int root = gate;
        while (root != parent[root]) {
            root = parent[root];
        }

        int current = gate;
        while (current != root) {
            int next = parent[current];
            parent[current] = root;
            current = next;
        }

        return root;
    }

    public static void main(String[] args) throws Exception {
        FastInput input = new FastInput();

        int gateCount = input.nextInt();
        int planeCount = input.nextInt();

        int[] parent = new int[gateCount + 1];
        for (int i = 0; i <= gateCount; i++) {
            parent[i] = i;
        }

        int dockedPlaneCount = 0;

        for (int i = 0; i < planeCount; i++) {
            int maxGateAllowed = input.nextInt();

            int chosenGate = findAvailableGate(maxGateAllowed, parent);
            if (chosenGate == 0) {
                break;
            }

            int nextAvailable = findAvailableGate(chosenGate - 1, parent);
            parent[chosenGate] = nextAvailable;

            dockedPlaneCount++;
        }

        System.out.print(dockedPlaneCount);
    }
}