import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        int n = Integer.parseInt(keyboard.next());

        while (true) {
            for (int i = 2; i <= n; i++) {
                if (n % i == 0) {
                    n /= i;
                    System.out.println(i);
                    break;
                }
            }

            if (n == 1) {
                break;
            }
        }
    }
}