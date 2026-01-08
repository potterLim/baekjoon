import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String numberText = scanner.nextLine().trim();

        int[] digitCounts = new int[10];
        int digitSum = 0;
        boolean hasZero = false;

        for (int i = 0; i < numberText.length(); ++i) {
            int digit = numberText.charAt(i) - '0';
            digitCounts[digit]++;
            digitSum += digit;

            if (digit == 0) {
                hasZero = true;
            }
        }

        if (!hasZero || digitSum % 3 != 0) {
            System.out.println("-1");
            return;
        }

        StringBuilder result = new StringBuilder(numberText.length());
        for (int i = 9; i >= 0; --i) {
            for (int j = 0; j < digitCounts[i]; ++j) {
                result.append((char) ('0' + i));
            }
        }

        System.out.println(result.toString());
    }
}