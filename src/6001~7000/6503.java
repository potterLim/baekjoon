import java.util.Scanner;

public class Main {
    private static final int ASCII_SIZE = 128;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            int maxDistinct = scanner.nextInt();
            scanner.nextLine();

            if (maxDistinct == 0) {
                break;
            }

            String text = scanner.nextLine();
            int maxLength = getMaxSubstringLength(text, maxDistinct);
            System.out.println(maxLength);
        }
    }

    private static int getMaxSubstringLength(String text, int maxDistinct) {
        int[] count = new int[ASCII_SIZE];
        int distinct = 0;
        int maxLength = 0;
        int left = 0;

        for (int right = 0; right < text.length(); ++right) {
            char currentChar = text.charAt(right);
            int asciiCode = currentChar;

            if (count[asciiCode] == 0) {
                ++distinct;
            }
            ++count[asciiCode];

            while (distinct > maxDistinct) {
                char leftChar = text.charAt(left);
                int leftAscii = leftChar;

                --count[leftAscii];
                if (count[leftAscii] == 0) {
                    --distinct;
                }
                ++left;
            }

            int currentLength = right - left + 1;
            if (currentLength > maxLength) {
                maxLength = currentLength;
            }
        }

        return maxLength;
    }
}
