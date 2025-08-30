import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public final class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.parseInt(reader.readLine().trim());

            int[] numbers = new int[n];
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

            for (int i = 0; i < n; ++i) {
                while (!tokenizer.hasMoreTokens()) {
                    tokenizer = new StringTokenizer(reader.readLine());
                }

                numbers[i] = Integer.parseInt(tokenizer.nextToken());
            }

            Arrays.sort(numbers);

            int m = Integer.parseInt(reader.readLine().trim());
            tokenizer = new StringTokenizer(reader.readLine());

            StringBuilder outputBuilder = new StringBuilder();

            for (int i = 0; i < m; ++i) {
                while (!tokenizer.hasMoreTokens()) {
                    tokenizer = new StringTokenizer(reader.readLine());
                }

                int queryValue = Integer.parseInt(tokenizer.nextToken());
                boolean exists = isPresentInSortedArray(numbers, queryValue);

                if (exists) {
                    outputBuilder.append("1");
                } else {
                    outputBuilder.append("0");
                }

                if (i != m - 1) {
                    outputBuilder.append('\n');
                }
            }

            System.out.print(outputBuilder.toString());
        }
    }

    private static boolean isPresentInSortedArray(int[] sortedNumbers, int target) {
        int left = 0;
        int right = sortedNumbers.length - 1;

        while (left <= right) {
            int mid = left + ((right - left) >>> 1);
            int midValue = sortedNumbers[mid];

            if (midValue < target) {
                left = mid + 1;
            } else if (midValue > target) {
                right = mid - 1;
            } else {
                return true;
            }
        }

        return false;
    }
}
