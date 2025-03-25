import java.util.Scanner;
import java.util.Arrays;
import java.math.BigInteger;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberCount = scanner.nextInt();
        String[] numbers = new String[numberCount];
        for (int i = 0; i < numberCount; ++i) {
            numbers[i] = scanner.next();
        }
        int k = scanner.nextInt();
        scanner.close();

        BigInteger originalSum = BigInteger.ZERO;
        for (int i = 0; i < numberCount; ++i) {
            originalSum = originalSum.add(new BigInteger(numbers[i], 36));
        }

        BigInteger[] gains = new BigInteger[36];
        for (int i = 0; i < 36; ++i) {
            gains[i] = BigInteger.ZERO;
        }

        for (int i = 0; i < numberCount; ++i) {
            String current = numbers[i];
            int length = current.length();
            for (int j = 0; j < length; ++j) {
                char c = current.charAt(length - 1 - j);
                int value = getValue(c);
                int diff = 35 - value;
                if (diff > 0) {
                    BigInteger pow = BigInteger.valueOf(36).pow(j);
                    BigInteger gain = BigInteger.valueOf(diff).multiply(pow);
                    gains[value] = gains[value].add(gain);
                }
            }
        }

        DigitGain[] digitGains = new DigitGain[36];
        for (int i = 0; i < 36; ++i) {
            digitGains[i] = new DigitGain(i, gains[i]);
        }
        Arrays.sort(digitGains, (a, b) -> b.gain.compareTo(a.gain));

        BigInteger sumOfGains = BigInteger.ZERO;
        for (int i = 0; i < k; ++i) {
            sumOfGains = sumOfGains.add(digitGains[i].gain);
        }

        BigInteger finalSum = originalSum.add(sumOfGains);
        System.out.println(toBase36(finalSum));
    }

    private static class DigitGain {
        private int index;
        private BigInteger gain;

        private DigitGain(int index, BigInteger gain) {
            this.index = index;
            this.gain = gain;
        }
    }

    private static int getValue(char c) {
        if (c >= '0' && c <= '9') {
            return c - '0';
        } else {
            return c - 'A' + 10;
        }
    }

    private static String toBase36(BigInteger value) {
        return value.toString(36).toUpperCase();
    }
}