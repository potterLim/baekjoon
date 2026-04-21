import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.close();

        long countA = getCountA(n);
        long countB = getCountB(n);
        long countC = getCountC(n);

        StringBuilder builder = new StringBuilder();
        builder.append(countA).append('\n');
        builder.append(countB).append('\n');
        builder.append(countC);

        System.out.print(builder.toString());
    }

    private static long getCountA(int n) {
        long m = (n - 1L) / 2L;
        return m * (n - m - 1L);
    }

    private static long getCountB(int n) {
        boolean[] isDivisor = new boolean[n + 1];
        int[] divisors = new int[2048];
        int divisorCount = 0;

        for (int i = 1; (long) i * i <= n; ++i) {
            if (n % i != 0) {
                continue;
            }

            divisors[divisorCount] = i;
            ++divisorCount;
            isDivisor[i] = true;

            int other = n / i;
            if (other != i) {
                divisors[divisorCount] = other;
                ++divisorCount;
                isDivisor[other] = true;
            }
        }

        Arrays.sort(divisors, 0, divisorCount);

        long count = 0L;

        for (int i = 0; i < divisorCount; ++i) {
            int x = divisors[i];

            for (int j = i; j < divisorCount; ++j) {
                int y = divisors[j];
                int z = x + y;

                if (z > n) {
                    break;
                }

                if (isDivisor[z]) {
                    ++count;
                }
            }
        }

        return count;
    }

    private static long getCountC(int n) {
        if (n < 5) {
            return 0L;
        }

        boolean[] isPrime = new boolean[n + 1];
        Arrays.fill(isPrime, true);

        isPrime[0] = false;
        isPrime[1] = false;

        for (int i = 2; (long) i * i <= n; ++i) {
            if (!isPrime[i]) {
                continue;
            }

            for (int j = i * i; j <= n; j += i) {
                isPrime[j] = false;
            }
        }

        long count = 0L;

        for (int i = 2; i + 2 <= n; ++i) {
            if (isPrime[i] && isPrime[i + 2]) {
                ++count;
            }
        }

        return count;
    }
}