import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        String[] parts = input.split("What");

        for (int i = 1; i < parts.length; i++) {
            String part = parts[i];
            int questionMarkIndex = part.indexOf('?');

            if (questionMarkIndex != -1) {
                String content = part.substring(0, questionMarkIndex);
                System.out.println("Forty-two" + content + ".");
            }
        }
    }
}
