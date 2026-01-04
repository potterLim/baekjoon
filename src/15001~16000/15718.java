import java.util.Scanner;

public class Main {
    private static final int MOD = 100007;
    private static final int MOD1 = 97;
    private static final int MOD2 = 1031;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int testCount = scanner.nextInt();

        StringBuilder output = new StringBuilder();

        for (int i = 0; i < testCount; ++i) {
            long n = scanner.nextLong();
            long m = scanner.nextLong();

            int answer = solve(n, m);
            output.append(answer).append('\n');
        }

        System.out.print(output.toString());
    }

    private static int solve(long n, long m) {
        if (m == 1L) {
            if (n == 0L) {
                return 1;
            } else {
                return 0;
            }
        }

        long top = n - 1L;
        long bottom = m - 2L;

        if (bottom < 0L) {
            return 0;
        }

        if (top < bottom) {
            return 0;
        }

        int r1 = lucas(top, bottom, MOD1);
        int r2 = lucas(top, bottom, MOD2);

        long combined = crt(r1, MOD1, r2, MOD2);
        return (int) (combined % MOD);
    }

    private static int lucas(long n, long k, int modPrime) {
        if (k == 0L) {
            return 1;
        }

        long nHigh = n / modPrime;
        long kHigh = k / modPrime;

        int highPart = lucas(nHigh, kHigh, modPrime);

        int nLow = (int) (n % modPrime);
        int kLow = (int) (k % modPrime);

        int lowPart = combSmall(nLow, kLow, modPrime);

        return (int) ((long) highPart * (long) lowPart % (long) modPrime);
    }

    private static int combSmall(int n, int k, int modPrime) {
        if (k < 0 || k > n) {
            return 0;
        }

        int result = 1;

        for (int i = 1; i <= k; ++i) {
            int numerator = (n - k + i) % modPrime;
            result = (int) ((long) result * (long) numerator % (long) modPrime);

            int inv = modPow(i, modPrime - 2, modPrime);
            result = (int) ((long) result * (long) inv % (long) modPrime);
        }

        return result;
    }

    private static int modPow(long base, int exp, int mod) {
        long value = base % mod;
        long result = 1L;
        int e = exp;

        while (e > 0) {
            if ((e & 1) == 1) {
                result = (result * value) % mod;
            }

            value = (value * value) % mod;
            e >>= 1;
        }

        return (int) result;
    }

    private static long crt(int a1, int m1, int a2, int m2) {
        int inv = modPow(m1, m2 - 2, m2);

        int diff = a2 - a1;
        int diffMod = diff % m2;
        if (diffMod < 0) {
            diffMod += m2;
        }

        long t = (long) diffMod * (long) inv % (long) m2;
        return (long) a1 + (long) m1 * t;
    }
}