import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private static final Map<String, Integer> dictionaryMap = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int dictionarySize = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < dictionarySize; ++i) {
            String word = scanner.nextLine();
            String normalizedWord = normalize(word);
            dictionaryMap.put(normalizedWord, dictionaryMap.getOrDefault(normalizedWord, 0) + 1);
        }

        int sentenceCount = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < sentenceCount; ++i) {
            String sentence = scanner.nextLine();
            long interpretationCount = calculateInterpretations(sentence);
            System.out.println(interpretationCount);
        }
    }

    private static long calculateInterpretations(String sentence) {
        String[] words = sentence.split(" ");
        long result = 1;

        for (String word : words) {
            String normalizedWord = normalize(word);
            int count = dictionaryMap.getOrDefault(normalizedWord, 0);

            result *= count;

            if (result == 0) {
                return 0;
            }
        }

        return result;
    }

    private static String normalize(String word) {
        assert (word != null);

        if (word.length() <= 2) {
            return word;
        }

        char[] middleCharacters = word.substring(1, word.length() - 1).toCharArray();
        Arrays.sort(middleCharacters);

        return word.charAt(0) + new String(middleCharacters) + word.charAt(word.length() - 1);
    }
}
