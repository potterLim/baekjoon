import java.io.InputStream;
import java.util.Arrays;
import java.util.Comparator;

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

    private static final class Job {
        private final int index;
        private final int durationDays;
        private final int penaltyPerDay;

        public Job(int index, int durationDays, int penaltyPerDay) {
            this.index = index;
            this.durationDays = durationDays;
            this.penaltyPerDay = penaltyPerDay;
        }

        public int getIndex() {
            return index;
        }

        public int getDurationDays() {
            return durationDays;
        }

        public int getPenaltyPerDay() {
            return penaltyPerDay;
        }
    }

    public static void main(String[] args) throws Exception {
        FastInput input = new FastInput(System.in);

        int jobCount = input.nextInt();
        Job[] jobs = new Job[jobCount];

        for (int i = 0; i < jobCount; i++) {
            int durationDays = input.nextInt();
            int penaltyPerDay = input.nextInt();
            jobs[i] = new Job(i + 1, durationDays, penaltyPerDay);
        }

        Arrays.sort(jobs, new Comparator<Job>() {
            @Override
            public int compare(Job left, Job right) {
                long leftValue = (long) left.getDurationDays() * (long) right.getPenaltyPerDay();
                long rightValue = (long) right.getDurationDays() * (long) left.getPenaltyPerDay();

                if (leftValue < rightValue) {
                    return -1;
                } else if (leftValue > rightValue) {
                    return 1;
                }

                return Integer.compare(left.getIndex(), right.getIndex());
            }
        });

        StringBuilder output = new StringBuilder();
        for (int i = 0; i < jobCount; i++) {
            if (i > 0) {
                output.append(' ');
            }
            output.append(jobs[i].getIndex());
        }

        System.out.print(output.toString());
    }
}