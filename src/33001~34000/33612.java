import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final int START_YEAR = 2024;
        final int START_MONTH = 8;
        final int INTERVAL_MONTHS = 7;

        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        int totalMonths = (n - 1) * INTERVAL_MONTHS;
        int year = START_YEAR + (START_MONTH - 1 + totalMonths) / 12;
        int month = (START_MONTH - 1 + totalMonths) % 12 + 1;

        System.out.printf("%d %d\n", year, month);
    }
}