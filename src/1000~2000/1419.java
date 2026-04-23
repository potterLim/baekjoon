import java.util.Scanner;

public class Main {
    private static long countMultiples(long left, long right, long divisor) {
        if (left > right) {
            return 0L;
        }

        return right / divisor - (left - 1L) / divisor;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        long l = scanner.nextLong();
        long r = scanner.nextLong();
        int k = scanner.nextInt();

        long answer = 0L;

        if (k == 2) {
            long left = Math.max(l, 3L);

            if (left <= r) {
                answer = r - left + 1L;
            }
        } else if (k == 3) {
            long left = Math.max(l, 6L);
            answer = countMultiples(left, r, 3L);
        } else if (k == 4) {
            long left = Math.max(l, 10L);
            answer = countMultiples(left, r, 2L);

            if (left <= 12L && 12L <= r) {
                --answer;
            }
        } else {
            long left = Math.max(l, 15L);
            answer = countMultiples(left, r, 5L);
        }

        System.out.println(answer);
        scanner.close();
    }
}