import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        long x = scanner.nextLong();
        long y = scanner.nextLong();

        long answer;

        if (x == 0L && y == 0L) {
            answer = 1L;
        } else {
            long n = x + y;

            if (y == 0L) {
                answer = 1L + 3L * n * (n + 1L);
            } else {
                answer = 3L * n * (n - 1L) + 2L + (n - 1L - x);
            }
        }

        System.out.println(answer);
        scanner.close();
    }
}