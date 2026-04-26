import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int testCaseCount = scanner.nextInt();
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < testCaseCount; ++i) {
            long x = scanner.nextLong();
            long y = scanner.nextLong();

            long distance = y - x;
            long n = (long) Math.sqrt(distance);

            int moveCount;

            if (distance == n * n) {
                moveCount = (int) (2 * n - 1);
            } else if (distance <= n * n + n) {
                moveCount = (int) (2 * n);
            } else {
                moveCount = (int) (2 * n + 1);
            }

            stringBuilder.append(moveCount);
            stringBuilder.append('\n');
        }

        System.out.print(stringBuilder.toString());

        scanner.close();
    }
}