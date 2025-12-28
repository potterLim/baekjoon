import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int valueA = findValue(scanner, "A");
        int valueB = findValue(scanner, "B");

        int sum = valueA + valueB;
        System.out.println("! " + sum);
    }

    private static int findValue(Scanner scanner, String variableName) {
        for (int i = 1; i <= 9; i++) {
            System.out.println("? " + variableName + " " + i);

            int response = scanner.nextInt();
            if (response == 1) {
                return i;
            }
        }

        return 0;
    }
}
