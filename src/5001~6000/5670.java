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
                String word = scanner.next();

                words[i] = word;
                totalLength += word.length();
            }

            int maxNodes = totalLength + 2;
            int rootIndex = rootIndex = 1;


            int[][] nextNode = new int[26][maxNodes];
            int[] childCount = new int[maxNodes];
            boolean[] isTerminal = new boolean[maxNodes];


            int nodeCount = rootIndex;


            for (int i = 0; i < wordCount; ++i) {
                String word = words[i];
                int current = rootIndex;

                for (int j = 0; j < word.length(); ++j) {
                    int letterIndex = word.charAt(j) - 'a';
                    int child = nextNode[letterIndex][current];


                    if (child == 0) {
                        nodeCount++;
                        child = nodeCount;

                        nextNode[letterIndex][current] = child;
                        ++childCount[current];
                    }

                    current = child;
                }

                isTerminal[current] = true;
            }

            long totalPresses = 0L;


            for (int i = 0; i < wordCount; ++i) {
                String word = words[i];
                int presses = 0;
                int current = rootIndex;

                for (int j = 0; j < word.length(); ++j) {
                    int letterIndex = word.charAt(j) - 'a';
                    int child = nextNode[letterIndex][current];

                    if (j == 0) {
                        ++presses;
                    } else {
                        if (isTerminal[current] || childCount[current] > 1) {
                            ++presses;
                        }
                    }

                    current = child;
                }

                totalPresses += presses;
            }

            double average;

            average = (double) totalPresses / (double) wordCount;
            output.append(String.format(Locale.US, "%.2f%n", average));
        }

        System.out.print(output.toString());
    }
}