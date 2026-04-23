import java.util.Scanner;

public class Main {
    private static int sRepeatCount;
    private static int sTargetLength;
    private static int sAlphabetCount;
    private static char[] sAnswer;

    private static boolean isValidPrefix(int currentLength) {
        for (int patternLength = 1; patternLength * sRepeatCount <= currentLength; ++patternLength) {
            boolean isRepeatedConsecutively = true;

            for (int repeatIndex = 1; repeatIndex < sRepeatCount && isRepeatedConsecutively; ++repeatIndex) {
                int leftStartIndex = currentLength - (repeatIndex + 1) * patternLength;
                int rightStartIndex = currentLength - repeatIndex * patternLength;

                for (int offset = 0; offset < patternLength; ++offset) {
                    if (sAnswer[leftStartIndex + offset] != sAnswer[rightStartIndex + offset]) {
                        isRepeatedConsecutively = false;
                        break;
                    }
                }
            }

            if (isRepeatedConsecutively) {
                return false;
            }
        }

        return true;
    }

    private static boolean canBuildAnswer(int currentLength) {
        if (currentLength == sTargetLength) {
            return true;
        }

        for (int alphabetIndex = 0; alphabetIndex < sAlphabetCount; ++alphabetIndex) {
            sAnswer[currentLength] = (char) ('A' + alphabetIndex);

            if (isValidPrefix(currentLength + 1) && canBuildAnswer(currentLength + 1)) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        sRepeatCount = scanner.nextInt();
        sTargetLength = scanner.nextInt();
        sAlphabetCount = scanner.nextInt();

        sAnswer = new char[sTargetLength];

        if (canBuildAnswer(0)) {
            System.out.println(new String(sAnswer));
        } else {
            System.out.println(-1);
        }

        scanner.close();
    }
}