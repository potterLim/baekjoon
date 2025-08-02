import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int testCount = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < testCount; i++) {
            String number = scanner.nextLine();
            String next = getNextPermutation(number);
            System.out.println(next);
        }

        scanner.close();
    }

    private static String getNextPermutation(String number) {
        char[] digits = number.toCharArray();
        int i = digits.length - 2;

        while (i >= 0 && digits[i] >= digits[i + 1]) {
            i--;
        }

        if (i < 0) {
            return "BIGGEST";
        }

        int j = digits.length - 1;
        while (digits[j] <= digits[i]) {
            j--;
        }

        swap(digits, i, j);

        reverse(digits, i + 1, digits.length - 1);

        return new String(digits);
    }

    private static void swap(char[] array, int i, int j) {
        char temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private static void reverse(char[] array, int start, int end) {
        while (start < end) {
            swap(array, start, end);
            start++;
            end--;
        }
    }
}
