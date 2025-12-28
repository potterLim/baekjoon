import java.util.Random;
import java.util.Scanner;

public class Main {
    private static final int MIN_VALUE = 1;
    private static final int MAX_VALUE = 10_000;
    private static final int MAX_QUESTIONS = 19_997;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random(System.nanoTime());

        int[] shuffledCandidates = buildShuffledCandidates(random);

        int questionsUsed = 0;

        int valueA = findHiddenValue(scanner, "A", shuffledCandidates, MAX_QUESTIONS, questionsUsed);
        questionsUsed += valueA >> 16;
        valueA = (short) valueA;

        int valueB = findHiddenValue(scanner, "B", shuffledCandidates, MAX_QUESTIONS, questionsUsed);
        valueB = (short) valueB;

        System.out.println("! " + (valueA + valueB));
    }

    private static int[] buildShuffledCandidates(Random random) {
        int[] candidates = new int[MAX_VALUE - MIN_VALUE + 1];

        for (int i = 0; i < candidates.length; i++) {
            candidates[i] = MIN_VALUE + i;
        }

        for (int i = candidates.length - 1; i >= 1; i--) {
            int swapIndex = random.nextInt(i + 1);

            int temp = candidates[i];
            candidates[i] = candidates[swapIndex];
            candidates[swapIndex] = temp;
        }

        return candidates;
    }

    private static int findHiddenValue(Scanner scanner, String variableName, int[] candidates, int questionLimit, int questionsUsedSoFar) {
        int questionsUsed = 0;

        for (int i = 0; i < candidates.length; i++) {
            if (questionsUsedSoFar + questionsUsed >= questionLimit) {
                break;
            }

            int guess = candidates[i];

            System.out.println("? " + variableName + " " + guess);

            int response = scanner.nextInt();
            questionsUsed++;

            if (response == 1) {
                return (questionsUsed << 16) | (guess & 0xFFFF);
            }
        }

        return (questionsUsed << 16);
    }
}
