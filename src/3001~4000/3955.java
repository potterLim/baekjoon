import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int testCount = scanner.nextInt();
        StringBuilder outputBuilder = new StringBuilder();

        for (int caseIndex = 0; caseIndex < testCount; ++caseIndex) {
            long k = scanner.nextLong();
            long c = scanner.nextLong();

            long gcdValue = gcd(k, c);
            if (gcdValue != 1) {
                outputBuilder.append("IMPOSSIBLE\n");
                continue;
            }

            long inverse = modInverse(c, k);
            long y = inverse;

            if (y <= 0) {
                y = (y % k + k) % k;
            }
            if (y == 0) {
                y = k;
            }

            long limit = 1000000000L;

            if (c * y <= k) {
                long needed = k + 1;
                long minY = (needed + c - 1) / c;

                if (minY > limit) {
                    outputBuilder.append("IMPOSSIBLE\n");
                    continue;
                }

                long add = 0;
                if (minY > y) {
                    long diff = minY - y;
                    add = (diff + k - 1) / k;
                }

                y = y + add * k;
            }

            if (y > limit) {
                outputBuilder.append("IMPOSSIBLE\n");
                continue;
            }

            outputBuilder.append(y).append('\n');
        }

        System.out.print(outputBuilder.toString());
    }

    private static long gcd(long a, long b) {
        long x = a;
        long y = b;

        while (y != 0) {
            long temp = x % y;
            x = y;
            y = temp;
        }

        if (x < 0) {
            x = -x;
        }

        return x;
    }

    private static long modInverse(long a, long mod) {
        ExtendedGcdResult result = extendedGcd(a, mod);
        long x = result.x;

        x %= mod;
        if (x < 0) {
            x += mod;
        }

        return x;
    }

    private static ExtendedGcdResult extendedGcd(long a, long b) {
        if (b == 0) {
            return new ExtendedGcdResult(a, 1, 0);
        }

        ExtendedGcdResult next = extendedGcd(b, a % b);

        long gcdValue = next.gcd;
        long x1 = next.x;
        long y1 = next.y;

        long x = y1;
        long y = x1 - (a / b) * y1;

        return new ExtendedGcdResult(gcdValue, x, y);
    }

    private static final class ExtendedGcdResult {
        public final long gcd;
        public final long x;
        public final long y;

        public ExtendedGcdResult(long gcd, long x, long y) {
            this.gcd = gcd;
            this.x = x;
            this.y = y;
        }
    }
}