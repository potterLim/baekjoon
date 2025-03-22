import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        for (int i = 0; i < t; ++i) {
            int n = scanner.nextInt();
            for (int j = 2; j * j <= n; ++j) {
                int cnt = 0;
                while (n % j == 0) {
                    cnt++;
                    n /= j;
                }
                if (cnt > 0) {
                    System.out.println(j + " " + cnt);
                }
            }
            if (n > 1) {
                System.out.println(n + " " + 1);
            }
        }
        scanner.close();
    }
}