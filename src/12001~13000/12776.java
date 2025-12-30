import java.io.InputStream;
import java.util.Arrays;

public class Main {

    private static final class FastInput {
        private final byte[] buffer = new byte[1 << 16];
        private int idx = 0, size = 0;

        private int readByte() throws Exception {
            if (idx >= size) {
                size = System.in.read(buffer);
                idx = 0;
                if (size <= 0) return -1;
            }
            return buffer[idx++];
        }

        public int nextInt() throws Exception {
            int c;
            do {
                c = readByte();
            } while (c <= ' ');

            int val = 0;
            while (c > ' ') {
                val = val * 10 + (c - '0');
                c = readByte();
            }
            return val;
        }
    }

    private static final class Job {
        int need;
        int gain;

        Job(int need, int gain) {
            this.need = need;
            this.gain = gain;
        }
    }

    public static void main(String[] args) throws Exception {
        FastInput in = new FastInput();

        int n = in.nextInt();
        Job[] jobs = new Job[n];

        for (int i = 0; i < n; i++) {
            int a = in.nextInt();
            int b = in.nextInt();
            jobs[i] = new Job(a, b);
        }

        Job[] good = new Job[n];
        Job[] bad = new Job[n];
        int goodCnt = 0, badCnt = 0;

        for (int i = 0; i < n; i++) {
            if (jobs[i].need <= jobs[i].gain) {
                good[goodCnt++] = jobs[i];
            } else {
                bad[badCnt++] = jobs[i];
            }
        }

        Arrays.sort(good, 0, goodCnt, (x, y) -> x.need - y.need);
        Arrays.sort(bad, 0, badCnt, (x, y) -> y.gain - x.gain);

        long currentSpace = 0;
        long requiredExtra = 0;

        for (int i = 0; i < goodCnt; i++) {
            if (currentSpace < good[i].need) {
                requiredExtra += (good[i].need - currentSpace);
                currentSpace = good[i].need;
            }
            currentSpace = currentSpace - good[i].need + good[i].gain;
        }

        for (int i = 0; i < badCnt; i++) {
            if (currentSpace < bad[i].need) {
                requiredExtra += (bad[i].need - currentSpace);
                currentSpace = bad[i].need;
            }
            currentSpace = currentSpace - bad[i].need + bad[i].gain;
        }

        System.out.println(requiredExtra);
    }
}