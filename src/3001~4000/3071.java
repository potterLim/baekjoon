import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int testCases = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < testCases; i++) {
            long number = scanner.nextLong();
            System.out.println(convertToCustomBase(number));
        }

        scanner.close();
    }

    private static String convertToCustomBase(long number) {
        if (number == 0) {
            return "0";
        }

        StringBuilder result = new StringBuilder();
        while (number != 0) {
            long remainder = number % 3;
            if (remainder == 0) {
                result.append("0");
            } else if (remainder == 1 || remainder == -2) {
                result.append("1");
                number -= 1;
            } else if (remainder == 2 || remainder == -1) {
                result.append("-");
                number += 1;
            }
            number /= 3;
        }

        return result.reverse().toString();
    }
}