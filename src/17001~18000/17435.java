import java.util.Scanner;

public class Main {
    private static final int MAX_POWER = 19;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int m = scanner.nextInt();

        int[][] jump = new int[MAX_POWER][m + 1];

        int i = 1;
        while (i <= m) {
            jump[0][i] = scanner.nextInt();
            ++i;
        }

        int p = 1;
        while (p < MAX_POWER) {
            int x = 1;
            while (x <= m) {
                int mid = jump[p - 1][x];
                jump[p][x] = jump[p - 1][mid];
                ++x;
            }
            ++p;
        }

        int queryCount = scanner.nextInt();
        StringBuilder output = new StringBuilder();

        int q = 0;
        while (q < queryCount) {
            int n = scanner.nextInt();
            int x = scanner.nextInt();

            int result = apply(jump, n, x);

            output.append(result).append('\n');

            ++q;
        }

        System.out.print(output.toString());
    }

    private static int apply(int[][] jump, int n, int x) {
        int current = x;
        int bitIndex = 0;
        int steps = n;

        while (steps != 0) {
            if ((steps & 1) != 0) {
                current = jump[bitIndex][current];
            }

            steps >>= 1;
            ++bitIndex;
        }

        return current;
    }
}