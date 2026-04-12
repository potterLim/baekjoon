import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static final int NOT_FOUND = -1;
    private static final int MAX_REPORTED_POSITION_COUNT = 3;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int testCaseCount = Integer.parseInt(scanner.nextLine());
        StringBuilder resultBuilder = new StringBuilder();

        for (int i = 0; i < testCaseCount; ++i) {
            String word = scanner.nextLine().toLowerCase();
            String text = scanner.nextLine().toLowerCase();

            int wordLength = word.length();
            int[] wordIndexByCharacter = new int[128];
            Arrays.fill(wordIndexByCharacter, NOT_FOUND);

            for (int j = 0; j < wordLength; ++j) {
                wordIndexByCharacter[word.charAt(j)] = j;
            }

            int[] partialMatchCounts = new int[wordLength];
            int occurrenceCount = 0;
            int[] reportedPositions = new int[MAX_REPORTED_POSITION_COUNT];

            for (int j = 0; j < text.length(); ++j) {
                char currentCharacter = text.charAt(j);

                if (currentCharacter >= 128) {
                    continue;
                }

                int wordIndex = wordIndexByCharacter[currentCharacter];

                if (wordIndex == NOT_FOUND) {
                    continue;
                }

                if (wordIndex == 0) {
                    if (wordLength == 1) {
                        if (occurrenceCount < MAX_REPORTED_POSITION_COUNT) {
                            reportedPositions[occurrenceCount] = j + 1;
                        }

                        ++occurrenceCount;
                    } else {
                        ++partialMatchCounts[0];
                    }
                } else if (partialMatchCounts[wordIndex - 1] > 0) {
                    --partialMatchCounts[wordIndex - 1];

                    if (wordIndex == wordLength - 1) {
                        if (occurrenceCount < MAX_REPORTED_POSITION_COUNT) {
                            reportedPositions[occurrenceCount] = j + 1;
                        }

                        ++occurrenceCount;
                    } else {
                        ++partialMatchCounts[wordIndex];
                    }
                }
            }

            resultBuilder.append(occurrenceCount);

            int reportedCount = occurrenceCount;
            if (reportedCount > MAX_REPORTED_POSITION_COUNT) {
                reportedCount = MAX_REPORTED_POSITION_COUNT;
            }

            for (int j = 0; j < reportedCount; ++j) {
                resultBuilder.append(' ').append(reportedPositions[j]);
            }

            if (i + 1 < testCaseCount) {
                resultBuilder.append('\n');
            }
        }

        System.out.print(resultBuilder);
    }
}