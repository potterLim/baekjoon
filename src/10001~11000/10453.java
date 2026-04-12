import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int testCaseCount = scanner.nextInt();
        StringBuilder resultBuilder = new StringBuilder();

        for (int i = 0; i < testCaseCount; ++i) {
            String source = scanner.next();
            String target = scanner.next();

            if (source.length() != target.length()) {
                resultBuilder.append(-1);

                if (i + 1 < testCaseCount) {
                    resultBuilder.append('\n');
                }

                continue;
            }

            long minimumSwapCount = 0L;
            int sourcePrefixBalance = 0;
            int targetPrefixBalance = 0;

            for (int j = 0; j < source.length(); ++j) {
                if (source.charAt(j) == 'a') {
                    ++sourcePrefixBalance;
                } else {
                    --sourcePrefixBalance;
                }

                if (target.charAt(j) == 'a') {
                    ++targetPrefixBalance;
                } else {
                    --targetPrefixBalance;
                }

                minimumSwapCount += Math.abs(sourcePrefixBalance - targetPrefixBalance);
            }

            minimumSwapCount /= 2L;
            resultBuilder.append(minimumSwapCount);

            if (i + 1 < testCaseCount) {
                resultBuilder.append('\n');
            }
        }

        System.out.print(resultBuilder);
    }
}