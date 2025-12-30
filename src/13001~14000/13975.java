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

    private static final class MinHeapLong {
        private long[] heap;
        private int size;

        public MinHeapLong(int capacity) {
            heap = new long[capacity + 1];
            size = 0;
        }

        public int size() {
            return size;
        }

        public void push(long value) {
            int index = ++size;
            heap[index] = value;

            while (index > 1) {
                int parentIndex = index / 2;
                if (heap[parentIndex] <= heap[index]) {
                    break;
                }

                long temp = heap[parentIndex];
                heap[parentIndex] = heap[index];
                heap[index] = temp;

                index = parentIndex;
            }
        }

        public long pop() {
            long result = heap[1];
            long last = heap[size--];

            if (size == 0) {
                return result;
            }

            heap[1] = last;

            int index = 1;
            while (true) {
                int left = index * 2;
                if (left > size) {
                    break;
                }

                int right = left + 1;
                int smallerChild = left;

                if (right <= size && heap[right] < heap[left]) {
                    smallerChild = right;
                }

                if (heap[index] <= heap[smallerChild]) {
                    break;
                }

                long temp = heap[index];
                heap[index] = heap[smallerChild];
                heap[smallerChild] = temp;

                index = smallerChild;
            }

            return result;
        }
    }

    public static void main(String[] args) throws Exception {
        FastInput input = new FastInput();
        StringBuilder output = new StringBuilder();

        int testCaseCount = input.nextInt();

        for (int i = 0; i < testCaseCount; i++) {
            int chapterCount = input.nextInt();

            MinHeapLong minHeap = new MinHeapLong(chapterCount);

            for (int j = 0; j < chapterCount; j++) {
                minHeap.push(input.nextInt());
            }

            long totalCost = 0;

            while (minHeap.size() > 1) {
                long first = minHeap.pop();
                long second = minHeap.pop();
                long mergedSize = first + second;

                totalCost += mergedSize;
                minHeap.push(mergedSize);
            }

            output.append(totalCost).append('\n');
        }

        System.out.print(output.toString());
    }
}