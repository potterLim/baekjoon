import java.util.Scanner;

public class Main {
    private static int minInt(int a, int b) {
        if (a < b) {
            return a;
        }
        return b;
    }

    private static int[] buildZArray(char[] text) {
        int length = text.length;
        int[] zValue = new int[length];

        zValue[0] = length;

        int left = 0;
        int right = 0;

        int i = 1;
        while (i < length) {
            int matchLength = 0;

            if (i <= right) {
                int mirroredIndex = i - left;
                matchLength = minInt(right - i + 1, zValue[mirroredIndex]);
            }

            while (i + matchLength < length && text[matchLength] == text[i + matchLength]) {
                ++matchLength;
            }

            zValue[i] = matchLength;

            int newRight = i + matchLength - 1;
            if (newRight > right) {
                left = i;
                right = newRight;
            }

            ++i;
        }

        return zValue;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int stringLength = scanner.nextInt();
        int appendLimit = scanner.nextInt();
        String originalString = scanner.next();

        char[] text = originalString.toCharArray();
        int[] zValue = buildZArray(text);

        long maxTotalLength = (long) stringLength + (long) appendLimit;

        int answer = 0;

        int patternLength = stringLength;
        while (patternLength >= 1) {
            boolean isPeriod = false;

            if (patternLength == stringLength) {
                isPeriod = true;
            } else {
                if (zValue[patternLength] >= stringLength - patternLength) {
                    isPeriod = true;
                }
            }

            if (isPeriod) {
                long p = (long) patternLength;

                long minimalMultiple = ((stringLength + patternLength - 1L) / p) * p;
                long requiredLength = minimalMultiple;

                long twoRepeats = 2L * p;
                if (requiredLength < twoRepeats) {
                    requiredLength = twoRepeats;
                }

                if (requiredLength <= maxTotalLength) {
                    answer = patternLength;
                    break;
                }
            }

            --patternLength;
        }

        System.out.print(answer);
    }
}