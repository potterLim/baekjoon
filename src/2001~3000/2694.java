import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader sReader = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer sTokenizer;

    public static void main(String[] args) throws Exception {
        int testCaseCount = nextInt();
        StringBuilder resultBuilder = new StringBuilder();

        for (int i = 0; i < testCaseCount; ++i) {
            int sequenceSize = nextInt();
            int[] sequence = new int[sequenceSize];

            int totalSum = 0;
            int maximumValue = 0;

            for (int j = 0; j < sequenceSize; ++j) {
                int value = nextInt();
                sequence[j] = value;
                totalSum += value;

                if (value > maximumValue) {
                    maximumValue = value;
                }
            }

            int answer = totalSum;

            for (int j = 1; (long) j * j <= totalSum; ++j) {
                if (totalSum % j != 0) {
                    continue;
                }

                int smallCandidate = j;
                int largeCandidate = totalSum / j;

                if (smallCandidate >= maximumValue && canSplit(sequence, smallCandidate)) {
                    answer = smallCandidate;
                    break;
                }

                if (largeCandidate < answer) {
                    if (largeCandidate >= maximumValue && canSplit(sequence, largeCandidate)) {
                        answer = largeCandidate;
                    }
                }
            }

            resultBuilder.append(answer);

            if (i + 1 < testCaseCount) {
                resultBuilder.append('\n');
            }
        }

        System.out.print(resultBuilder);
    }

    private static int nextInt() throws IOException {
        while (sTokenizer == null || !sTokenizer.hasMoreTokens()) {
            sTokenizer = new StringTokenizer(sReader.readLine());
        }

        return Integer.parseInt(sTokenizer.nextToken());
    }

    private static boolean canSplit(int[] sequence, int targetGroupSum) {
        int currentGroupSum = 0;

        for (int i = 0; i < sequence.length; ++i) {
            currentGroupSum += sequence[i];

            if (currentGroupSum == targetGroupSum) {
                currentGroupSum = 0;
            } else if (currentGroupSum > targetGroupSum) {
                return false;
            }
        }

        return currentGroupSum == 0;
    }
}