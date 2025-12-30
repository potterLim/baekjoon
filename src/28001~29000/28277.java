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

    private static final class IntHashSet {
        private static final int EMPTY = 0;

        private int[] slots;
        private int elementCount;
        private int resizeThreshold;

        public IntHashSet(int expectedCount) {
            int capacity = 1;
            int desired = expectedCount * 2;
            while (capacity < desired) {
                capacity <<= 1;
            }

            slots = new int[capacity];
            resizeThreshold = (int) (capacity * 0.7);
            elementCount = 0;
        }

        public int size() {
            return elementCount;
        }

        public void clear() {
            slots = new int[1];
            resizeThreshold = 0;
            elementCount = 0;
        }

        public void add(int value) {
            if (value == EMPTY) {
                return;
            }

            if (elementCount + 1 > resizeThreshold) {
                grow();
            }

            int mask = slots.length - 1;
            int position = mix(value) & mask;

            while (true) {
                int current = slots[position];

                if (current == EMPTY) {
                    slots[position] = value;
                    elementCount++;
                    return;
                }

                if (current == value) {
                    return;
                }

                position = (position + 1) & mask;
            }
        }

        public void addAllFrom(IntHashSet source) {
            int[] sourceSlots = source.slots;
            for (int i = 0; i < sourceSlots.length; i++) {
                int value = sourceSlots[i];
                if (value != EMPTY) {
                    add(value);
                }
            }
        }

        private void grow() {
            int[] oldSlots = slots;
            int newCapacity = oldSlots.length << 1;
            int[] newSlots = new int[newCapacity];
            int mask = newCapacity - 1;

            for (int i = 0; i < oldSlots.length; i++) {
                int value = oldSlots[i];
                if (value == EMPTY) {
                    continue;
                }

                int position = mix(value) & mask;
                while (newSlots[position] != EMPTY) {
                    position = (position + 1) & mask;
                }
                newSlots[position] = value;
            }

            slots = newSlots;
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

    public static void main(String[] args) throws Exception {
        FastInput input = new FastInput();

        int setCount = input.nextInt();
        int queryCount = input.nextInt();

        IntHashSet[] setsById = new IntHashSet[setCount + 1];

        for (int i = 1; i <= setCount; i++) {
            int initialSize = input.nextInt();
            IntHashSet set = new IntHashSet(initialSize);

            for (int j = 0; j < initialSize; j++) {
                set.add(input.nextInt());
            }

            setsById[i] = set;
        }

        StringBuilder output = new StringBuilder();

        for (int i = 0; i < queryCount; i++) {
            int commandType = input.nextInt();

            if (commandType == 1) {
                int destinationId = input.nextInt();
                int sourceId = input.nextInt();

                if (destinationId == sourceId) {
                    continue;
                }

                IntHashSet destinationSet = setsById[destinationId];
                IntHashSet sourceSet = setsById[sourceId];

                if (sourceSet.size() == 0) {
                    continue;
                }

                if (destinationSet.size() < sourceSet.size()) {
                    setsById[destinationId] = sourceSet;
                    setsById[sourceId] = destinationSet;

                    destinationSet = setsById[destinationId];
                    sourceSet = setsById[sourceId];
                }

                destinationSet.addAllFrom(sourceSet);
                sourceSet.clear();
            } else {
                int targetId = input.nextInt();
                output.append(setsById[targetId].size()).append('\n');
            }
        }

        System.out.print(output.toString());
    }
}