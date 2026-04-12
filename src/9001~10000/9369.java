import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static final int ALPHABET_COUNT = 26;
    private static final int ALL_LETTER_MASK = (1 << ALPHABET_COUNT) - 1;
    private static final String IMPOSSIBLE = "IMPOSSIBLE";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int testCaseCount = scanner.nextInt();
        StringBuilder resultBuilder = new StringBuilder();

        for (int i = 0; i < testCaseCount; ++i) {
            int encryptedMessageCount = scanner.nextInt();
            String[] encryptedMessages = new String[encryptedMessageCount];

            for (int j = 0; j < encryptedMessageCount; ++j) {
                encryptedMessages[j] = scanner.next();
            }

            String decryptedMessage = scanner.next();
            String targetMessage = scanner.next();

            int[] possiblePlainMaskByCipherLetter = new int[ALPHABET_COUNT];
            int validEncryptedMessageCount = 0;

            for (int j = 0; j < encryptedMessageCount; ++j) {
                String encryptedMessage = encryptedMessages[j];

                if (encryptedMessage.length() != decryptedMessage.length()) {
                    continue;
                }

                int[] plainLetterByCipherLetter = new int[ALPHABET_COUNT];
                int[] cipherLetterByPlainLetter = new int[ALPHABET_COUNT];
                Arrays.fill(plainLetterByCipherLetter, -1);
                Arrays.fill(cipherLetterByPlainLetter, -1);

                boolean isValidMapping = true;

                for (int k = 0; k < encryptedMessage.length(); ++k) {
                    int cipherLetterIndex = encryptedMessage.charAt(k) - 'a';
                    int plainLetterIndex = decryptedMessage.charAt(k) - 'a';

                    if (plainLetterByCipherLetter[cipherLetterIndex] != -1
                            && plainLetterByCipherLetter[cipherLetterIndex] != plainLetterIndex) {
                        isValidMapping = false;
                        break;
                    }

                    if (cipherLetterByPlainLetter[plainLetterIndex] != -1
                            && cipherLetterByPlainLetter[plainLetterIndex] != cipherLetterIndex) {
                        isValidMapping = false;
                        break;
                    }

                    plainLetterByCipherLetter[cipherLetterIndex] = plainLetterIndex;
                    cipherLetterByPlainLetter[plainLetterIndex] = cipherLetterIndex;
                }

                if (!isValidMapping) {
                    continue;
                }

                ++validEncryptedMessageCount;

                int usedPlainLetterMask = 0;

                for (int k = 0; k < ALPHABET_COUNT; ++k) {
                    if (plainLetterByCipherLetter[k] != -1) {
                        usedPlainLetterMask |= 1 << plainLetterByCipherLetter[k];
                    }
                }

                int unusedPlainLetterMask = ALL_LETTER_MASK ^ usedPlainLetterMask;

                for (int k = 0; k < ALPHABET_COUNT; ++k) {
                    if (plainLetterByCipherLetter[k] != -1) {
                        possiblePlainMaskByCipherLetter[k] |= 1 << plainLetterByCipherLetter[k];
                    } else {
                        possiblePlainMaskByCipherLetter[k] |= unusedPlainLetterMask;
                    }
                }
            }

            if (validEncryptedMessageCount == 0) {
                resultBuilder.append(IMPOSSIBLE);
            } else {
                for (int j = 0; j < targetMessage.length(); ++j) {
                    int cipherLetterIndex = targetMessage.charAt(j) - 'a';
                    int possiblePlainMask = possiblePlainMaskByCipherLetter[cipherLetterIndex];

                    if ((possiblePlainMask & (possiblePlainMask - 1)) == 0) {
                        int plainLetterIndex = Integer.numberOfTrailingZeros(possiblePlainMask);
                        resultBuilder.append((char) ('a' + plainLetterIndex));
                    } else {
                        resultBuilder.append('?');
                    }
                }
            }

            if (i + 1 < testCaseCount) {
                resultBuilder.append('\n');
            }
        }

        System.out.print(resultBuilder);
    }
}