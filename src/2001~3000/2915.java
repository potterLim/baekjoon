import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.next();

        int[] targetCount = getRomanCount(input);

        for (int i = 1; i < 100; ++i) {
            String roman = toRoman(i);
            int[] currentCount = getRomanCount(roman);

            if (isSameCount(targetCount, currentCount)) {
                System.out.println(roman);
                break;
            }
        }

        scanner.close();
    }

    private static String toRoman(int number) {
        String[] tensRomans = {
                "",
                "X",
                "XX",
                "XXX",
                "XL",
                "L",
                "LX",
                "LXX",
                "LXXX",
                "XC"
        };

        String[] onesRomans = {
                "",
                "I",
                "II",
                "III",
                "IV",
                "V",
                "VI",
                "VII",
                "VIII",
                "IX"
        };

        int tens = number / 10;
        int ones = number % 10;

        return tensRomans[tens] + onesRomans[ones];
    }

    private static int[] getRomanCount(String roman) {
        int[] count = new int[5];

        for (int i = 0; i < roman.length(); ++i) {
            char ch = roman.charAt(i);

            if (ch == 'I') {
                ++count[0];
            } else if (ch == 'V') {
                ++count[1];
            } else if (ch == 'X') {
                ++count[2];
            } else if (ch == 'L') {
                ++count[3];
            } else if (ch == 'C') {
                ++count[4];
            }
        }

        return count;
    }

    private static boolean isSameCount(int[] a, int[] b) {
        for (int i = 0; i < a.length; ++i) {
            if (a[i] != b[i]) {
                return false;
            }
        }

        return true;
    }
}