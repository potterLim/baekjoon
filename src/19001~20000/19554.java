import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int left = 1;
        int right = scanner.nextInt();

        while (true) {
            int mid = (left + right) / 2;

            System.out.println("? " + mid);
            System.out.flush();

            int response = scanner.nextInt();

            if (response == 0) {
                System.out.println("= " + mid);
                System.out.flush();
                return;
            }

            if (response < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
    }
}
