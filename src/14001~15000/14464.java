import java.io.InputStream;
import java.util.Arrays;

public class Main {
    private static final class FastInput {
        private final InputStream in;
        private final byte[] buffer;
        private int bufferSize;
        private int bufferIndex;

        public FastInput(InputStream in) {
            this.in = in;
            this.buffer = new byte[1 << 16];
        }

        private int readByte() throws Exception {
            if (bufferIndex >= bufferSize) {
                bufferSize = in.read(buffer);
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

    private static final class MinHeapInt {
        private final int[] heap;
        private int size;

        public MinHeapInt(int capacity) {
            heap = new int[capacity + 1];
            size = 0;
        }

        public int size() {
            return size;
        }

        public void push(int value) {
            int index = ++size;
            heap[index] = value;

            while (index > 1) {
                int parentIndex = index / 2;
                if (heap[parentIndex] <= heap[index]) {
                    break;
                }

                int temp = heap[parentIndex];
                heap[parentIndex] = heap[index];
                heap[index] = temp;

                index = parentIndex;
            }
        }

        public int peek() {
            return heap[1];
        }

        public int pop() {
            int result = heap[1];
            int last = heap[size--];

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

                int temp = heap[index];
                heap[index] = heap[smallerChild];
                heap[smallerChild] = temp;

                index = smallerChild;
            }

            return result;
        }
    }

    public static void main(String[] args) throws Exception {
        FastInput input = new FastInput(System.in);

        int chickenCount = input.nextInt();
        int cowCount = input.nextInt();

        int[] chickenTimes = new int[chickenCount];
        for (int i = 0; i < chickenCount; i++) {
            chickenTimes[i] = input.nextInt();
        }
        Arrays.sort(chickenTimes);

        long[] cowIntervals = new long[cowCount];
        for (int i = 0; i < cowCount; i++) {
            int startTime = input.nextInt();
            int endTime = input.nextInt();
            cowIntervals[i] = (((long) startTime) << 32) | (endTime & 0xffffffffL);
        }
        Arrays.sort(cowIntervals);

        MinHeapInt candidateEndTimes = new MinHeapInt(cowCount);

        int cowIndex = 0;
        int matchedCount = 0;

        for (int i = 0; i < chickenCount; i++) {
            int chickenTime = chickenTimes[i];

            while (cowIndex < cowCount) {
                int cowStart = (int) (cowIntervals[cowIndex] >>> 32);
                if (cowStart > chickenTime) {
                    break;
                }

                int cowEnd = (int) cowIntervals[cowIndex];
                candidateEndTimes.push(cowEnd);
                cowIndex++;
            }

            while (candidateEndTimes.size() > 0 && candidateEndTimes.peek() < chickenTime) {
                candidateEndTimes.pop();
            }

            if (candidateEndTimes.size() > 0) {
                candidateEndTimes.pop();
                matchedCount++;
            }
        }

        System.out.print(matchedCount);
    }
}