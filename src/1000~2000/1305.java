import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int boardLength = scanner.nextInt();
        String visibleText = scanner.next();

        int[] prefix = new int[boardLength];

        int j = 0;
        for (int i = 1; i < boardLength; ++i) {
            while (j > 0 && visibleText.charAt(i) != visibleText.charAt(j)) {
                j = prefix[j - 1];
            }

            if (visibleText.charAt(i) == visibleText.charAt(j)) {
                prefix[i] = ++j;
            }
        }

        int shortestAdLength = boardLength - prefix[boardLength - 1];
        System.out.print(shortestAdLength);
    }
}