import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        scanner.close();

        int result = getLongestNonPalindromeLength(input);
        System.out.println(result);
    }

    private static int getLongestNonPalindromeLength(String text) {
        if (!isPalindrome(text)) {
            return text.length();
        }

        if (allCharactersSame(text)) {
            return -1;
        }

        return text.length() - 1;
    }

    private static boolean isPalindrome(String text) {
        int left = 0;
        int right = text.length() - 1;

        while (left < right) {
            if (text.charAt(left) != text.charAt(right)) {
                return false;
            }

            ++left;
            --right;
        }

        return true;
    }

    private static boolean allCharactersSame(String text) {
        char firstChar = text.charAt(0);

        for (int i = 1; i < text.length(); ++i) {
            if (text.charAt(i) != firstChar) {
                return false;
            }
        }

        return true;
    }
}