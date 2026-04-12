import java.util.Scanner;

public class Main {
    private static final long OPERATIONS_PER_SECOND = 100000000L;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int testCaseCount = scanner.nextInt();
        StringBuilder resultBuilder = new StringBuilder();

        for (int i = 0; i < testCaseCount; ++i) {
            String complexity = scanner.next();
            long n = scanner.nextLong();
            long testCount = scanner.nextLong();
            long timeLimit = scanner.nextLong();

            long limit = OPERATIONS_PER_SECOND * timeLimit;
            boolean isTimeLimitExceeded = false;

            if (complexity.equals("O(N)")) {
                isTimeLimitExceeded = isExceededByLinear(n, testCount, limit);
            } else if (complexity.equals("O(N^2)")) {
                isTimeLimitExceeded = isExceededByPolynomial(n, 2, testCount, limit);
            } else if (complexity.equals("O(N^3)")) {
                isTimeLimitExceeded = isExceededByPolynomial(n, 3, testCount, limit);
            } else if (complexity.equals("O(2^N)")) {
                isTimeLimitExceeded = isExceededByExponential(n, testCount, limit);
            } else if (complexity.equals("O(N!)")) {
                isTimeLimitExceeded = isExceededByFactorial(n, testCount, limit);
            }

            if (isTimeLimitExceeded) {
                resultBuilder.append("TLE!");
            } else {
                resultBuilder.append("May Pass.");
            }

            if (i + 1 < testCaseCount) {
                resultBuilder.append('\n');
            }
        }

        System.out.print(resultBuilder);
    }

    private static boolean isExceededByLinear(long n, long testCount, long limit) {
        if (n > limit) {
            return true;
        }

        return testCount > limit / n;
    }

    private static boolean isExceededByPolynomial(long n, int exponent, long testCount, long limit) {
        long value = 1L;

        for (int i = 0; i < exponent; ++i) {
            if (value > limit / n) {
                return true;
            }

            value *= n;
        }

        if (value > limit) {
            return true;
        }

        return testCount > limit / value;
    }

    private static boolean isExceededByExponential(long n, long testCount, long limit) {
        long value = 1L;

        for (long i = 0; i < n; ++i) {
            if (value > limit / 2L) {
                return true;
            }

            value *= 2L;
        }

        if (value > limit) {
            return true;
        }

        return testCount > limit / value;
    }

    private static boolean isExceededByFactorial(long n, long testCount, long limit) {
        long value = 1L;

        for (long i = 2; i <= n; ++i) {
            if (value > limit / i) {
                return true;
            }

            value *= i;
        }

        if (value > limit) {
            return true;
        }

        return testCount > limit / value;
    }
}