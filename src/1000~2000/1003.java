import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int testCaseCount = scanner.nextInt();

        int[] zeroCounts = new int[41];
        int[] oneCounts = new int[41];

        zeroCounts[0] = 1;
        oneCounts[0] = 0;

        zeroCounts[1] = 0;
        oneCounts[1] = 1;

        for (int i = 2; i <= 40; ++i) {
            zeroCounts[i] = zeroCounts[i - 1] + zeroCounts[i - 2];
            oneCounts[i] = oneCounts[i - 1] + oneCounts[i - 2];
        }

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < testCaseCount; ++i) {
            int n = scanner.nextInt();

            stringBuilder.append(zeroCounts[n]);
            stringBuilder.append(' ');
            stringBuilder.append(oneCounts[n]);
            stringBuilder.append('\n');
        }

        System.out.print(stringBuilder.toString());

        scanner.close();
    }
}