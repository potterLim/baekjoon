import java.util.Scanner;

public class Main {
    private static final long MOD = 1000000007L;

    private static final class FibonacciPair {
        private final long mCurrent;
        private final long mNext;

        public FibonacciPair(long current, long next) {
            mCurrent = current;
            mNext = next;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long n = scanner.nextLong();

        long targetIndex = n;

        if ((n & 1L) == 1L) {
            targetIndex = n + 1L;
        }

        FibonacciPair fibonacciPair = getFibonacciPair(targetIndex);
        System.out.println(fibonacciPair.mCurrent);
    }

    private static FibonacciPair getFibonacciPair(long index) {
        if (index == 0L) {
            return new FibonacciPair(0L, 1L);
        }

        FibonacciPair halfPair = getFibonacciPair(index / 2L);
        long a = halfPair.mCurrent;
        long b = halfPair.mNext;

        long doubledIndexValue = (2L * b) % MOD;
        doubledIndexValue = (doubledIndexValue - a + MOD) % MOD;

        long current = (a * doubledIndexValue) % MOD;
        long next = ((a * a) % MOD + (b * b) % MOD) % MOD;

        if ((index & 1L) == 0L) {
            return new FibonacciPair(current, next);
        }

        return new FibonacciPair(next, (current + next) % MOD);
    }
}