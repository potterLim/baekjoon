import java.util.Scanner;

public class Main {
    private static final int MAX_LINE_LENGTH = 80;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int length = Integer.parseInt(scanner.nextLine());
        char[] s = new char[length];

        for (int i = 0; i < length; ++i) {
            s[i] = scanner.nextLine().charAt(0);
        }

        int left = 0;
        int right = length - 1;
        StringBuilder result = new StringBuilder();

        while (left <= right) {
            boolean takeLeft = false;

            for (int i = 0; left + i <= right; ++i) {
                if (s[left + i] < s[right - i]) {
                    takeLeft = true;
                    break;
                } else if (s[left + i] > s[right - i]) {
                    break;
                }
            }

            if (takeLeft) {
                result.append(s[left++]);
            } else {
                result.append(s[right--]);
            }
        }

        for (int i = 0; i < result.length(); i++) {
            System.out.print(result.charAt(i));
            if ((i + 1) % MAX_LINE_LENGTH == 0) {
                System.out.println();
            }
        }
        System.out.println();

        scanner.close();
    }
}