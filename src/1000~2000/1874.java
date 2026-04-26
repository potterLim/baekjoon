import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();

        int[] stack = new int[n];
        int topIndex = -1;
        int nextNumber = 1;

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < n; ++i) {
            int targetNumber = scanner.nextInt();

            while (nextNumber <= targetNumber) {
                ++topIndex;
                stack[topIndex] = nextNumber;
                ++nextNumber;

                result.append("+\n");
            }

            if (topIndex >= 0 && stack[topIndex] == targetNumber) {
                --topIndex;
                result.append("-\n");
            } else {
                System.out.println("NO");
                scanner.close();
                return;
            }
        }

        System.out.print(result);

        scanner.close();
    }
}