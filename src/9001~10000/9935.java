import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String text = scanner.nextLine();
        String bomb = scanner.nextLine();
        scanner.close();

        String result = explodeString(text, bomb);
        System.out.println(result.isEmpty() ? "FRULA" : result);
    }

    private static String explodeString(String text, String bomb) {
        StringBuilder result = new StringBuilder();
        int bombLength = bomb.length();

        for (int i = 0; i < text.length(); ++i) {
            result.append(text.charAt(i));

            if (result.length() >= bombLength) {
                boolean isBomb = true;

                for (int j = 0; j < bombLength; ++j) {
                    if (result.charAt(result.length() - bombLength + j) != bomb.charAt(j)) {
                        isBomb = false;
                        break;
                    }
                }

                if (isBomb) {
                    result.setLength(result.length() - bombLength);
                }
            }
        }

        return result.toString();
    }
}