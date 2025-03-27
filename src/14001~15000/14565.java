import java.util.Scanner;
import java.math.BigInteger;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BigInteger n = scanner.nextBigInteger();
        BigInteger a = scanner.nextBigInteger();

        BigInteger additiveInverse = n.subtract(a).mod(n);
        BigInteger multiplicativeInverse = getModularInverse(a, n);

        System.out.println(additiveInverse + " " + multiplicativeInverse);
    }

    private static BigInteger getModularInverse(BigInteger a, BigInteger n) {
        BigInteger[] result = extendedGCD(a, n);
        BigInteger gcd = result[0];
        BigInteger x = result[1];

        if (!gcd.equals(BigInteger.ONE)) {
            return BigInteger.valueOf(-1);
        }

        return x.mod(n);
    }

    private static BigInteger[] extendedGCD(BigInteger a, BigInteger b) {
        if (b.equals(BigInteger.ZERO)) {
            return new BigInteger[]{a, BigInteger.ONE, BigInteger.ZERO};
        }

        BigInteger[] values = extendedGCD(b, a.mod(b));
        BigInteger d = values[0];
        BigInteger x1 = values[2];
        BigInteger y1 = values[1].subtract(a.divide(b).multiply(values[2]));

        return new BigInteger[]{d, x1, y1};
    }
}
