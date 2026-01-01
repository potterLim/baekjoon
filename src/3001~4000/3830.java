import java.io.BufferedInputStream;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws Exception {
        InputReader inputReader;
        StringBuilder output;

        inputReader = new InputReader();
        output = new StringBuilder();

        while (true) {
            int sampleCount;
            int operationCount;

            sampleCount = inputReader.nextInt();
            operationCount = inputReader.nextInt();

            if (sampleCount == 0 && operationCount == 0) {
                break;
            }

            int[] parent;
            int[] componentSize;
            long[] weightToParent;

            parent = new int[sampleCount + 1];
            componentSize = new int[sampleCount + 1];
            weightToParent = new long[sampleCount + 1];

            for (int i = 1; i <= sampleCount; i++) {
                parent[i] = i;
                componentSize[i] = 1;
                weightToParent[i] = 0L;
            }

            for (int i = 0; i < operationCount; i++) {
                char operationType;

                operationType = inputReader.nextNonSpaceChar();

                if (operationType == '!') {
                    int lighterSample;
                    int heavierSample;
                    int deltaWeight;

                    lighterSample = inputReader.nextInt();
                    heavierSample = inputReader.nextInt();
                    deltaWeight = inputReader.nextInt();

                    union(parent, componentSize, weightToParent, lighterSample, heavierSample, (long) deltaWeight);
                } else {
                    int firstSample;
                    int secondSample;

                    firstSample = inputReader.nextInt();
                    secondSample = inputReader.nextInt();

                    int rootFirst;
                    int rootSecond;

                    rootFirst = find(parent, weightToParent, firstSample);
                    rootSecond = find(parent, weightToParent, secondSample);

                    if (rootFirst != rootSecond) {
                        output.append("UNKNOWN\n");
                    } else {
                        long difference;

                        difference = weightToParent[secondSample] - weightToParent[firstSample];
                        output.append(difference).append('\n');
                    }
                }
            }
        }

        System.out.print(output.toString());
    }

    private static int find(int[] parent, long[] weightToParent, int node) {
        int currentParent;

        currentParent = parent[node];
        if (currentParent == node) {
            return node;
        }

        int root;

        root = find(parent, weightToParent, currentParent);
        weightToParent[node] += weightToParent[currentParent];
        parent[node] = root;

        return root;
    }

    private static void union(int[] parent, int[] componentSize, long[] weightToParent, int lighterSample, int heavierSample, long deltaWeight) {
        int rootLighter;
        int rootHeavier;

        rootLighter = find(parent, weightToParent, lighterSample);
        rootHeavier = find(parent, weightToParent, heavierSample);

        if (rootLighter == rootHeavier) {
            return;
        }

        long lighterToRoot;
        long heavierToRoot;

        lighterToRoot = weightToParent[lighterSample];
        heavierToRoot = weightToParent[heavierSample];

        if (componentSize[rootLighter] < componentSize[rootHeavier]) {
            parent[rootLighter] = rootHeavier;
            weightToParent[rootLighter] = -deltaWeight - lighterToRoot + heavierToRoot;
            componentSize[rootHeavier] += componentSize[rootLighter];
        } else {
            parent[rootHeavier] = rootLighter;
            weightToParent[rootHeavier] = deltaWeight + lighterToRoot - heavierToRoot;
            componentSize[rootLighter] += componentSize[rootHeavier];
        }
    }

    private static final class InputReader {
        private final BufferedInputStream inputStream;

        private final byte[] buffer;
        private int bufferSize;
        private int bufferIndex;

        public InputReader() {
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

            int sign;
            sign = 1;

            if (c == '-') {
                sign = -1;
                c = read();
            }

            int value;
            value = 0;

            while (c > ' ') {
                value = value * 10 + (c - '0');
                c = read();
            }

            return value * sign;
        }

        public char nextNonSpaceChar() throws IOException {
            int c;

            c = read();
            while (c <= ' ' && c != -1) {
                c = read();
            }

            return (char) c;
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