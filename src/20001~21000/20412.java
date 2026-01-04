import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        long mod = scanner.nextLong();
        long seed = scanner.nextLong();
        long x1 = scanner.nextLong();
        long x2 = scanner.nextLong();

        long a;
        long c;

        long diff = normalize(x1 - seed, mod);

        if (diff == 0) {
            a = 0;
            c = x1;
        } else {
            long numerator = normalize(x2 - x1, mod);
            long inv = modInverse(diff, mod);

            a = multiplyMod(numerator, inv, mod);
            c = normalize(x1 - multiplyMod(a, seed, mod), mod);
        }

        System.out.print(a + " " + c);
    }

    private static long normalize(long value, long mod) {
        long result = value % mod;
        if (result < 0) {
            result += mod;
        }
        return result;
    }

    private static long multiplyMod(long a, long b, long mod) {
        return (a * b) % mod;
    }

    private static long modInverse(long a, long mod) {
        long exponent = mod - 2;
        return modPow(a, exponent, mod);
    }

    private static long modPow(long base, long exponent, long mod) {
        long result = 1;
        long current = normalize(base, mod);
        long power = exponent;

        while (power > 0) {
            if ((power & 1L) == 1L) {
                result = multiplyMod(result, current, mod);
            }
            current = multiplyMod(current, current, mod);
            power >>= 1;
        }

        return result;
    }
}