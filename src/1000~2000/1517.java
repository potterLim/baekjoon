import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();

        long[] values = new long[n];
        long[] sorted = new long[n];

        for (int i = 0; i < n; ++i) {
            long value = scanner.nextLong();
            values[i] = value;
            sorted[i] = value;
        }

        Arrays.sort(sorted);

        int uniqueCount = 0;
        for (int i = 0; i < n; ++i) {
            if (i == 0 || sorted[i] != sorted[i - 1]) {
                sorted[uniqueCount] = sorted[i];
                ++uniqueCount;
            }
        }

        Fenwick fenwick = new Fenwick(uniqueCount);
        long inversionCount = 0L;

        for (int i = 0; i < n; ++i) {
            long value = values[i];
            int rank = lowerBound(sorted, uniqueCount, value) + 1;

            long lessOrEqualCount = fenwick.sum(rank);
            long seenCount = i;
            inversionCount += (seenCount - lessOrEqualCount);

            fenwick.add(rank, 1L);
        }

        System.out.print(inversionCount);
    }

    private static int lowerBound(long[] array, int length, long target) {
        int left = 0;
        int right = length;

        while (left < right) {
            int mid = (left + right) >>> 1;
            if (array[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }

    private static final class Fenwick {
        private final long[] tree;

        public Fenwick(int size) {
            this.tree = new long[size + 1];
        }

        public void add(int index, long delta) {
            int i = index;

            while (i < tree.length) {
                tree[i] += delta;
                i += (i & -i);
            }
        }

        public long sum(int index) {
            long result = 0L;
            int i = index;

            while (i > 0) {
                result += tree[i];
                i -= (i & -i);
            }

            return result;
        }
    }
}