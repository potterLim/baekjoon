import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public final class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.parseInt(reader.readLine().trim());
            long k = Long.parseLong(reader.readLine().trim());

            long low = 1;
            long high = (long) n * (long) n;
            long answer = 0;

            while (low <= high) {
                long mid = low + ((high - low) >>> 1);

                if (countLessOrEqual(mid, n) >= k) {
                    answer = mid;
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }

            System.out.println(answer);
        }
    }

    private static long countLessOrEqual(long value, int n) {
        long count = 0;

        for (int i = 1; i <= n; ++i) {
            long add = value / i;
            if (add > n) {
                add = n;
            }
            count += add;
        }

        return count;
    }
}
