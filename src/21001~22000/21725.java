import java.io.BufferedInputStream;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws Exception {
        FastInput input;
        StringBuilder output;

        input = new FastInput();
        output = new StringBuilder();

        int friendCount;
        int recordCount;

        friendCount = input.nextInt();
        recordCount = input.nextInt();

        int[] parent;
        int[] groupSize;
        long[] groupAdd;
        long[] diffToRoot;
        long[] personalAdd;

        parent = new int[friendCount + 1];
        groupSize = new int[friendCount + 1];
        groupAdd = new long[friendCount + 1];
        diffToRoot = new long[friendCount + 1];
        personalAdd = new long[friendCount + 1];

        for (int i = 1; i <= friendCount; i++) {
            parent[i] = i;
            groupSize[i] = 1;
            groupAdd[i] = 0L;
            diffToRoot[i] = 0L;
            personalAdd[i] = 0L;
        }

        for (int i = 0; i < recordCount; i++) {
            int recordType;
            int personX;

            recordType = input.nextInt();
            personX = input.nextInt();

            if (recordType == 1) {
                int personY;

                personY = input.nextInt();
                union(parent, groupSize, groupAdd, diffToRoot, personX, personY);
            } else {
                long cost;
                int root;
                int size;
                long share;

                cost = input.nextLong();
                root = find(parent, diffToRoot, personX);
                size = groupSize[root];
                share = cost / (long) size;

                groupAdd[root] -= share;
                personalAdd[personX] += cost;
            }
        }

        long[] balance;
        int[] debtorId;
        long[] debtorNeed;
        int[] creditorId;
        long[] creditorNeed;

        balance = new long[friendCount + 1];
        debtorId = new int[friendCount];
        debtorNeed = new long[friendCount];
        creditorId = new int[friendCount];
        creditorNeed = new long[friendCount];

        int debtorCount;
        int creditorCount;

        debtorCount = 0;
        creditorCount = 0;

        for (int i = 1; i <= friendCount; i++) {
            int root;
            long currentBalance;

            root = find(parent, diffToRoot, i);
            currentBalance = personalAdd[i] + groupAdd[root] + diffToRoot[i];
            balance[i] = currentBalance;

            if (currentBalance < 0L) {
                debtorId[debtorCount] = i;
                debtorNeed[debtorCount] = -currentBalance;
                debtorCount++;
            } else if (currentBalance > 0L) {
                creditorId[creditorCount] = i;
                creditorNeed[creditorCount] = currentBalance;
                creditorCount++;
            }
        }

        int[] transferFrom;
        int[] transferTo;
        long[] transferAmount;

        transferFrom = new int[friendCount];
        transferTo = new int[friendCount];
        transferAmount = new long[friendCount];

        int transferCount;
        int debtorIndex;
        int creditorIndex;

        transferCount = 0;
        debtorIndex = 0;
        creditorIndex = 0;

        while (debtorIndex < debtorCount && creditorIndex < creditorCount) {
            long payAmount;

            if (debtorNeed[debtorIndex] < creditorNeed[creditorIndex]) {
                payAmount = debtorNeed[debtorIndex];
            } else {
                payAmount = creditorNeed[creditorIndex];
            }

            transferFrom[transferCount] = debtorId[debtorIndex];
            transferTo[transferCount] = creditorId[creditorIndex];
            transferAmount[transferCount] = payAmount;
            transferCount++;

            debtorNeed[debtorIndex] -= payAmount;
            creditorNeed[creditorIndex] -= payAmount;

            if (debtorNeed[debtorIndex] == 0L) {
                debtorIndex++;
            }

            if (creditorNeed[creditorIndex] == 0L) {
                creditorIndex++;
            }
        }

        if (transferCount > friendCount) {
            System.out.print("-1\n");
            return;
        }

        output.append(transferCount).append('\n');
        for (int i = 0; i < transferCount; i++) {
            output.append(transferFrom[i]).append(' ');
            output.append(transferTo[i]).append(' ');
            output.append(transferAmount[i]).append('\n');
        }

        System.out.print(output.toString());
    }

    private static int find(int[] parent, long[] diffToRoot, int node) {
        int parentNode;

        parentNode = parent[node];
        if (parentNode == node) {
            return node;
        }

        int root;

        root = find(parent, diffToRoot, parentNode);
        diffToRoot[node] += diffToRoot[parentNode];
        parent[node] = root;

        return root;
    }

    private static void union(int[] parent, int[] groupSize, long[] groupAdd, long[] diffToRoot, int a, int b) {
        int rootA;
        int rootB;

        rootA = find(parent, diffToRoot, a);
        rootB = find(parent, diffToRoot, b);

        if (rootA == rootB) {
            return;
        }

        if (groupSize[rootA] < groupSize[rootB]) {
            int temp;
            temp = rootA;
            rootA = rootB;
            rootB = temp;
        }

        parent[rootB] = rootA;
        diffToRoot[rootB] = groupAdd[rootB] - groupAdd[rootA];
        groupSize[rootA] += groupSize[rootB];
    }

    private static final class FastInput {
        private final BufferedInputStream inputStream;

        private final byte[] buffer;

        private int bufferSize;
        private int bufferIndex;

        public FastInput() {
            this.inputStream = new BufferedInputStream(System.in);
            this.buffer = new byte[1 << 16];
            this.bufferSize = 0;
            this.bufferIndex = 0;
        }

        public int nextInt() throws IOException {
            int c;

            c = read();
            while (c <= ' ' && c != -1) {
                c = read();
            }

            int value;

            value = 0;
            while (c > ' ') {
                value = value * 10 + (c - '0');
                c = read();
            }

            return value;
        }

        public long nextLong() throws IOException {
            int c;

            c = read();
            while (c <= ' ' && c != -1) {
                c = read();
            }

            long value;

            value = 0L;
            while (c > ' ') {
                value = value * 10L + (long) (c - '0');
                c = read();
            }

            return value;
        }

        private int read() throws IOException {
            if (bufferIndex >= bufferSize) {
                bufferSize = inputStream.read(buffer);
                bufferIndex = 0;

                if (bufferSize <= 0) {
                    return -1;
                }
            }

            return buffer[bufferIndex++];
        }
    }
}