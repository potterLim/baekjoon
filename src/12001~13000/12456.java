import java.io.BufferedInputStream;
import java.util.Arrays;
import java.util.Comparator;

public class Main {

    private static final class FastInput {
        private final BufferedInputStream in;
        private final byte[] buffer;
        private int bufferSize;
        private int bufferIndex;

        public FastInput() {
            in = new BufferedInputStream(System.in);
            buffer = new byte[1 << 16];
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

        public long nextLong() throws Exception {
            int c;
            do {
                c = readByte();
            } while (c <= ' ');

            long value = 0;
            while (c > ' ') {
                value = value * 10L + (long) (c - '0');
                c = readByte();
            }
            return value;
        }

        public int nextInt() throws Exception {
            return (int) nextLong();
        }
    }

    private static final class CoffeeType {
        private final long cups;
        private final long deadline;
        private final int satisfaction;

        public CoffeeType(long cups, long deadline, int satisfaction) {
            this.cups = cups;
            this.deadline = deadline;
            this.satisfaction = satisfaction;
        }

        public long getCups() {
            return cups;
        }

        public long getDeadline() {
            return deadline;
        }

        public int getSatisfaction() {
            return satisfaction;
        }
    }

    public static void main(String[] args) throws Exception {
        FastInput input = new FastInput();

        int testCount = input.nextInt();
        StringBuilder output = new StringBuilder();

        for (int testIndex = 1; testIndex <= testCount; testIndex++) {
            int typeCount = input.nextInt();
            long totalDays = input.nextLong();

            CoffeeType[] types = new CoffeeType[typeCount];

            for (int i = 0; i < typeCount; i++) {
                long cups = input.nextLong();
                long deadline = input.nextLong();
                int satisfaction = input.nextInt();

                long effectiveDeadline = deadline;
                if (effectiveDeadline > totalDays) {
                    effectiveDeadline = totalDays;
                }

                types[i] = new CoffeeType(cups, effectiveDeadline, satisfaction);
            }

            Arrays.sort(types, new Comparator<CoffeeType>() {
                @Override
                public int compare(CoffeeType a, CoffeeType b) {
                    if (a.getDeadline() < b.getDeadline()) {
                        return -1;
                    } else if (a.getDeadline() > b.getDeadline()) {
                        return 1;
                    }
                    return 0;
                }
            });

            long[] chosenCountBySatisfaction = new long[1001];
            long chosenTotalCount = 0L;
            long chosenTotalSatisfaction = 0L;

            for (int i = 0; i < typeCount; i++) {
                long cups = types[i].getCups();
                long deadline = types[i].getDeadline();
                int satisfaction = types[i].getSatisfaction();

                if (deadline == 0L) {
                    continue;
                }

                chosenCountBySatisfaction[satisfaction] += cups;
                chosenTotalCount += cups;
                chosenTotalSatisfaction += cups * (long) satisfaction;

                if (chosenTotalCount > deadline) {
                    long overflow = chosenTotalCount - deadline;

                    for (int j = 1; j <= 1000 && overflow > 0L; j++) {
                        long available = chosenCountBySatisfaction[j];
                        if (available == 0L) {
                            continue;
                        }

                        long removeCount = available;
                        if (removeCount > overflow) {
                            removeCount = overflow;
                        }

                        chosenCountBySatisfaction[j] -= removeCount;
                        chosenTotalCount -= removeCount;
                        chosenTotalSatisfaction -= removeCount * (long) j;
                        overflow -= removeCount;
                    }
                }
            }

            output.append("Case #");
            output.append(testIndex);
            output.append(": ");
            output.append(chosenTotalSatisfaction);
            output.append('\n');
        }

        System.out.print(output.toString());
    }
}