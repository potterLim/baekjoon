import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder output = new StringBuilder();

        while (scanner.hasNextInt()) {
            int wordCount = scanner.nextInt();

            String[] words = new String[wordCount];
            int totalLength = 0;

            for (int i = 0; i < wordCount; ++i) {
                words[i] = scanner.next();
                totalLength += words[i].length();
            }

            int maxNodes = totalLength + 2;
            int rootIndex = 1;

            int[][] nextNode = new int[26][maxNodes];
            int[] childCount = new int[maxNodes];
            boolean[] isTerminal = new boolean[maxNodes];

            int nodeCount = rootIndex;

            for (int i = 0; i < wordCount; ++i) {
                int current = rootIndex;
                String word = words[i];

                for (int j = 0; j < word.length(); ++j) {
                    int letterIndex = word.charAt(j) - 'a';

                    if (nextNode[letterIndex][current] == 0) {
                        nextNode[letterIndex][current] = ++nodeCount;
                        ++childCount[current];
                    }

                    current = nextNode[letterIndex][current];
                }

                isTerminal[current] = true;
            }

            long totalPresses = 0L;

            for (int i = 0; i < wordCount; ++i) {
                int current = rootIndex;
                int presses = 0;
                String word = words[i];

                for (int j = 0; j < word.length(); ++j) {
                    int letterIndex = word.charAt(j) - 'a';

                    if (j == 0 || isTerminal[current] || childCount[current] > 1) {
                        ++presses;
                    }

                    current = nextNode[letterIndex][current];
                }

                totalPresses += presses;
            }

            double average = (double) totalPresses / wordCount;
            output.append(String.format(Locale.US, "%.2f%n", average));
        }

        System.out.print(output);
    }
}