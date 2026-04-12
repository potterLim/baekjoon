import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int stringLength = scanner.nextInt();
        int headacheCount = scanner.nextInt();
        String input = scanner.next();

        char[] originalCharacters = input.toCharArray();
        char[] resultCharacters = new char[stringLength];

        int index = 0;

        while (index < stringLength) {
            char currentCharacter = originalCharacters[index];

            if (currentCharacter != 'w' && currentCharacter != 'h') {
                resultCharacters[index] = currentCharacter;
                ++index;
                continue;
            }

            int segmentStartIndex = index;

            while (index < stringLength) {
                char segmentCharacter = originalCharacters[index];

                if (segmentCharacter != 'w' && segmentCharacter != 'h') {
                    break;
                }

                ++index;
            }

            int segmentEndIndex = index;
            int segmentLength = segmentEndIndex - segmentStartIndex;

            for (int segmentIndex = 0; segmentIndex < segmentLength; ++segmentIndex) {
                resultCharacters[segmentStartIndex + segmentIndex] = 'w';
            }

            int hCount = 0;

            for (int segmentIndex = 0; segmentIndex < segmentLength; ++segmentIndex) {
                if (originalCharacters[segmentStartIndex + segmentIndex] == 'h') {
                    int originalPosition = segmentIndex;
                    int finalPosition = originalPosition - headacheCount;

                    if (finalPosition < hCount) {
                        finalPosition = hCount;
                    }

                    resultCharacters[segmentStartIndex + finalPosition] = 'h';
                    ++hCount;
                }
            }
        }

        System.out.println(new String(resultCharacters));
    }
}