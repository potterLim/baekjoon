import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        long[] a = new long[n];

        long minValue = Long.MAX_VALUE;
        long maxValue = 0L;

        for (int i = 0; i < n; ++i) {
            a[i] = scanner.nextLong();

            if (a[i] < minValue) {
                minValue = a[i];
            }

            if (a[i] > maxValue) {
                maxValue = a[i];
            }
        }

        long k = scanner.nextLong();

        long minRequiredHeight = 0L;

        for (int i = 0; i < n - 1; ++i) {
            if (a[i] > a[i + 1] && a[i] > minRequiredHeight) {
                minRequiredHeight = a[i];
            }
        }

        long startHeight = minRequiredHeight;

        if (startHeight <= minValue) {
            startHeight = minValue + 1;
        }

        if (getCost(a, startHeight, k) > k) {
            System.out.println(0);
            return;
        }

        long left = startHeight;
        long right = maxValue + k;
        long lastPossibleHeight = startHeight - 1;

        while (left <= right) {
            long mid = left + (right - left) / 2;
            long cost = getCost(a, mid, k);

            if (cost <= k) {
                lastPossibleHeight = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        System.out.println(lastPossibleHeight - startHeight + 1);
    }

    private static long getCost(long[] a, long height, long limit) {
        long sum = 0L;

        for (int i = 0; i < a.length; ++i) {
            if (a[i] < height) {
                sum += height - a[i];

                if (sum > limit) {
                    return limit + 1;
                }
            }
        }

        return sum;
    }
}