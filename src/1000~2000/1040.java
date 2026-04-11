import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static int sTargetDistinctCount;
    private static String sLowerBound;
    private static int sLength;
    private static int[][][] sMemo;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String number = scanner.next();
        int targetDistinctCount = scanner.nextInt();

        sTargetDistinctCount = targetDistinctCount;

        String answer = buildSmallestNumber(number.length(), number);

        if (answer == null) {
            int minimumLength;

            if (targetDistinctCount == 1) {
                minimumLength = 1;
            } else {
                minimumLength = targetDistinctCount;
            }

            int nextLength = number.length() + 1;

            if (nextLength < minimumLength) {
                nextLength = minimumLength;
            }

            answer = buildSmallestNumber(nextLength, null);
        }

        System.out.println(answer);
    }

    private static String buildSmallestNumber(int length, String lowerBoundOrNull) {
        sLength = length;
        sLowerBound = lowerBoundOrNull;
        sMemo = new int[length + 1][1 << 10][2];

        for (int position = 0; position <= length; ++position) {
            for (int digitMask = 0; digitMask < (1 << 10); ++digitMask) {
                Arrays.fill(sMemo[position][digitMask], -1);
            }
        }

        int isTight = 0;

        if (lowerBoundOrNull != null) {
            isTight = 1;
        }

        boolean canBuild = canBuildNumber(0, 0, isTight);

        if (!canBuild) {
            return null;
        }

        StringBuilder builder = new StringBuilder();

        int digitMask = 0;

        for (int position = 0; position < length; ++position) {
            int startDigit = 0;

            if (position == 0) {
                startDigit = 1;
            }

            int lowerDigit = 0;

            if (isTight == 1) {
                lowerDigit = sLowerBound.charAt(position) - '0';
            }

            for (int digit = startDigit; digit <= 9; ++digit) {
                if (isTight == 1 && digit < lowerDigit) {
                    continue;
                }

                int nextDigitMask = digitMask | (1 << digit);
                int nextIsTight = 0;

                if (isTight == 1 && digit == lowerDigit) {
                    nextIsTight = 1;
                }

                if (canBuildNumber(position + 1, nextDigitMask, nextIsTight)) {
                    builder.append(digit);
                    digitMask = nextDigitMask;
                    isTight = nextIsTight;
                    break;
                }
            }
        }

        return builder.toString();
    }

    private static boolean canBuildNumber(int position, int digitMask, int isTight) {
        int usedDigitCount = Integer.bitCount(digitMask);
        int remainingCount = sLength - position;

        if (usedDigitCount > sTargetDistinctCount) {
            return false;
        }

        if (usedDigitCount + remainingCount < sTargetDistinctCount) {
            return false;
        }

        if (position == sLength) {
            return usedDigitCount == sTargetDistinctCount;
        }

        if (sMemo[position][digitMask][isTight] != -1) {
            return sMemo[position][digitMask][isTight] == 1;
        }

        int startDigit = 0;

        if (position == 0) {
            startDigit = 1;
        }

        int lowerDigit = 0;

        if (isTight == 1) {
            lowerDigit = sLowerBound.charAt(position) - '0';
        }

        for (int digit = startDigit; digit <= 9; ++digit) {
            if (isTight == 1 && digit < lowerDigit) {
                continue;
            }

            int nextDigitMask = digitMask | (1 << digit);
            int nextIsTight = 0;

            if (isTight == 1 && digit == lowerDigit) {
                nextIsTight = 1;
            }

            if (canBuildNumber(position + 1, nextDigitMask, nextIsTight)) {
                sMemo[position][digitMask][isTight] = 1;
                return true;
            }
        }

        sMemo[position][digitMask][isTight] = 0;
        return false;
    }
}