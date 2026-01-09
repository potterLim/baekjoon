import java.util.Scanner;

public class Main {
    private static final long MOD = 1_000_000_007L;

    private static long[][] MultiplyMatrix(long[][] left, long[][] right) {
        long[][] result = new long[2][2];

        result[0][0] = (left[0][0] * right[0][0] + left[0][1] * right[1][0]) % MOD;
        result[0][1] = (left[0][0] * right[0][1] + left[0][1] * right[1][1]) % MOD;
        result[1][0] = (left[1][0] * right[0][0] + left[1][1] * right[1][0]) % MOD;
        result[1][1] = (left[1][0] * right[0][1] + left[1][1] * right[1][1]) % MOD;

        return result;
    }

    private static long[][] PowerMatrix(long[][] baseMatrix, long exponent) {
        long[][] resultMatrix = new long[][]{ { 1, 0 }, { 0, 1 } };

        while (exponent > 0) {
            if ((exponent & 1L) == 1L) {
                resultMatrix = MultiplyMatrix(resultMatrix, baseMatrix);
            }

            baseMatrix = MultiplyMatrix(baseMatrix, baseMatrix);
            exponent >>= 1;
        }

        return resultMatrix;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long width = scanner.nextLong();
        scanner.close();

        if ((width & 1L) == 1L) {
            System.out.println(0);
            return;
        }

        long halfWidth = width / 2;

        if (halfWidth == 0) {
            System.out.println(1);
            return;
        }

        if (halfWidth == 1) {
            System.out.println(3);
            return;
        }

        long[][] transitionMatrix = new long[][]{
                { 4, MOD - 1 },
                { 1, 0 }
        };

        long[][] poweredMatrix = PowerMatrix(transitionMatrix, halfWidth - 1);

        long valueAt1 = 3;
        long valueAt0 = 1;

        long answer = (poweredMatrix[0][0] * valueAt1 + poweredMatrix[0][1] * valueAt0) % MOD;
        System.out.println(answer);
    }
}