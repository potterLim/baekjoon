import java.util.Scanner;

public class Main {
    public static long modPow(long base, long exponent, long mod) {
        if (exponent == 0) {
            return 1;
        }

        if (exponent == 1) {
            return base % mod;
        }

        long half = modPow(base, exponent / 2, mod);
        long result = (half * half) % mod;

        if (exponent % 2 == 1) {
            result = (result * base) % mod;
        }

        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long A = scanner.nextLong();
        long B = scanner.nextLong();
        long C = scanner.nextLong();
        scanner.close();

        System.out.println(modPow(A, B, C));
    }
}