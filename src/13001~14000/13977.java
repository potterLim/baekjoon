import java.util.Scanner;

public class Main {
    private static final int MOD = 1000000007;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int queryCount = scanner.nextInt();

        int[] nValues = new int[queryCount];
        int[] kValues = new int[queryCount];

        int maxN = 0;

        for (int i = 0; i < queryCount; ++i) {
            int n = scanner.nextInt();
            int k = scanner.nextInt();

            nValues[i] = n;
            kValues[i] = k;

            if (n > maxN) {
                maxN = n;
            }
        }

        long[] factorial = new long[maxN + 1];
        long[] inverseFactorial = new long[maxN + 1];

        factorial[0] = 1;
        for (int i = 1; i <= maxN; ++i) {
            factorial[i] = factorial[i - 1] * i % MOD;
        }

        inverseFactorial[maxN] = modPow(factorial[maxN], MOD - 2);

        for (int i = maxN - 1; i >= 0; --i) {
            inverseFactorial[i] = inverseFactorial[i + 1] * (i + 1) % MOD;
        }

        StringBuilder output = new StringBuilder();

        for (int i = 0; i < queryCount; ++i) {
            int n = nValues[i];
            int k = kValues[i];

            long result = factorial[n] * inverseFactorial[k] % MOD * inverseFactorial[n - k] % MOD;

            output.append(result).append('\n');
        }

        System.out.print(output.toString());
    }

    private static long modPow(long base, long exponent) {
        long result = 1;
        long value = base;

        while (exponent > 0) {
            if ((exponent & 1) == 1) {
                result = result * value % MOD;
            }
            value = value * value % MOD;
            exponent >>= 1;
        }

        return result;
    }
}