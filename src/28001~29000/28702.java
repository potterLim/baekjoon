import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String line0 = scanner.nextLine();
        String line1 = scanner.nextLine();
        String line2 = scanner.nextLine();

        long nextNumber;

        if (isNumber(line2)) {
            nextNumber = Long.parseLong(line2) + 1L;
        } else if (isNumber(line1)) {
            nextNumber = Long.parseLong(line1) + 2L;
        } else {
            nextNumber = Long.parseLong(line0) + 3L;
        }

        System.out.print(toFizzBuzz(nextNumber));
    }

    private static boolean isNumber(String text) {
        if (text == null || text.isEmpty()) {
            return false;
        }

        for (int i = 0; i < text.length(); ++i) {
            char c = text.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }

        return true;
    }

    private static String toFizzBuzz(long number) {
        if (number % 15 == 0) {
            return "FizzBuzz";
        }

        if (number % 3 == 0) {
            return "Fizz";
        }

        if (number % 5 == 0) {
            return "Buzz";
        }

        return Long.toString(number);
    }
}
