import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int recordLength = Integer.parseInt(scanner.nextLine());
        String[] record = new String[recordLength];
        int unknownIndex = -1;

        Set<String> usedWords = new HashSet<>();

        for (int i = 0; i < recordLength; i++) {
            record[i] = scanner.nextLine();
            if (record[i].equals("?")) {
                unknownIndex = i;
            } else {
                usedWords.add(record[i]);
            }
        }

        int candidateCount = Integer.parseInt(scanner.nextLine());
        List<String> candidates = new ArrayList<>();

        for (int i = 0; i < candidateCount; i++) {
            candidates.add(scanner.nextLine());
        }

        for (String candidate : candidates) {
            if (usedWords.contains(candidate)) {
                continue;
            }

            boolean isValid = true;

            if (unknownIndex > 0) {
                String prevWord = record[unknownIndex - 1];
                if (prevWord.charAt(prevWord.length() - 1) != candidate.charAt(0)) {
                    isValid = false;
                }
            }

            if (unknownIndex < recordLength - 1) {
                String nextWord = record[unknownIndex + 1];
                if (!nextWord.equals("?") && candidate.charAt(candidate.length() - 1) != nextWord.charAt(0)) {
                    isValid = false;
                }
            }

            if (isValid) {
                System.out.println(candidate);
                return;
            }
        }
    }
}
