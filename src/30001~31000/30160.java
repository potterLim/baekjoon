import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();

        long prefixA = 0L;
        long prefixIA = 0L;
        long prefixI2A = 0L;

        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= n; ++i) {
            long a = scanner.nextInt();

            prefixA += a;
            prefixIA += (long) i * a;
            prefixI2A += (long) i * i * a;

            long kPlusOne = i + 1L;
            long result = kPlusOne * kPlusOne * prefixA - 2L * kPlusOne * prefixIA + prefixI2A;

            sb.append(result);

            if (i != n) {
                sb.append(' ');
            }
        }

        System.out.print(sb.toString());
        scanner.close();
    }
}